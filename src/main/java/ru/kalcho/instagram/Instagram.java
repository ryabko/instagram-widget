package ru.kalcho.instagram;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.kalcho.instagram.http.HttpClient;
import ru.kalcho.instagram.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Instagram {

    private static final String INSTAGRAM_URL = "https://api.instagram.com/v1";

    private String clientId;

    private Gson jsonParser;

    public Instagram(String clientId) {
        this.clientId = clientId;
        this.jsonParser = createJsonParser();
    }

    private Gson createJsonParser() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public Response recentMedia(String userId, int count) {
        HttpClient httpClient = new HttpClient();
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("count", String.valueOf(count));
        httpClient.setUrl(INSTAGRAM_URL + "/users/" + userId + "/media/recent", params);
        try {
            String response = httpClient.request();
            return jsonParser.fromJson(response, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
