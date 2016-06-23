package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Department entity class
 */
@Entity
public class Department extends BaseEntity {
    @Index
    String name;
    String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
