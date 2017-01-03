package com.damavandi.androidsample.network.modeles;

import java.util.List;

/**
 * Created by arch on 1/3/17.
 */

public class ScheduleModel {

    private String time;
    private List<String> days;

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
