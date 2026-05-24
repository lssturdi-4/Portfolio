package com.sturdy_softwares;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Open_FormController {
    Entry entry = new Entry();

    @FXML
    private Label app_status;
    
    @FXML
    private Label cl_path;
    
    @FXML
    private Label cmp_name;
    
    @FXML
    private Label date_applied;
    
    @FXML
    private Label description;
    
    @FXML
    private Button edit_btn;
    
    @FXML
    private Label interview_date;
    
    @FXML
    private Label interviewed;
    
    @FXML
    private Label job_title;
    
    @FXML
    private Label pay;
    
    @FXML
    private Label res_path;
    
    @FXML
    private Button return_btn;
    
    @FXML
    private Label work_loc;

    static final Logger logger = Logger.getLogger(Open_FormController.class.getName());


    public void initialize(Entry entry) throws IOException {
        this.entry = entry;
        edit_btn.setOnAction(event -> {
            try {
                edit_entry();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to open edit form", e);
            }
        });

        return_btn.setOnAction(event -> {
            try {
                return_main(event);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to return to main display", e);
            }
        });
        setEntryData(entry);
    }

    @FXML
    void edit_entry() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_form.fxml"));
        Parent root = loader.load();
        App.setRoot(root);
        // Code to open the edit form and pass the entry data for editing
        System.out.println("Opening edit form for entry: " + entry.getId());
        Edit_FormController editController = loader.getController();
        editController.setEntryData(entry);
        // Code to display the edit form (e.g., using a new stage or scene)

    }

    @FXML
    void return_main(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jat_main.fxml"));
        Parent root = loader.load();
        App.setRoot(root);

    }

    public void setEntryData(Entry entry) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cmp_name.setText(entry.getCompany_name() != null ? entry.getCompany_name() : "(Company Name)");
        job_title.setText(entry.getJob_title() != null ? entry.getJob_title() : "(Job Title)");
        app_status.setText(entry.getApp_status());
        date_applied.setText(entry.getDate_applied() != null ? formatter.format(entry.getDate_applied()) : "N/A");
        interviewed.setText(entry.isInterview() ? "Yes" : "No");

        interview_date.setText(entry.getInterview_date() != null ? formatter.format(entry.getInterview_date()) : "N/A");

        int lastSlashIndex = entry.getResume_path() != null ? entry.getResume_path().lastIndexOf("\\") : -1;
        String resumeFileName = (lastSlashIndex != -1 && entry.getResume_path().length() > lastSlashIndex + 1) ? entry.getResume_path().substring(lastSlashIndex + 1) : "N/A";
        res_path.setText(entry.isResume() ? resumeFileName : "N/A");
        

        int clLastSlashIndex = entry.getCover_letter_path() != null ? entry.getCover_letter_path().lastIndexOf("\\") : -1;
        String clFileName = (clLastSlashIndex != -1 && entry.getCover_letter_path().length() > clLastSlashIndex + 1) ? entry.getCover_letter_path().substring(clLastSlashIndex + 1) : "N/A";
        cl_path.setText(entry.isCover_letter() ? clFileName : "N/A");
        
        description.setText(entry.getDescription());
        work_loc.setText(entry.getWork_loc());

        String rateText = entry.getRate() != null ? entry.getRate().equalsIgnoreCase("Salary") ? "annually" : "per hour" : "N/A";
        pay.setText((entry.getAmount() > 0 ? " $" + entry.getAmount() : "") + (rateText.equals("N/A") ? "" : " " + rateText));
    }
}
