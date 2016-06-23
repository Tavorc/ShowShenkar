package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Embed;

import java.io.Serializable;


/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Media entity class for app content
 */
@Embed
public class Media implements Serializable {
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
