package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class ImageModel implements Parcelable{

    private String medium;
    private String original;

    protected ImageModel(Parcel in) {
        medium = in.readString();
        original = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(medium);
        dest.writeString(original);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
