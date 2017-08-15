package com.ubs.opsit.interviews;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class TimeConverterImplTest {
    private TimeConverter converter;

    @Before
    public void setUp() {
        converter = new TimeConverterImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyOrNullTimePassedForConversion() {
        converter.convertTime("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidTimePassedForConversion() {
        converter.convertTime("000:00");
    }

    @Test
    public void shouldShowTheTwoSecondLampAsOn() throws Exception {
        String timeRepresentation = converter.convertTime("00:00:00");
        assertNotNull(timeRepresentation);
        assertEquals("Y" , getLine(1, timeRepresentation));
    }

    @Test
    public void shouldShowTheTwoSecondLampAsOff() throws Exception {
        String timeRepresentation = converter.convertTime("00:00:01");
        assertEquals("O" , getLine(1, timeRepresentation));
    }

    @Test
    public void shouldShowTheHourFirstRowLampAsOff() throws Exception {
        String timeRepresentation = converter.convertTime("00:17:01");
        assertEquals("OOOO" , getLine(2, timeRepresentation));
    }

    @Test
    public void shouldShowTheHourFirstRowLampAsRed() throws Exception {
        String timeRepresentation = converter.convertTime("05:17:01");
        assertEquals("ROOO" , getLine(2, timeRepresentation));
    }

    @Test
    public void shouldShowTheHourSecondRowLampAsOff() throws Exception {
        String timeRepresentation = converter.convertTime("05:15:01");
        assertEquals("OOOO" , getLine(3, timeRepresentation));
    }

    @Test
    public void shouldShowTheHourSecondRowLampAsRed() throws Exception {
        String timeRepresentation = converter.convertTime("13:17:01");
        assertEquals("RRRO" , getLine(3, timeRepresentation));
    }

    @Test
    public void shouldShowTheMinutesFirstRowLampAsYellow() throws Exception {
        String timeRepresentation = converter.convertTime("00:05:01");
        assertEquals("YOOOOOOOOOO" , getLine(4, timeRepresentation));
    }

    @Test
    public void shouldShowTheMinutesFirstRowLampAsRedForThirdSixthNineColumn() throws Exception {
        String timeRepresentation = converter.convertTime("00:15:01");
        assertEquals("YYROOOOOOOO" , getLine(4, timeRepresentation));
    }

    @Test
    public void shouldShowTheMinutesSecondRowLampAsOff() throws Exception {
        String timeRepresentation = converter.convertTime("00:15:01");
        assertEquals("OOOO" , getLine(5, timeRepresentation));
    }

    @Test
    public void shouldShowTheMinutesSecondRowLampAsYellow() throws Exception {
        String timeRepresentation = converter.convertTime("00:17:01");
        assertEquals("YYOO" , getLine(5, timeRepresentation));
    }

    private String getLine(int number, String timeRepresentation) {
        String line = "";
        if(timeRepresentation == null && timeRepresentation.isEmpty()) {
            return line;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(timeRepresentation.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        int index = 1;
        try {
            while((line = reader.readLine()) != null) {
                    if(index == number) {
                        return line;
                    }
                    index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

}