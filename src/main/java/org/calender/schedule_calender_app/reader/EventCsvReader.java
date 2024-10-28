package org.calender.schedule_calender_app.reader;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


import com.opencsv.CSVReader;
import org.calender.schedule_calender_app.event.Meeting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventCsvReader {

    public List<Meeting> readMeetings(String path) throws IOException {
        List<Meeting> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);
        for (int i = 0; i < read.size(); i++) {
            // 헤더는 스킵
            if (skipHeader(i)) {
                continue;
            }

            String[] each = read.get(i);

            result.add(
                    new Meeting(
                            Integer.parseInt(each[0]),                          // ID
                            each[2],                                            // 제목
                            ZonedDateTime.of(
                                    LocalDateTime.parse(each[6], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")
                            ),                                                  // 시작 시간
                            ZonedDateTime.of(
                                    LocalDateTime.parse(each[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                    ZoneId.of("Asia/Seoul")
                            ),                                                  // 종료 시간
                            new HashSet<>(Arrays.asList(each[3].replace("\"", "").split(","))), // 참여자
                            each[4],                                            // 회의실
                            each[5]                                             // 안건
                    )
            );

        }
        return result;
    }

    private static boolean skipHeader(int i) {
        return i == 0;
    }

    private List<String[]> readAll(String path) throws IOException {
        InputStream in = getClass().getResourceAsStream(path);
        if (in == null) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다: " + path);
        }

        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll();
        }
    }
}
