package il.ac.shenkar.showshenkar.backend.model;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Location entity class for app content
 */
public class Location {
    String name;
    String url;

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
}
