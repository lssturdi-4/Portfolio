package com.sturdy_softwares;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    static final ObservableList<Entry> entryList = FXCollections.observableArrayList();

    static final Logger logger = Logger.getLogger(Main_DisplayController.class.getName());
    
    static final IdGenerator idGenerator = new IdGenerator();

    public void initialize() throws IOException {
        Utilities utilities = App.utilities;
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
                try {
                    utilities.updateEntry(cellData.getValue());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to update application status", e);
                }
            });
            return new javafx.beans.property.SimpleObjectProperty<>(comboBox);
        });
        date_applied.setCellValueFactory(cellData -> {
            DatePicker datePicker = new DatePicker();
            if (cellData.getValue().getDate_applied() != null) {
                datePicker.setValue(cellData.getValue().getDate_applied().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            }
            datePicker.setOnAction(event -> {
                cellData.getValue().setDate_applied(java.util.Date.from(datePicker.getValue().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
                try {
                    utilities.updateEntry(cellData.getValue());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to update application date", e);
                }
            });
            return new javafx.beans.property.SimpleObjectProperty<>(datePicker);
        });
        intrvw.setCellValueFactory(cellData -> {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(cellData.getValue().isInterview());
            checkBox.setOnAction(event -> {
                cellData.getValue().setInterview(checkBox.isSelected());
                try {
                    utilities.updateEntry(cellData.getValue());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to update interview status", e);
                }
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
                try {
                    utilities.updateEntry(cellData.getValue());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to update interview date", e);
                }
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
                File file = new File(cellData.getValue().getResume_path());
                if (file.exists()) {
                    // Code to open the selected resume file
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(file);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Failed to open resume file", e);
                        System.out.println("Failed to open resume file: " + e.getMessage());
                    }
                }
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
                File file = new File(cellData.getValue().getCover_letter_path());
                if (file.exists()) {
                    // Code to open the selected cover letter file
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(file);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Failed to open cover letter file", e);
                        System.out.println("Failed to open cover letter file: " + e.getMessage());
                    }
                }
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
                try {
                    utilities.removeEntry(selectedEntry.getId());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to delete entry", e);
                }
            }
        });
        
        edit_btn.setOnAction(event -> {
            Entry selectedEntry = tracker_table.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                // Code to open an edit dialog for the selected entry
                entryList.remove(selectedEntry);
                try {
                    utilities.removeEntry(selectedEntry.getId());
                    handleEdit(selectedEntry);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to open edit form", e);
                }
            }
        });

        new_btn.setOnAction(event -> {
            // Code to open a new entry form
            try {
                handleNew();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to open new form", e);
            }
        });

        open_btn.setOnAction(event -> {
            Entry selectedEntry = tracker_table.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                // Code to open a detailed view of the selected entry
                try {
                    handleOpen(selectedEntry);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Failed to open detailed view", e);
                }
            }
        });
    }

    @FXML
    private void handleEdit(Entry entry) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_form.fxml"));
        Parent root = loader.load();
        App.setRoot(root);

        App.editEntryController = loader.getController();
        App.editEntryController.setEntryData(entry);
    }

    @FXML
    private void handleNew() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new_entry_form.fxml"));
        Parent root = loader.load();
        App.setRoot(root);
        App.newEntryController = loader.getController();
    }

    @FXML
    private void handleOpen(Entry entry) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("open_form.fxml"));
        Parent root = loader.load();
        App.setRoot(root);
        // Code to initialize the open form with the selected entry's data
        App.openform = loader.getController();
        App.openform.initialize(entry);
    }

    public void loadEntries() throws IOException {
        // Code to load data from a file or database and populate the entryList
        entryList.clear();
        App.utilities.loadData();
        entryList.setAll(App.utilities.getEntries());
    }

}
