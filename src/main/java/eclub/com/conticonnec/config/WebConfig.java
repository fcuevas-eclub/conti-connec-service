package eclub.com.conticonnec.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.net.ssl.SSLContext;
import java.security.*;
import java.security.cert.CertificateException;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${api.conti.comm-protocol}")
    private String contibankProtocol;

    @Value("${api.conti.connTimeout}")
    private int contibankConnTimeout;

    @Value("${api.conti.readTimeout}")
    private int contibankReadTimeout;

    @Value("${api.conti.SSLFilePath}")
    private Resource contibankSSLFilePath;

    @Value("${api.conti.SSLPwd}")
    private String contibankSSLPwd;

    @Bean(name = "contiRestTemplate")
    RestTemplate restTemplate() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, URISyntaxException {

        KeyStore clientStore = KeyStore.getInstance("PKCS12");
        clientStore.load(new FileInputStream(contibankSSLFilePath.getFile()), contibankSSLPwd.toCharArray());

        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        sslContextBuilder.useProtocol("TLS");
        sslContextBuilder.loadKeyMaterial(clientStore, contibankSSLPwd.toCharArray());
        sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(contibankConnTimeout); // x seconds
        requestFactory.setReadTimeout(contibankReadTimeout); // x seconds

        return new RestTemplate(requestFactory);

    }

}
