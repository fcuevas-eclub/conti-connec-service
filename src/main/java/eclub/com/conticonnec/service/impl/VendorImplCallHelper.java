package eclub.com.conticonnec.service.impl;

import eclub.com.conticonnec.service.VendorCallHelper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class VendorImplCallHelper implements VendorCallHelper {
    @Override
    public <T,S> ResponseEntity<T> callGetObject(RestTemplate restTemplate,
                                                 String url,
                                                 Class<T> responseType,
                                                 S input,
                                                 Map<String, String> headerParams) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.GET, requestEntity, responseType, null);
    }

    @Override
    public <T,S> ResponseEntity<T> callGetObject(RestTemplate restTemplate,
                                                 String url,
                                                 Class<T> responseType,
                                                 S input,
                                                 Map<String, String> headerParams,
                                                 Map<String, ?> uriVariables) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T,S> ResponseEntity<T> callPostObject(RestTemplate restTemplate,
                                                  String url,
                                                  Class<T> responseType,
                                                  S input,
                                                  Map<String, String> headerParams) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.POST, requestEntity, responseType, null);
    }

    @Override
    public <T,S> ResponseEntity<T> callPostObject(RestTemplate restTemplate,
                                                  String url,
                                                  Class<T> responseType,
                                                  S input,
                                                  Map<String, String> headerParams,
                                                  Map<String, ?> uriVariable) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.POST, requestEntity, responseType, uriVariable);
    }

    @Override
    public <T,S> ResponseEntity<T> callPutObject(RestTemplate restTemplate,
                                                 String url,
                                                 Class<T> responseType,
                                                 S input,
                                                 Map<String, String> headerParams) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.PUT, requestEntity, responseType, null);
    }

    @Override
    public <T,S> ResponseEntity<T> callPutObject(RestTemplate restTemplate,
                                                 String url,
                                                 Class<T> responseType,
                                                 S input,
                                                 Map<String, String> headerParams,
                                                 Map<String, ?> uriVariable) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.PUT, requestEntity, responseType, uriVariable);
    }

    @Override
    public <T,S> ResponseEntity<T> callDeleteObject(RestTemplate restTemplate,
                                                    String url,
                                                    Class<T> responseType,
                                                    S input,
                                                    Map<String, String> headerParams) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.DELETE, requestEntity, responseType, null);
    }

    @Override
    public <T,S> ResponseEntity<T> callDeleteObject(RestTemplate restTemplate,
                                                    String url,
                                                    Class<T> responseType,
                                                    S input,
                                                    Map<String, String> headerParams,
                                                    Map<String, ?> uriVariable) {
        HttpEntity<S> requestEntity = new HttpEntity<>(input, createHeader(headerParams));
        return exchange(restTemplate, url, HttpMethod.DELETE, requestEntity, responseType, uriVariable);
    }

    private HttpHeaders createHeader(Map<String, String> headerParam) {
        if(headerParam == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();

        for(Map.Entry<String, String> entry:headerParam.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }

        return headers;
    }

    private <T,S> ResponseEntity<T> exchange(RestTemplate restTemplate,
                                             String url,
                                             HttpMethod methodType,
                                             HttpEntity<S> requestEntity,
                                             Class<T> responseType,
                                             Map<String, ?> uriVariables) {
        return uriVariables != null ?
                restTemplate.exchange(url, methodType, requestEntity, responseType, uriVariables) :
                restTemplate.exchange(url, methodType, requestEntity, responseType);
    }

}
