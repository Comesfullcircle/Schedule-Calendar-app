package org.calender.schedule_calender_app;

import org.calender.schedule_calender_app.event.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class CalenderAppApplication {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();

        //List<AbstractEvent> list = new ArrayList<>();
        HashSet<String> participants = new HashSet<>();
        participants.add("seol");

        Meeting meeting1 = new Meeting(
                1, "meeting1",
                ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
                participants,"meetingRoomA","코딩공부"
        );
        schedule.add(meeting1);

        Todo todo1 = new Todo(
                2, "todo1",
                ZonedDateTime.now().plusHours(3), ZonedDateTime.now().plusHours(4),
                "코딩테스트 연습"
        );
        schedule.add(todo1);

        Todo todo2 = new Todo(
                3, "todo2",
                ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(6),
                "개인 사이드프로젝트 만들기"
        );
        schedule.add(todo2);

        //schedule.printBy(EventType.TO_DO);
        schedule.printAll();
    }
}
