package com.example.streamapi.util;

import com.example.streamapi.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class RequestHandler {

    private static final String BASE_URL = "https://www.apicountries.com/";

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    public RequestHandler() {
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newBuilder().build();
    }

    public ObservableList<Country> handleRequest(String endPoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endPoint))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Country[] countries = objectMapper.readValue(response.body(), Country[].class);
        return FXCollections.observableArrayList(Arrays.asList(countries));
    }
}
