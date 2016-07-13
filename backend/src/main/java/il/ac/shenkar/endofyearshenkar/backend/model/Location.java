package il.ac.shenkar.endofyearshenkar.backend.model;

import com.googlecode.objectify.annotation.Embed;

import java.io.Serializable;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Location entity class for app content
 */
@Embed
public class Location implements Serializable {
    String description;
    Double lat;
    Double lng;
    String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
