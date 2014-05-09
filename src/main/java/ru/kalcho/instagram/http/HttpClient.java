package ru.kalcho.instagram.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 *
 */
public class HttpClient {

    private static final String PARAMS_ENCODING = "UTF-8";

    private String url;

    public HttpClient setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpClient setUrl(String url, Map<String, String> params) {
        this.url = url;
        if (params != null) {
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    buf.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), PARAMS_ENCODING))
                            .append("&");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            String queryString = buf.toString();
            if (!queryString.isEmpty()) {
                if (!url.contains("?")) {
                    this.url += "?" + queryString;
                } else {
                    this.url += "&" + queryString;
                }
            }
        }
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String request() throws IOException {
        URLConnection connection = new URL(url).openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            StringBuilder response = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
                response.append(line);

            return response.toString();
        }
    }

}
