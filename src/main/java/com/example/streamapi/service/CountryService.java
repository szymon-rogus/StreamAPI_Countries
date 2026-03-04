package com.example.streamapi.service;

import com.example.streamapi.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CountryService {

    private final HttpClient httpClient;

    private static final String BASE_URL = "https://www.apicountries.com/";

    private final ObjectMapper objectMapper;

    @Getter
    private final ObservableList<Country> countryList;

    public CountryService() {
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newBuilder().build();
        try {
            countryList = getAllCountries();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Country> getAllCountries() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "countries"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Country[] countries = objectMapper.readValue(response.body(), Country[].class);
        return FXCollections.observableArrayList(Arrays.asList(countries));
    }

    public ObservableList<Country> filterByName(String pattern) {
        return countryList.stream()
                .filter(country -> country.getName().toLowerCase().startsWith(pattern.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
