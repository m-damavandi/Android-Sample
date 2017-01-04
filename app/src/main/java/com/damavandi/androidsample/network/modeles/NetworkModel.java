package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arch on 1/3/17.
 */

public class NetworkModel implements Parcelable{

    private int id;
    private String name;
    private CountryModel country;

    protected NetworkModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        country = in.readParcelable(CountryModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(country, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NetworkModel> CREATOR = new Creator<NetworkModel>() {
        @Override
        public NetworkModel createFromParcel(Parcel in) {
            return new NetworkModel(in);
        }

        @Override
        public NetworkModel[] newArray(int size) {
            return new NetworkModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }
}
