package com.damavandi.androidsample.network.modeles;

import java.util.List;

/**
 * Created by arch on 1/3/17.
 */

public class ShowModel {

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
