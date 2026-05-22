package com.sturdy_softwares;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main_DisplayController { 
    @FXML
    private TableView<Entry> tracker_table;

    @FXML
    private TableColumn<Entry, ComboBox<String>> app_status;

    @FXML
    private TableColumn<Entry, String> cmp_name;

    @FXML
    private TableColumn<Entry, DatePicker> date_applied;
    
    
    @FXML
    private TableColumn<Entry, CheckBox> intrvw;
    
    @FXML
    private TableColumn<Entry, DatePicker> intrvw_date;
    
    @FXML
    private TableColumn<Entry, String> job_title;

    @FXML
    private TableColumn<Entry, Button> resume;

    @FXML
    private TableColumn<Entry, Button> cover_ltr;
    
    @FXML
    private Button del_btn, edit_btn, open_btn, new_btn;

    Entry entry1 = new Entry("Google", "Software Engineer", "Interview", new Date(), true, new Date(),false, false);
    Entry entry2 = new Entry("Amazon", "C Developer", "Applied", new Date(), false, null, false, true);
    Entry entry3 = new Entry("Microsoft", "Computer Scientist", "Offered", new Date(), true, new Date(), true, true);

    ObservableList<Entry> entryList = FXCollections.observableArrayList(Arrays.asList(entry1, entry2, entry3));

    public void initialize() {

        // Set tooltips for buttons
        del_btn.setTooltip(new Tooltip("Delete job entry"));
        edit_btn.setTooltip(new Tooltip("Edit job entry"));
        open_btn.setTooltip(new Tooltip("Open job entry"));
        new_btn.setTooltip(new Tooltip("Create new job entry"));

        // Initialize the table columns and other UI components here
        cmp_name.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCompany_name()));
        job_title.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getJob_title()));
        app_status.setCellValueFactory(cellData -> {
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("Applied", "Interview", "Offered", "Rejected");
            comboBox.setValue(cellData.getValue().getApp_status());
            comboBox.setOnAction(event -> {
                cellData.getValue().setApp_status(comboBox.getValue());
            });
            return new javafx.beans.property.SimpleObjectProperty<>(comboBox);
        });
        date_applied.setCellValueFactory(cellData -> {
            DatePicker datePicker = new DatePicker();
            datePicker.setValue(cellData.getValue().getDate_applied().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            datePicker.setOnAction(event -> {
                cellData.getValue().setDate_applied(java.util.Date.from(datePicker.getValue().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
            });
            return new javafx.beans.property.SimpleObjectProperty<>(datePicker);
        });
        intrvw.setCellValueFactory(cellData -> {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(cellData.getValue().isInterview());
            checkBox.setOnAction(event -> {
                cellData.getValue().setInterview(checkBox.isSelected());
            });
            return new javafx.beans.property.SimpleObjectProperty<>(checkBox);
        });
        intrvw.setStyle("-fx-alignment: CENTER;");
        intrvw_date.setCellValueFactory(cellData -> {
            DatePicker datePicker = new DatePicker();
            if (cellData.getValue().getInterview_date() != null) {
                datePicker.setValue(cellData.getValue().getInterview_date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            }
            datePicker.setOnAction(event -> {
                cellData.getValue().setInterview_date(java.util.Date.from(datePicker.getValue().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
            });
            datePicker.setDisable(!cellData.getValue().isInterview());
            return new javafx.beans.property.SimpleObjectProperty<>(datePicker);
        });
        // Initialize resume and cover letter buttons with appropriate actions
        resume.setCellValueFactory(cellData -> {
            Button button = new Button();
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/file-solid.png")));
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                button.setGraphic(imageView);
                button.setDisable(!entryList.get(tracker_table.getItems().indexOf(cellData.getValue())).isResume());
            button.setOnAction(event -> {
                // Code to view the resume
            });
            return new javafx.beans.property.SimpleObjectProperty<>(button);
        });
        resume.setStyle("-fx-alignment: CENTER;");
        cover_ltr.setCellValueFactory(cellData -> {
            Button button = new Button();
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/file-regular.png")));
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                button.setGraphic(imageView);
                button.setDisable(!entryList.get(tracker_table.getItems().indexOf(cellData.getValue())).isCover_letter());
            button.setOnAction(event -> {
                // Code to view the cover letter
            });
            return new javafx.beans.property.SimpleObjectProperty<>(button);
        });
        cover_ltr.setStyle("-fx-alignment: CENTER;");
        tracker_table.setItems(entryList);
        tracker_table.setEditable(true);


        // Initialize delete, edit, and open buttons with appropriate actions
        del_btn.setOnAction(event -> {
            Entry selectedEntry = tracker_table.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                entryList.remove(selectedEntry);
            }
        });
        
        edit_btn.setOnAction(event -> {
            Entry selectedEntry = tracker_table.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                // Code to open an edit dialog for the selected entry
                try {
                    handleEdit(selectedEntry);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        new_btn.setOnAction(event -> {
            // Code to open a new entry form
            try {
                handleNew();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        open_btn.setOnAction(event -> {
            Entry selectedEntry = tracker_table.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                // Code to open a detailed view of the selected entry
                try {
                    handleOpen(selectedEntry);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void handleEdit(Entry entry) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_form.fxml"));
        Parent root = loader.load();
        App.setRoot(root);

        Edit_FormController editController = loader.getController();
        editController.setEntryData(entry);

        entryList.remove(entry);
        entryList.add(editController.getEntry());
    }

    @FXML
    private void handleNew() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void handleOpen(Entry entry) throws IOException {
        App.setRoot("open_form");
        // Code to initialize the open form with the selected entry's data
        Open_FormController openController = new Open_FormController(entry);
    }

}
