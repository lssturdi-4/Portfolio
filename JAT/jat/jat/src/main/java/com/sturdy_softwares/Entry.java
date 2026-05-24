package com.sturdy_softwares;

import java.util.Date;

public class Entry {
    
    private int id;
    private String cmp_name;
    private String job_title;
    private String app_status;
    private Date date_applied;
    private boolean interview;
    private Date interview_date;
    private boolean resume;
    private String resume_path;
    private boolean cover_letter;
    private String cover_letter_path;
    private String description;
    private String rate;
    private String work_loc;
    private double amount;
    

    public Entry(String cmp_name, String job_title, String app_status, boolean interview, boolean resume, boolean cover_letter) {
        this.id = Main_DisplayController.idGenerator.generateId();
        this.cmp_name = cmp_name;
        this.job_title = job_title;
        this.app_status = app_status;
        this.interview = interview;
        this.resume = resume;
        this.cover_letter = cover_letter;
    }

    public Entry() {
        this.id = Main_DisplayController.idGenerator.generateId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return cmp_name;
    }
    
    public String getJob_title() {
        return job_title;
    }

    public String getApp_status() {
        return app_status;
    }

    public Date getDate_applied() {
        return date_applied;
    }

    public boolean isInterview() {
        return interview;
    }

    public Date getInterview_date() {
        return interview_date;
    }

    public void setCompany_name(String company_name) {
        this.cmp_name = company_name;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
    }

    public void setDate_applied(Date date_applied) {
        this.date_applied = date_applied;
    }

    public void setInterview(boolean interview) {
        this.interview = interview;
    }

    public void setInterview_date(Date interview_date) {
        this.interview_date = interview_date;
    }

    public boolean isResume() {
        return resume;
    }

    public void setResume(boolean resume) {
        this.resume = resume;
    }

    public boolean isCover_letter() {
        return cover_letter;
    }

    public void setCover_letter(boolean cover_letter) {
        this.cover_letter = cover_letter;
    }

    public String getResume_path() {
        return resume_path;
    }

    public void setResume_path(String resume_path) {
        this.resume_path = resume_path;
    }

    public String getCover_letter_path() {
        return cover_letter_path;
    }

    public void setCover_letter_path(String cover_letter_path) {
        this.cover_letter_path = cover_letter_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWork_loc() {
        return work_loc;
    }

    public void setWork_loc(String work_loc) {
        this.work_loc = work_loc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
