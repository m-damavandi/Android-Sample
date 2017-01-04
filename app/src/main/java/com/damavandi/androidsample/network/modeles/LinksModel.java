package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class LinksModel implements Parcelable{
    private HrefModel self;
    private HrefModel previousepisode;

    protected LinksModel(Parcel in) {
        self = in.readParcelable(HrefModel.class.getClassLoader());
        previousepisode = in.readParcelable(HrefModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(self, flags);
        dest.writeParcelable(previousepisode, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinksModel> CREATOR = new Creator<LinksModel>() {
        @Override
        public LinksModel createFromParcel(Parcel in) {
            return new LinksModel(in);
        }

        @Override
        public LinksModel[] newArray(int size) {
            return new LinksModel[size];
        }
    };
}
