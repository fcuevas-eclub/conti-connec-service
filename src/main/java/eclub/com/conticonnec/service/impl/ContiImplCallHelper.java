package eclub.com.conticonnec.service.impl;

import eclub.com.conticonnec.dto.SeguimientoRequestDTO;
import eclub.com.conticonnec.service.VendorCallHelper;
import eclub.com.conticonnec.dto.SolicitudContiDTO;
import eclub.com.conticonnec.dto.SolicitudResumenContiDTO;
import eclub.com.conticonnec.service.ContiCallHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContiImplCallHelper implements ContiCallHelper {

    private final Logger logger = LoggerFactory.getLogger(ContiImplCallHelper.class);

    @Autowired
    private VendorCallHelper vendorCallHelper;

    @Autowired
    private RestTemplate contiRestTemplate;

    @Value("${api.conti.subscription_key}")
    private String ContiSubcriptionKey;

    @Value("${api.conti.client_id}")
    private String ContiClientId;

    @Value("${api.conti.client_secret}")
    private String ContiClientSecret;

    @Value("${api.conti.url.post_token}")
    private String urlContiToken;

    @Value("${api.conti.url.base}")
    private String urlContiBase;

    @Value("${api.conti.url.post_solicitud}")
    private String urlContiPostSolicitud;

    @Value("${api.conti.url.get_by_nro_solicitud}")
    private String urlContiGetByNroSolicitud;

    @Value("${api.conti.url.get_by_state}")
    private String urlContiGetByState;

    @Value("${api.conti.url.post_registrar_seguimiento}")
    private String urlRegistrarSeguimiento;


    private String getToken() throws Exception {
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerParams.put("Connection", "keep-alive");
        headerParams.put("Grant-Type", "client_credentials");
        headerParams.put("Scope", "profile");
        headerParams.put("Subscription-Key", ContiSubcriptionKey);
        headerParams.put("Client-Id", ContiClientId);
        headerParams.put("Client-Secret", ContiClientSecret);

        ResponseEntity<Object> response = vendorCallHelper.callPostObject(contiRestTemplate,
                                                                            urlContiToken,
                                                                            Object.class,
                                                                            null,
                                                                            headerParams);
        if (response == null)
            throw new Exception("Error procesar la solicitud!");
        if (response.getStatusCode().value() == 401)
            throw new Exception("error: invalid_grant, error_description: Invalid user credentials");
        if (response.getStatusCode().value() == 500)
            throw new Exception("Error generado en el servido");
        if (response.getStatusCode().isError())
            throw new Exception("Error desconocido");

        JSONObject jsonObject = new JSONObject((Map) response.getBody());
        String textoToken = (String) jsonObject.get("access_token");
        return textoToken;
    }

    @Override
    public ResponseEntity sendSolicitud(SolicitudContiDTO dto) throws Exception {
        String token = this.getToken();

        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerParams.put("Subscription-Key", ContiSubcriptionKey);
        headerParams.put("Authorization", "Bearer " + token);

        ResponseEntity response;
        try {
            response = vendorCallHelper.callPostObject(contiRestTemplate,
                                                    urlContiBase + urlContiPostSolicitud,
                                                        String.class,
                                                        dto,
                                                        headerParams);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
        String nroSolicitud = (String) jsonObject.get("mensaje");

        return new ResponseEntity(nroSolicitud, HttpStatus.OK);
    }

    @Override
    public SolicitudResumenContiDTO GetSolicitudByNroSolicitud(String nrosolicitud) throws Exception {
        String token = this.getToken();

        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerParams.put("Authorization", "Bearer " + token);
        headerParams.put("Subscription-Key", ContiSubcriptionKey);

        ResponseEntity<SolicitudResumenContiDTO> response = vendorCallHelper.callGetObject(contiRestTemplate,
                                                                                        urlContiBase + urlContiGetByNroSolicitud + "/" + nrosolicitud,
                                                                                            SolicitudResumenContiDTO.class,
                                                                                        null,
                                                                                            headerParams);

        return response.getBody();
    }

    @Override
    public ResponseEntity GetSolicitudByEstado(int estado) throws Exception {
        String token = this.getToken();

        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerParams.put("Authorization", "Bearer " + token);
        headerParams.put("Subscription-Key", ContiSubcriptionKey);

        ResponseEntity response = vendorCallHelper.callGetObject(contiRestTemplate,
                                                                urlContiBase + urlContiGetByState + "/" + estado,
                                                                    Object.class,
                                                                null,
                                                                headerParams);

        return response;
    }

    @Override
    //public ResponseEntity<?> registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception {
    public ResponseEntity registrarSeguimiento(SeguimientoRequestDTO dto) throws Exception {
        String token = this.getToken();

        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headerParams.put("Subscription-Key", ContiSubcriptionKey);
        headerParams.put("Authorization", "Bearer " + token);

        try {
            ResponseEntity response;
            response = vendorCallHelper.callPostObject(contiRestTemplate,
                                                    urlContiBase + urlRegistrarSeguimiento,
                                                        String.class,
                                                        dto,
                                                        headerParams);
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
