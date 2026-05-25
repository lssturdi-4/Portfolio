package com.sturdy_softwares;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class EntryTest {

    @Test
    public void gettersAndSetters_workAsExpected() {
        Entry e = new Entry();
        e.setCompany_name("Acme Corp");
        e.setJob_title("Engineer");
        e.setApp_status("Applied");
        Date now = new Date();
        e.setDate_applied(now);
        e.setInterview(true);
        e.setInterview_date(now);
        e.setResume(true);
        e.setCover_letter(true);
        e.setResume_path("/tmp/resume.pdf");
        e.setCover_letter_path("/tmp/cover.pdf");
        e.setDescription("Job description");
        e.setRate("Hourly");
        e.setWork_loc("Remote");
        e.setAmount(1234.56);

        assertEquals("Acme Corp", e.getCompany_name());
        assertEquals("Engineer", e.getJob_title());
        assertEquals("Applied", e.getApp_status());
        assertEquals(now, e.getDate_applied());
        assertTrue(e.isInterview());
        assertEquals(now, e.getInterview_date());
        assertTrue(e.isResume());
        assertTrue(e.isCover_letter());
        assertEquals("/tmp/resume.pdf", e.getResume_path());
        assertEquals("/tmp/cover.pdf", e.getCover_letter_path());
        assertEquals("Job description", e.getDescription());
        assertEquals("Hourly", e.getRate());
        assertEquals("Remote", e.getWork_loc());
        assertEquals(1234.56, e.getAmount(), 0.0001);
    }
}
