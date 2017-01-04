package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class ExternalsModel implements Parcelable {

    private int tvrage;
    private int thetvdb;
    private String imdb;

    protected ExternalsModel(Parcel in) {
        tvrage = in.readInt();
        thetvdb = in.readInt();
        imdb = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tvrage);
        dest.writeInt(thetvdb);
        dest.writeString(imdb);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExternalsModel> CREATOR = new Creator<ExternalsModel>() {
        @Override
        public ExternalsModel createFromParcel(Parcel in) {
            return new ExternalsModel(in);
        }

        @Override
        public ExternalsModel[] newArray(int size) {
            return new ExternalsModel[size];
        }
    };

    public int getTvrage() {
        return tvrage;
    }

    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    public int getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
