package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by arch on 1/3/17.
 */

public class ScheduleModel implements Parcelable{

    private String time;
    private List<String> days;

    protected ScheduleModel(Parcel in) {
        time = in.readString();
        days = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeStringList(days);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleModel> CREATOR = new Creator<ScheduleModel>() {
        @Override
        public ScheduleModel createFromParcel(Parcel in) {
            return new ScheduleModel(in);
        }

        @Override
        public ScheduleModel[] newArray(int size) {
            return new ScheduleModel[size];
        }
    };

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
