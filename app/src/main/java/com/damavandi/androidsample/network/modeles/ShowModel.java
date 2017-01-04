package com.damavandi.androidsample.network.modeles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by arch on 1/3/17.
 */

public class ShowModel implements Parcelable{

    private int id;
    private String url;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private String premiered;
    private RatingModel rating;
    private int weight;
    private NetworkModel network;
    private NetworkModel webChannel;
    private ExternalsModel externals;
    private ImageModel image;
    private String summary;
    private long updated;
    private LinksModel _links;

    public ShowModel() {
    }

    protected ShowModel(Parcel in) {
        id = in.readInt();
        url = in.readString();
        name = in.readString();
        type = in.readString();
        language = in.readString();
        genres = in.createStringArrayList();
        status = in.readString();
        runtime = in.readInt();
        premiered = in.readString();
        rating = in.readParcelable(RatingModel.class.getClassLoader());
        weight = in.readInt();
        network = in.readParcelable(NetworkModel.class.getClassLoader());
        webChannel = in.readParcelable(NetworkModel.class.getClassLoader());
        externals = in.readParcelable(ExternalsModel.class.getClassLoader());
        image = in.readParcelable(ImageModel.class.getClassLoader());
        summary = in.readString();
        updated = in.readLong();
        _links = in.readParcelable(LinksModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(language);
        dest.writeStringList(genres);
        dest.writeString(status);
        dest.writeInt(runtime);
        dest.writeString(premiered);
        dest.writeParcelable(rating, flags);
        dest.writeInt(weight);
        dest.writeParcelable(network, flags);
        dest.writeParcelable(webChannel, flags);
        dest.writeParcelable(externals, flags);
        dest.writeParcelable(image, flags);
        dest.writeString(summary);
        dest.writeLong(updated);
        dest.writeParcelable(_links, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShowModel> CREATOR = new Creator<ShowModel>() {
        @Override
        public ShowModel createFromParcel(Parcel in) {
            return new ShowModel(in);
        }

        @Override
        public ShowModel[] newArray(int size) {
            return new ShowModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public RatingModel getRating() {
        return rating;
    }

    public int getWeight() {
        return weight;
    }

    public NetworkModel getNetwork() {
        return network;
    }

    public NetworkModel getWebChannel() {
        return webChannel;
    }

    public ExternalsModel getExternals() {
        return externals;
    }

    public ImageModel getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public long getUpdated() {
        return updated;
    }

    public LinksModel get_links() {
        return _links;
    }
}
