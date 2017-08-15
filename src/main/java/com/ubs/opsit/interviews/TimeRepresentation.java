package com.ubs.opsit.interviews;

public class TimeRepresentation {
    private static final String LAMP_OFF = "O";
    private static final String LAMP_YELLOW = "Y";
    private static final String LAMP_RED = "R";
    private static final int LAMP_COUNT_FOUR = 4;
    private static final int LAMP_COUNT_ELEVEN = 11;
    public static final int MINUTE_FIRST_QUARTER_COUNT = 3;
    public static final int MINUTE_HALF_COUNT = 6;
    public static final int MINUTE_LAST_QUARTER_COUNT = 9;
    private static final int SECOND_COUNT = 2;

    public String getHourRepresentation(int numberOfRedLampForFirstRow, int numberOfYellowLampForSecondRow) {
        StringBuffer hourRepresentation = new StringBuffer();
        hourRepresentation.append(getRepresentationFor(numberOfRedLampForFirstRow, LAMP_RED));
        hourRepresentation.append("\n");
        hourRepresentation.append(getRepresentationFor(numberOfYellowLampForSecondRow, LAMP_RED));
        return hourRepresentation.toString();
    }

    public String getMinuteRepresentation(int numberOfYellowLampForFirstRow, int numberOfYellowLampForSecondRow) {
        StringBuffer minuteRepresentation = new StringBuffer();
        for (int i = 1; i <= LAMP_COUNT_ELEVEN; i++) {
            if(i <= numberOfYellowLampForFirstRow) {
                switchOnFirstMinuteRowAsRedOrYellow(minuteRepresentation, i);
            } else {
                minuteRepresentation.append(LAMP_OFF);
            }
        }

        minuteRepresentation.append("\n");

        minuteRepresentation.append(getRepresentationFor(numberOfYellowLampForSecondRow, LAMP_YELLOW));
        return minuteRepresentation.toString();
    }

    public String getSecondsRepresentation(int seconds) {
        String secondsRepresentation = LAMP_OFF;
        if(seconds % SECOND_COUNT == 0) {
            secondsRepresentation = LAMP_YELLOW;
        }
        return secondsRepresentation;
    }

    private String getRepresentationFor(int numberOfRedLampForFirstRow, String lampOnType) {
        StringBuffer representation = new StringBuffer();
        for (int i = 1; i <= LAMP_COUNT_FOUR; i++) {
            if(i <= numberOfRedLampForFirstRow) {
                representation.append(lampOnType);
            } else {
                representation.append(LAMP_OFF);
            }
        }
        return representation.toString();
    }

    private void switchOnFirstMinuteRowAsRedOrYellow(StringBuffer minuteRepresentation, int i) {
        if (i % MINUTE_FIRST_QUARTER_COUNT == 0 || i % MINUTE_HALF_COUNT == 0 || i % MINUTE_LAST_QUARTER_COUNT == 0) {
            minuteRepresentation.append(LAMP_RED);
        } else {
            minuteRepresentation.append(LAMP_YELLOW);
        }
    }
}
