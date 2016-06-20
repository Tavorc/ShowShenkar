package il.ac.shenkar.showshenkar.backend.model;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Media entity class for app content
 */
public class Media {
    String name;
    String url;
    String imageKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
