package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Entity;

import java.util.List;

/**
 * Created by:  Gregory Kondratenko on 11/06/2016.
 * Description: Route entity class
 */
@Entity
public class Route extends BaseEntity {
    String name;
    List<Project> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
