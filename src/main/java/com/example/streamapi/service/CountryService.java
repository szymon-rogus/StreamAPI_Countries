package com.example.streamapi.service;

import com.example.streamapi.model.Country;
import com.example.streamapi.util.RequestHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CountryService {

    private final RequestHandler requestHandler;

    @Getter
    private final ObservableList<Country> countryList;

    public CountryService() {
        requestHandler = new RequestHandler();
        try {
            countryList = getAllCountries();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Country> getAllCountries() throws IOException, InterruptedException {
        return requestHandler.handleRequest("countries");
    }

    public ObservableList<Country> filterByName(String pattern) {
        return countryList.stream()
                .filter(country -> country.getName().toLowerCase().startsWith(pattern.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Country> filterByMinPopulation(int value) {
        return countryList.stream()
                .filter(country -> country.getPopulation() >= value)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Country> filterByMaxPopulation(int value) {
        return countryList.stream()
                .filter(country -> country.getPopulation() <= value)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public int getMinPopulation() {
        return countryList.stream()
                .map(Country::getPopulation)
                .min(Comparator.naturalOrder())
                .orElse(0);
    }

    public int getMaxPopulation() {
        return countryList.stream()
                .map(Country::getPopulation)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
