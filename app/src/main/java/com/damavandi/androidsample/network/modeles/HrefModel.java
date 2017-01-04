package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class HrefModel implements Parcelable{

    private String href;

    protected HrefModel(Parcel in) {
        href = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HrefModel> CREATOR = new Creator<HrefModel>() {
        @Override
        public HrefModel createFromParcel(Parcel in) {
            return new HrefModel(in);
        }

        @Override
        public HrefModel[] newArray(int size) {
            return new HrefModel[size];
        }
    };
}
