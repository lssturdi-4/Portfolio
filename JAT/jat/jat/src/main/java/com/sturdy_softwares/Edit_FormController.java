package com.sturdy_softwares;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class Edit_FormController {
    private Entry entry;

    @FXML
    private TextField job_title;

    @FXML
    private TextField cmp_name;

    @FXML
    private TextField work_loc;

    @FXML
    private ComboBox<String> app_status;

    @FXML
    private TextField amount;
    
    @FXML
    private ComboBox<String> rate;

    @FXML
    private DatePicker date_applied;

    @FXML
    private CheckBox intrvw;

    @FXML
    private DatePicker intrvw_date;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button save_btn;

    @FXML
    private TextField resume_path;

    @FXML
    private Button browse_res;

    @FXML
    private TextField cl_path;

    @FXML
    private Button browse_cl;

    @FXML
    private TextArea description;

    @FXML
    private Label error_label;


    @FXML
    void return_main(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jat_main.fxml"));
        Parent root = loader.load();
        App.setRoot(root);
    }

    @FXML
    void save_entry(ActionEvent event) throws IOException {
        // Code to save the edited entry
        // You can access the form fields and update the entry object accordingly
        entry.setJob_title(job_title.getText());
        entry.setCompany_name(cmp_name.getText());
        // Update other fields as needed

        // After saving, return to the main display
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jat_main.fxml"));
        Parent root = loader.load();
        App.setRoot(root);

    }
    
    public void initialize() {
        // Initialization code, if needed
        app_status.getItems().addAll("Applied", "Interview", "Offered", "Rejected");
        rate.getItems().addAll("Hourly", "Salary");
        cancel_btn.setTooltip(new Tooltip("Cancel editing and return to main display"));
        save_btn.setTooltip(new Tooltip("Save changes and return to main display"));
    }

    public void setEntryData(Entry entry) {
        this.entry = entry;
        // Code to populate the edit form fields with the entry data
        job_title.setText(entry.getJob_title());
        cmp_name.setText(entry.getCompany_name());
    }

    public Entry getEntry() {
        // Code to create an Entry object from the edit form fields
        return entry;
    }


}
