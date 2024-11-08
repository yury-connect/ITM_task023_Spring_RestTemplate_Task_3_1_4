package ru.itmentor.spring.rest.template.util;

import org.springframework.stereotype.Component;
import ru.itmentor.spring.rest.template.model.AuthToken;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserServiceUtil {

    private final AuthToken authToken;
    private final Map<String, String> cookies;


    public UserServiceUtil() {
        this.authToken = new AuthToken();
        this.cookies = new HashMap<>();
    }


    public void setJwtToken(String jwtToken) {
        this.authToken.setJwtToken(jwtToken);
    }

    public void setCookie(String name, String value) {
        this.cookies.put(name, value);
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}
