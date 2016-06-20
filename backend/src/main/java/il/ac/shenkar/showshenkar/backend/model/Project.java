package il.ac.shenkar.showshenkar.backend.model;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Projects entity class
 */
@Entity
@Cache(expirationSeconds=600)
public class Project extends BaseEntity {

    String name;
    @Index
    String department;
    List<String> studentNames;
    List<String> studentEMail;
    List<String> contentIds;

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

    public List<String> getStudentEMail() {
        return studentEMail;
    }

    public void setStudentEMail(List<String> studentEMail) {
        this.studentEMail = studentEMail;
    }

    public List<String> getContentIds() {
        return contentIds;
    }

    public void setContentIds(List<String> contentIds) {
        this.contentIds = contentIds;
    }
}
