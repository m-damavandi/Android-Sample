package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class RatingModel implements Parcelable{

    private float average;

    protected RatingModel(Parcel in) {
        average = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(average);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RatingModel> CREATOR = new Creator<RatingModel>() {
        @Override
        public RatingModel createFromParcel(Parcel in) {
            return new RatingModel(in);
        }

        @Override
        public RatingModel[] newArray(int size) {
            return new RatingModel[size];
        }
    };

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
