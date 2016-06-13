package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Base Content Entity class for app content
 */
@Entity
@Cache(expirationSeconds=600)
public class Content extends BaseEntity{

    String type;
    Media media;
    Info info;
    Location location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
