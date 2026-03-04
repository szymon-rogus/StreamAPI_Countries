package com.example.streamapi.controller;

import com.example.streamapi.model.Country;
import com.example.streamapi.service.CountryService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryController implements Initializable {

    private CountryService countryService;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, String> countryName;

    @FXML
    private TableColumn<Country, String> countryPopulation;

    @FXML
    private TableColumn<Country, String> countryArea;

    @FXML
    private TextField countryNameFilter;

    @FXML
    private RangeSlider populationRange;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryService = new CountryService();

        setCellValueFactories();
        countryTable.setItems(countryService.getCountryList());

        setRanges();

        setListeners();
    }

    private void setCellValueFactories() {
        countryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));
        countryArea.setCellValueFactory(new PropertyValueFactory<>("area"));
    }


    private void setListeners() {
        countryNameFilter.textProperty().addListener((_, _, newText)
                -> countryTable.setItems(countryService.filterByName(newText)));

        populationRange.lowValueProperty().addListener((_, _, newValue) -> {
            countryTable.setItems(countryService.filterByMinPopulation(newValue.intValue()));
            System.out.println(newValue);
        });

        populationRange.highValueProperty().addListener((_, _, newValue) -> {
            countryTable.setItems(countryService.filterByMaxPopulation(newValue.intValue()));
            System.out.println(newValue);
        });
    }

    private void setRanges() {
        int minPopulation = countryService.getMinPopulation();
        int maxPopulation = countryService.getMaxPopulation();

        this.populationRange.setMin(minPopulation);
        this.populationRange.setMax(maxPopulation);
        this.populationRange.setLowValue(minPopulation);
        this.populationRange.setHighValue(maxPopulation);
    }
}
