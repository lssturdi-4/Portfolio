package com.sturdy_softwares;

public class Open_FormController {
    Entry entry;

    public Open_FormController(Entry entry) {
        this.entry = entry;
        // Constructor code, if needed
    }

        public void initialize() {
            // Code to populate the open form with the entry's data
            // For example:
            // cmp_name.setText(entry.getCompany_name());
            // job_title.setText(entry.getJob_title());
            // app_status.setValue(entry.getApp_status());
            // date_applied.setValue(entry.getDate_applied().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // intrvw.setSelected(entry.isInterview());
            // intrvw_date.setValue(entry.getInterview_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // resume_path.setText(entry.getResume_path());
            // cover_ltr_path.setText(entry.getCover_letter_path());
        }
}
