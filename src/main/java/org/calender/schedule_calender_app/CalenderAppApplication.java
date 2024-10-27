package org.calender.schedule_calender_app;

import org.calender.schedule_calender_app.event.*;
import org.calender.schedule_calender_app.reader.EventCsvReader;


import java.io.IOException;
import java.util.List;

public class CalenderAppApplication {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        EventCsvReader csvReader = new EventCsvReader();
        String meetingCsvPath = "/data/meeting.csv";

        try {
            List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
            meetings.forEach(schedule::add);
        } catch (IOException e) {
            System.out.println("CSV 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }

        schedule.printAll();
    }
}