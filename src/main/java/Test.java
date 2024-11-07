import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.model.request.RequestDTO;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {


        /*

        // GET запрос:
        ResponseEntity<RequestDTO[]> response = new RestTemplate().getForEntity("http://94.198.50.185:7081/api/users", RequestDTO[].class);
        RequestDTO[] users = response.getBody();
        System.out.println(Arrays.asList(users));


        // POST запрос (сохранение нового пользователя):
        User newUser = new User("James", "Brown", 30);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://94.198.50.185:7081/api/users", request, String.class);


        // PUT запрос (обновление пользователя):
        User updatedUser = new User("Thomas", "Shelby", 31);
        HttpEntity<User> requestUpdate = new HttpEntity<>(updatedUser, headers);

        restTemplate.put("http://94.198.50.185:7081/api/users/3", requestUpdate);


        // DELETE запрос (удаление пользователя):
        restTemplate.delete("http://94.198.50.185:7081/api/users/3");


        */



    }

}
