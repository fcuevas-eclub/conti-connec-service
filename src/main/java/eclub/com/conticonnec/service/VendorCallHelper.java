package eclub.com.conticonnec.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface VendorCallHelper {
    <T,S> ResponseEntity<T> callGetObject(RestTemplate restTemplate,
                                          String url,
                                          Class<T> responseType,
                                          S input,
                                          Map<String, String> headerParams);

    <T,S> ResponseEntity<T> callGetObject(RestTemplate restTemplate,
                                          String url,
                                          Class<T> responseType,
                                          S input,
                                          Map<String, String> headerParams,
                                          Map<String, ?> uriVariables);

    <T,S> ResponseEntity<T> callPostObject(RestTemplate restTemplate,
                                           String url,
                                           Class<T> responseType,
                                           S input,
                                           Map<String, String> headerParams);

    <T,S> ResponseEntity<T> callPostObject(RestTemplate restTemplate,
                                           String url,
                                           Class<T> responseType,
                                           S input,
                                           Map<String, String> headerParams,
                                           Map<String, ?> uriVariable);

    <T,S> ResponseEntity<T> callPutObject(RestTemplate restTemplate,
                                          String url,
                                          Class<T> responseType,
                                          S input,
                                          Map<String, String> headerParams);

    <T,S> ResponseEntity<T> callPutObject(RestTemplate restTemplate,
                                          String url,
                                          Class<T> responseType,
                                          S input,
                                          Map<String, String> headerParams,
                                          Map<String, ?> uriVariable);

    <T,S> ResponseEntity<T> callDeleteObject(RestTemplate restTemplate,
                                             String url,
                                             Class<T> responseType,
                                             S input,
                                             Map<String, String> headerParams);

    <T,S> ResponseEntity<T> callDeleteObject(RestTemplate restTemplate,
                                             String url,
                                             Class<T> responseType,
                                             S input,
                                             Map<String, String> headerParams,
                                             Map<String, ?> uriVariable);
}
