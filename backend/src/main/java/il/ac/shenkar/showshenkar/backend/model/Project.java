package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Projects entity class
 */
@Entity
public class Project extends BaseEntity {

    @Index
    String name;
    @Index
    String department;
    List<String> studentNames;
    Content location;
    List<Content> content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }

    public Content getLocation() {
        return location;
    }

    public void setLocation(Content location) {
        this.location = location;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
