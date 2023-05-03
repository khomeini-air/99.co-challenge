package co99.khomeini.challenge.application.web.integration.service;

import co99.khomeini.challenge.application.web.api.model.User;
import co99.khomeini.challenge.application.web.integration.response.CreateUserResponse;
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
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserWebClientServiceImpl implements UserWebClientService {
    final static String USER_PATH = "/users";
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Value("${integration.user.api.baseurl}")
    protected String serviceBaseUrl;

    protected Logger logger = Logger.getLogger(UserWebClientServiceImpl.class.getName());

    @Override
    public User createUser(final String name) throws JsonProcessingException {
        User result = null;
        final String serviceUrl = String.format("%s%s", serviceBaseUrl, USER_PATH);
        final LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl);
        parameters.put("name", List.of(name));

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
            result = getUserFromResponse(responseEntity.getBody());
        }

        return result;
    }

    private User getUserFromResponse(final String responseString) throws JsonProcessingException {
        if (!StringUtils.hasLength(responseString)) {
            return null;
        }
        final ObjectMapper mapper = new ObjectMapper();
        final CreateUserResponse response = mapper.readValue(responseString, CreateUserResponse.class);

        return response.getUser();
    }
}
