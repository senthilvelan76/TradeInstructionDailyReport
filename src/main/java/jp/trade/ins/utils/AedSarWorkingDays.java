package jp.trade.ins.utils;

import java.time.DayOfWeek;

public class AedSarWorkingDays extends WorkingDays {

    private static AedSarWorkingDays instance = null;

    public static AedSarWorkingDays getInstance() {
        if (instance == null) {
            instance = new AedSarWorkingDays();
        }
        return instance;
    }

    private AedSarWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false); // in arabia those are not working days
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false); // in arabia those are not working days
    }
}
