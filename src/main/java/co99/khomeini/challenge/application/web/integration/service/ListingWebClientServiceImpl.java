package co99.khomeini.challenge.application.web.integration.service;

import co99.khomeini.challenge.application.web.api.model.Listing;
import co99.khomeini.challenge.application.web.api.response.CreateListingResponse;
import co99.khomeini.challenge.application.web.integration.response.GetListingsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ListingWebClientServiceImpl implements ListingWebClientService {
    private static final String LISTING_PATH = "/listings";
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Value("${integration.listing.api.baseurl}")
    protected String serviceBaseUrl;

    protected Logger logger = Logger.getLogger(ListingWebClientServiceImpl.class.getName());

    @Override
    public List<Listing> findAll(final Integer userId, final Integer page, final Integer size) throws JsonProcessingException {
        List<Listing> result = null;
        final String serviceUrl = String.format("%s%s", serviceBaseUrl, LISTING_PATH);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl)
                .queryParam("page", page)
                .queryParam("size", size);

        if (!ObjectUtils.isEmpty(userId)) {
            builder.queryParam("user_id", userId);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    requestEntity,
                    String.class);
        } catch (HttpClientErrorException e) {
            // HTTP 404
        }

        if (!Objects.isNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = getListingsFromResponse(responseEntity.getBody());
        }

        return result;
    }

    @Override
    public Listing createListing(Integer userId, String type, Integer price) throws JsonProcessingException {
        Listing result = null;
        final String serviceUrl = String.format("%s%s", serviceBaseUrl, LISTING_PATH);
        final LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl);
        parameters.put("user_id", List.of(String.valueOf(userId)));
        parameters.put("listing_type", List.of(type));
        parameters.put("price", List.of(String.valueOf(price)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.CREATED) {
            result = getListingFromResponse(responseEntity.getBody());
        }

        return result;
    }

    private List<Listing> getListingsFromResponse(final String responseString) throws JsonProcessingException {
        if (!StringUtils.hasLength(responseString)) {
            return null;
        }
        final ObjectMapper mapper = new ObjectMapper();
        final GetListingsResponse response = mapper.readValue(responseString, GetListingsResponse.class);

        return response.getListings();
    }

    private Listing getListingFromResponse(final String responseString) throws JsonProcessingException {
        if (!StringUtils.hasLength(responseString)) {
            return null;
        }
        final ObjectMapper mapper = new ObjectMapper();
        final CreateListingResponse response = mapper.readValue(responseString, CreateListingResponse.class);

        return response.getListing();
    }

}
