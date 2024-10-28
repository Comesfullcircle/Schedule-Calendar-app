package org.calender.schedule_calender_app.reader;

import org.calender.schedule_calender_app.event.Meeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventCsvReaderTest {

    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    @Mock
    private RawCsvReader rawCsvReader;

    @InjectMocks
    private EventCsvReader sut;

    @Test
    public void reader() throws IOException {
        // given
        String path = "";

        List<String[]> mockData = new ArrayList<>();
        mockData.add(new String[8]);

        int mockSize = 5;
        for (int i = 0; i < mockSize; i++) {
            mockData.add(generateMock(i));
        }

        when(rawCsvReader.readAll(path)).thenReturn(mockData);

        // when
        List<Meeting> meetings = sut.readMeetings(path);

        // then
        assertEquals(mockSize, meetings.size());
        assertEquals("title0", meetings.get(0).getTitle());
    }

    private String[] generateMock(int id) {
        String[] mock = new String[8];
        mock[0] = String.valueOf(id);
        mock[1] = "MEETING" + id;
        mock[2] = "title" + id;
        mock[3] = "A,B,C" + id;
        mock[4] = "AI" + id;
        mock[5] = "test" + id;
        mock[6] = ZonedDateTime.now().plusHours(id).format(formatter);
        mock[7] = ZonedDateTime.now().plusHours(id + 1).format(formatter);

        return mock;
    }
}
