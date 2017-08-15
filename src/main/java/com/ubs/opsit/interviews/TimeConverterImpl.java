package com.ubs.opsit.interviews;

import java.security.InvalidParameterException;

public class TimeConverterImpl implements TimeConverter {
    private static final int HOUR_COUNT = 5;
    private static final int MINUTE_COUNT = 5;

    private TimeRepresentation representation;

    public TimeConverterImpl() {
        this.representation = new TimeRepresentation();
    }

    @Override
    public String convertTime(String aTime) {
        validateTime(aTime);

        String[] timeRepresentation = aTime.split(":");

        StringBuffer convertedTime = new StringBuffer();
        convertedTime.append(getSecondsRepresentation(Integer.parseInt(timeRepresentation[2])));
        convertedTime.append("\n");
        convertedTime.append(getHourRepresentation(Integer.parseInt(timeRepresentation[0])));
        convertedTime.append("\n");
        convertedTime.append(getMinuteRepresentation(Integer.parseInt(timeRepresentation[1])));

        return convertedTime.toString();
    }

    private void validateTime(String aTime) {
        if(aTime == null || aTime.isEmpty()) {
            throw new IllegalArgumentException("Please provide value for time");
        }

        String[] timeRepresentation = aTime.split(":");
        if (timeRepresentation.length != 3 || isInvalidHour(timeRepresentation[0]) || isInvalidMinute(timeRepresentation[1]) || isInvalidSecond(timeRepresentation[2])) {
            throw new IllegalArgumentException("Please provide valid value for time");
        }
    }

    private boolean isInvalidHour(String hour) {
        int hourVal = Integer.parseInt(hour);
        if(hourVal < 0 || hourVal > 24) {
            return true;
        }
        return false;
    }

    private boolean isInvalidMinute(String minute) {
        int minuteVal = Integer.parseInt(minute);
        if(minuteVal < 0 || minuteVal > 60) {
            return true;
        }
        return false;
    }

    private boolean isInvalidSecond(String second) {
        int secondVal = Integer.parseInt(second);
        if(secondVal < 0 || secondVal > 60) {
            return true;
        }
        return false;
    }

    private String getHourRepresentation(int hour) {
        int numberOfRedLampForFirstRow = hour / HOUR_COUNT;
        int numberOfYellowLampForSecondRow = hour % HOUR_COUNT;
        return representation.getHourRepresentation(numberOfRedLampForFirstRow, numberOfYellowLampForSecondRow);
    }

    private String getMinuteRepresentation(int minutes) {
        int numberOfYellowLampForFirstRow = minutes / MINUTE_COUNT;
        int numberOfYellowLampForSecondRow = minutes % MINUTE_COUNT;

        return representation.getMinuteRepresentation(numberOfYellowLampForFirstRow, numberOfYellowLampForSecondRow);
    }

    private String getSecondsRepresentation(int seconds) {
        return representation.getSecondsRepresentation(seconds);
    }
}
