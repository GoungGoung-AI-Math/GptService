package com.gonggong.gpt4j.openaiClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientConnection {

    public static HttpURLConnection getHttpURLConnection(String json, String clientUrl, ApiKey apiKey) throws IOException {
        URL url = new URL(clientUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("POST");

        // Set headers
        connection.setRequestProperty("Authorization", "Bearer " + apiKey.getApiKey());
        connection.setRequestProperty("Content-Type", "application/json");

        // Enable input and output streams
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // Write JSON data to request body
        OutputStream os = connection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        return connection;
    }
}
