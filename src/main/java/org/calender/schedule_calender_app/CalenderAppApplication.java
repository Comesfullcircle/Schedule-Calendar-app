package org.calender.schedule_calender_app;

import org.calender.schedule_calender_app.event.*;
import org.calender.schedule_calender_app.reader.EventCsvReader;


import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public class CalenderAppApplication {

    public static void main(String[] args) throws IOException {
        Schedule schedule = new Schedule();
        EventCsvReader csvReader = new EventCsvReader();
        String meetingCsvPath = "/data/meeting.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);

        schedule.printAll();

       /* try {
            List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
            meetings.forEach(schedule::add);

            Meeting meeting = meetings.get(0);
            meeting.print();

            System.out.println("수정 후 ... ");
            meetings.get(0).validateAndUpdate(
                    new UpdateMeeting(
                            "new title",
                            ZonedDateTime.now(),
                            ZonedDateTime.now().plusHours(1),
                            null,
                            "A",
                            "new agenda"
                    )
            );
            meeting.print();

            schedule.printAll();

        } catch (IOException e) {
            System.out.println("CSV 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }

        schedule.printAll();*/
    }
}