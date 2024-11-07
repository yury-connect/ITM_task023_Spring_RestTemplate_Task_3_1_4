package ru.itmentor.spring.rest.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.request.RequestDTO;
import ru.itmentor.spring.rest.template.model.response.Response;

import java.util.ArrayList;
import java.util.List;

import static ru.itmentor.spring.rest.template.constants.Constants.SOURCE_URL;


@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class RestTemplateUserControllerImpl implements RestTemplateUserController {

    private final RestTemplate restTemplate;

//    @Autowired
//    public RestTemplateUserController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }



    // step № 1
    @GetMapping
    public List<Response> getResponse() {
        /*{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity entity = new HttpEntity(headers);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("param1", "value1")
                    .queryParam("param2", "value2");

            HttpEntity&lt;String&gt; response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        }*/
        List<Response> list;
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(SOURCE_URL, ArrayList.class);
        return response.getBody();
    }





    @PostMapping
    public String getName(@RequestBody RequestDTO requestDTO) {
        HttpEntity<RequestDTO> requestDTOHttpEntity = new HttpEntity<>(requestDTO);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(SOURCE_URL, requestDTOHttpEntity, String.class);
        return stringResponseEntity.getBody();
    }
}
