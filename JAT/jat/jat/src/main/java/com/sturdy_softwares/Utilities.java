package com.sturdy_softwares;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;



public class Utilities {

    static final Logger logger = Logger.getLogger(Main_DisplayController.class.getName());
    
    ObservableList<Entry> entryList = FXCollections.observableArrayList();

    public ObservableList<Entry> getEntries() throws IOException {
        loadData();
        return entryList;
    }

    public void addEntry(Entry entry) throws IOException {
        entryList.add(entry);
        saveData();
    }

    public void updateEntry(Entry updatedEntry) throws IOException {
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).getId() == updatedEntry.getId()) {
                entryList.set(i, updatedEntry);
                break;
            }
        }
        saveData();
    }
    
    public void loadData() {
        // Code to load data from a file or database and populate the entryList
        String filePath = "C:\\Users\\leroy\\OneDrive\\Documents\\Portfolio\\JAT\\jat\\jat\\src\\main\\resources\\data\\user_data.json";
        File dataFile;
        try {
            dataFile = new File(filePath);
            if (!dataFile.exists()) {
                logger.log(Level.WARNING, "Data file not found: " + filePath, new FileNotFoundException(filePath));
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to access data file: " + filePath, e);
            return;
        }
        ObjectMapper objectMapper = JsonMapper.builder()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)                            
                                    .build();
        JsonNode jsonObject = objectMapper.readTree(dataFile);
        jsonObject.get("entries").forEach(entryNode -> {
            int id = entryNode.get("id").asInt();
            String cmp_name = entryNode.get("company_name").asString();
            String job_title = entryNode.get("job_title").asString();
            String app_status = entryNode.get("app_status").asString();
            boolean interview = entryNode.get("interview").asBoolean();
            boolean resume = entryNode.get("resume").asBoolean();
            boolean cover_letter = entryNode.get("cover_letter").asBoolean();
            Entry entry = new Entry(cmp_name, job_title, app_status, interview, resume, cover_letter);
            entry.setId(id);
            try {
                String dateAppliedStr = entryNode.get("date_applied").asString();
                Date dateApplied = new SimpleDateFormat("yyyy-MM-dd").parse(dateAppliedStr);
                entry.setDate_applied(dateApplied);
            } catch (ParseException e) {
                logger.log(Level.SEVERE, "Failed to parse date applied", e);
            }
            try {
                String interviewDateStr = entryNode.get("interview_date").asString();
                Date interview_date = new SimpleDateFormat("yyyy-MM-dd").parse(interviewDateStr);
                entry.setInterview_date(interview_date);
            } catch (ParseException e) {
                logger.log(Level.SEVERE, "Failed to parse interview date", e);
            }
            entry.setWork_loc(entryNode.get("work_loc").asString());
            entry.setRate(entryNode.get("rate").asString());
            entry.setAmount(entryNode.get("amount").asDouble());
            entry.setDescription(entryNode.get("description").asString());
            entry.setResume_path(entryNode.get("resume_path").asString());
            entry.setCover_letter_path(entryNode.get("cover_letter_path").asString());
            entryList.add(entry);
        });
    }

    public void saveData() throws IOException {
        ObjectMapper objectMapper = JsonMapper.builder()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)                            
                                    .build();

        JsonNode entriesNode = objectMapper.valueToTree(entryList);
        JsonNode rootNode = objectMapper.createObjectNode().set("entries", entriesNode);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("C:\\Users\\leroy\\OneDrive\\Documents\\Portfolio\\JAT\\jat\\jat\\src\\main\\resources\\data\\user_data.json"), rootNode);
    }

    public void removeEntry(int removeId) throws IOException {
        entryList.removeIf(entry -> entry.getId() == removeId);
    }

}
