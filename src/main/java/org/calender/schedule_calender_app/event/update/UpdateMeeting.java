package org.calender.schedule_calender_app.event.update;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdateMeeting extends AbstractAuditableEvent{

    private final Set<String> participatns;
    private final String meetingRoom;
    private final String agenda;

    public UpdateMeeting(String title, ZonedDateTime startAt, ZonedDateTime endAt,
                            Set<String> participatns, String meetingRoom, String agenda) {
        super(title, startAt, endAt);

        this.participatns = participatns;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    public Set<String> getParticipatns() {return participatns;}
    public String getMeetingRoom() {return meetingRoom;}
    public String getAgenda() {return agenda;}

}
