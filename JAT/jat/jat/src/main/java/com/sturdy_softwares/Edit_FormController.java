package com.sturdy_softwares;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

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

    static final Logger logger = Logger.getLogger(Edit_FormController.class.getName());


    @FXML
    void return_main(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jat_main.fxml"));
        Parent root = loader.load();
        App.setRoot(root);
    }

    @FXML
    void save_entry() throws IOException {
        Utilities utilities = new Utilities();

        Entry updatedEntry = getEntry();

        if (updatedEntry == null) {
            return;
        }

        utilities.loadData();
        utilities.removeEntry(entry.getId());
        utilities.addEntry(updatedEntry);

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
        browse_res.setTooltip(new Tooltip("Browse for resume file"));
        browse_cl.setTooltip(new Tooltip("Browse for cover letter file"));

        save_btn.setOnAction(event -> {
            try {
                save_entry();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to save entry", e);
            }
        });

        cancel_btn.setOnAction(event -> {
            try {
                Main_DisplayController.entryList.add(entry);
                return_main(event);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to return to main display", e);
            }
        });

        browse_res.setOnAction(event -> {
            // Code to open a file chooser and set the selected resume path
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Resume File");
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                resume_path.setText(selectedFile.getAbsolutePath());
                resume_path.setTooltip(new Tooltip(selectedFile.getAbsolutePath()));
                entry.setResume(true);
            }
        });

        browse_cl.setOnAction(event -> {
            // Code to open a file chooser and set the selected cover letter path
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Cover Letter File");
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                cl_path.setText(selectedFile.getAbsolutePath());
                cl_path.setTooltip(new Tooltip(selectedFile.getAbsolutePath()));
                entry.setCover_letter(true);
            }
        });

    }

    public void setEntryData(Entry entry) {
        this.entry = entry;
        // Code to populate the edit form fields with the entry data
        job_title.setText(entry.getJob_title());
        cmp_name.setText(entry.getCompany_name());
        work_loc.setText(entry.getWork_loc());
        app_status.setValue(entry.getApp_status());
        amount.setText(String.valueOf(entry.getAmount()));
        rate.setValue(entry.getRate());
        
        date_applied.setValue(entry.getDate_applied() != null && !entry.getDate_applied().toString().isEmpty() ? entry.getDate_applied().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        intrvw.setSelected(entry.isInterview());
        intrvw_date.setValue(entry.getInterview_date() != null && !entry.getInterview_date().toString().isEmpty() ? entry.getInterview_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        resume_path.setText(entry.getResume_path());
        cl_path.setText(entry.getCover_letter_path());
        description.setText(entry.getDescription());
    }

    public Entry getEntry() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Code to save the edited entry
        // You can access the form fields and update the entry object accordingly
        entry.setJob_title(job_title.getText());
        entry.setCompany_name(cmp_name.getText());
        entry.setWork_loc(work_loc.getText());
        entry.setApp_status(app_status.getValue());
        try {
            entry.setAmount(Double.parseDouble(amount.getText()));
        } catch (NumberFormatException e) {
            error_label.setVisible(true);
            return null;
        }
        entry.setAmount(Double.parseDouble(amount.getText()));
        entry.setRate(rate.getValue());

        if (date_applied.getValue() != null) {
            Date dateApplied = Date.from(date_applied.getValue().atStartOfDay(defaultZoneId).toInstant());
            entry.setDate_applied(dateApplied);
        } else {
            entry.setDate_applied(null); // Clear date applied if not selected
        }

        entry.setInterview(intrvw.isSelected());
        
        if (intrvw.isSelected() && intrvw_date.getValue() != null) {
            Date interviewDate = Date.from(intrvw_date.getValue().atStartOfDay().atZone(defaultZoneId).toInstant());
            entry.setInterview_date(interviewDate);
        } else {
            entry.setInterview_date(null); // Clear interview date if not selected
        }

        entry.setResume(resume_path.getText() != null && !resume_path.getText().isEmpty());
        entry.setResume_path(resume_path.getText());

        entry.setCover_letter(cl_path.getText() != null && !cl_path.getText().isEmpty());
        entry.setCover_letter_path(cl_path.getText());

        entry.setDescription(description.getText());
        return entry;
    }
}
