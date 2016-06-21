package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Embed;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Info entity class for app content
 */
@Embed
public class Info {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
