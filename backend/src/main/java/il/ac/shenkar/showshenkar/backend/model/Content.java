package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Base Content Entity class for app content
 */
@Entity
public class Content extends BaseEntity{

    String type;
    Boolean isMedia;
    Boolean isInfo;
    Boolean isLocation;
    Media media;
    Info info;
    Location location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsMedia() {
        return isMedia;
    }

    public void setIsMedia(Boolean isMedia) {
        this.isMedia = isMedia;
    }

    public Boolean getIsInfo() {
        return isInfo;
    }

    public void setIsInfo(Boolean isInfo) {
        this.isInfo = isInfo;
    }

    public Boolean getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(Boolean isLocation) {
        this.isLocation = isLocation;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
