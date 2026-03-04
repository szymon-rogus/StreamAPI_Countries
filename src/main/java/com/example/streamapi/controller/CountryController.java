package com.example.streamapi.controller;

import com.example.streamapi.model.Country;
import com.example.streamapi.service.CountryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryService = new CountryService();
        countryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));
        countryArea.setCellValueFactory(new PropertyValueFactory<>("area"));

        countryTable.setItems(countryService.getCountryList());

        setListeners();
    }


    private void setListeners() {
        countryNameFilter.textProperty().addListener((_, _, newText) -> {
            countryTable.setItems(countryService.filterByName(newText));
        });
    }
}
