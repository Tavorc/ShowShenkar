package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Base Entity class for app content
 */
public class BaseEntity {

    @Id
    Long id;
    @Index
    Date created;
    @Index
    Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
