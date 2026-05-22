package com.sturdy_softwares;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

    Entry entry1 = new Entry("Sample 1", "Software Engineer", "Interview", true,false, false);
    Entry entry2 = new Entry("Sample 2", "C Developer", "Applied", true, false, true);
    Entry entry3 = new Entry("Sample 3", "Computer Scientist", "Offered", true, true, true);
    

    ObservableList<Entry> sampleList = FXCollections.observableArrayList(Arrays.asList(entry1, entry2, entry3));
    ObservableList<Entry> entryList = FXCollections.observableArrayList();

    public ObservableList<Entry> getSampleEntries() throws IOException {
        entry1.setDate_applied(new Date());
        entry2.setDate_applied(new Date());
        entry3.setDate_applied(new Date());
        entry1.setInterview_date(new Date());
        entry2.setInterview_date(new Date());
        entry3.setInterview_date(new Date());
        loadData();
        return sampleList;
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
    }
    
    public void loadData() throws IOException {
        // Code to load data from a file or database and populate the entryList
        System.out.println("Loading data...");
        ObjectMapper objectMapper = JsonMapper.builder()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)                            
                                    .build();
        JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\leroy\\OneDrive\\Documents\\Portfolio\\JAT\\jat\\jat\\src\\main\\java\\com\\sturdy_softwares\\mydata.json"));
        String name = jsonNode.get("name").asString();
        int age = jsonNode.get("age").asInt();
        String city = jsonNode.get("city").asString();
        String state = jsonNode.get("state").asString();
        String country = jsonNode.get("country").asString();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Country: " + country);
        JsonNode jsonObject = objectMapper.readTree(new File("C:\\Users\\leroy\\OneDrive\\Documents\\Portfolio\\JAT\\jat\\jat\\src\\main\\resources\\data\\sample.json"));
        System.out.println("Entries in JSON: " + jsonObject.get("entries").size());
        jsonObject.get("entries").forEach(entryNode -> {
            String cmp_name = entryNode.get("company").asString();
            String job_title = entryNode.get("job_title").asString();
            String app_status = entryNode.get("app_status").asString();
            boolean interview = entryNode.get("interviewed").asBoolean();
            boolean resume = entryNode.get("resume").asBoolean();
            boolean cover_letter = entryNode.get("cover_letter").asBoolean();
            Entry entry = new Entry(cmp_name, job_title, app_status, interview, resume, cover_letter);
            try {
                String dateAppliedStr = entryNode.get("date_applied").asString();
                Date dateApplied = new SimpleDateFormat("yyyy-MM-dd").parse(dateAppliedStr);
                entry.setDate_applied(dateApplied);
            } catch (ParseException e) {
                logger.log(Level.SEVERE, "Failed to parse date applied", e);
            }
            try {
                String interviewDateStr = entryNode.get("interviewDate").asString();
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
            sampleList.add(entry);
        });
        System.out.println("Loaded " + entryList.size() + " entries.");
    }

    public void saveData() throws IOException {
        // ObjectMapper objectMapper = new ObjectMapper();
        // ObjectNode jsonNode = objectMapper.createObjectNode();
        // jsonNode.put("name", "Abul Hasan");
        // jsonNode.put("age", 23);
        // jsonNode.put("city", "Lucknow");
        // jsonNode.put("state", "Uttar Pradesh");
        // jsonNode.put("country", "India");
        // objectMapper.writeValue(new File("mydata.json"), jsonNode);
    }


}
