package com.sturdy_softwares;

import java.util.Date;

public class Entry {
    
    private String company_name;
    private String job_title;
    private String app_status;
    private Date date_applied;
    private boolean interview;
    private Date interview_date;
    private boolean resume;
    private boolean cover_letter;
    

    public Entry(String company_name, String job_title, String app_status, Date date_applied, boolean interview, Date interview_date, boolean resume, boolean cover_letter) {
        this.company_name = company_name;
        this.job_title = job_title;
        this.app_status = app_status;
        this.date_applied = date_applied;
        this.interview = interview;
        this.interview_date = interview_date;
        this.resume = resume;
        this.cover_letter = cover_letter;
    }

    public String getCompany_name() {
        return company_name;
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
        this.company_name = company_name;
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

}
