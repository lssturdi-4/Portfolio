package com.sturdy_softwares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Edit_FormController {
    private Entry entry;

    public void initialize(Entry entry) {
        // Code to initialize the edit form
        this.entry = entry;
        // Code to populate the edit form fields with the entry data
        job_title.setText(entry.getJob_title());
    }

    public void setEntryData(Entry entry) {
        this.entry = entry;
        // Code to populate the edit form fields with the entry data
        job_title.setText(entry.getJob_title());
    }

    public Entry getEntry() {
        // Code to create an Entry object from the edit form fields
        return entry;
    }

    @FXML
    private ImageView cancel_btn;

    @FXML
    private TextField job_title;

    @FXML
    private ImageView logo;

    @FXML
    private Button save_btn;

    @FXML
    private ImageView title;

    @FXML
    void return_main(MouseEvent event) {

    }

    @FXML
    void save_entry(ActionEvent event) {

    }

}
