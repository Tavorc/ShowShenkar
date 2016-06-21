package il.ac.shenkar.showshenkar.backend.servlets;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.shenkar.showshenkar.backend.OfyService;
import il.ac.shenkar.showshenkar.backend.model.Content;
import il.ac.shenkar.showshenkar.backend.model.Department;
import il.ac.shenkar.showshenkar.backend.model.Info;
import il.ac.shenkar.showshenkar.backend.model.Location;
import il.ac.shenkar.showshenkar.backend.model.Media;
import il.ac.shenkar.showshenkar.backend.model.Project;
import il.ac.shenkar.showshenkar.backend.model.Route;

/**
 * Created by:  Gregory Kondratenko on 13/06/2016.
 * Description: Image save servlet
 */
public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Content content;
        ImagesService imagesService;
        String type = req.getParameter("type");
        String name;
        String description;
        String url;
        String infoText;
        String projDepartment;
        String studentNames;
        String studentEMails;
        String projectIds;
        String contentIds;
        Long lat;
        Long lng;

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");

        switch (type){
            case "LocationImg" :
                name = req.getParameter("name");
                lat = Long.valueOf(req.getParameter("lat"));
                lng = Long.valueOf(req.getParameter("lng"));
                description = req.getParameter("description");
                content = new Content();
                imagesService = ImagesServiceFactory.getImagesService();
                content.setType("location");
                content.setCreated(new Date());
                content.setModified(new Date());
                Location location = new Location();
                location.setUrl(imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0))));
                location.setDescription(description);
                location.setLat(lat);
                location.setLng(lng);
                location.setName(name);
                content.setLocation(location);
                OfyService.ofy().save().entity(content).now();
                break;
            case "Img" :
                name = req.getParameter("name");
                content = new Content();
                imagesService = ImagesServiceFactory.getImagesService();
                content.setType("image");
                content.setCreated(new Date());
                content.setModified(new Date());
                Media img = new Media();
                img.setName(name);
                img.setUrl(imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0))));
                content.setMedia(img);
                OfyService.ofy().save().entity(content).now();
                break;
            case "Video" :
                url = req.getParameter("url");
                name = req.getParameter("name");
                content = new Content();
                content.setType("video");
                content.setCreated(new Date());
                content.setModified(new Date());
                Media video = new Media();
                video.setName(name);
                video.setUrl(url);
                content.setMedia(video);
                OfyService.ofy().save().entity(content).now();
                break;
            case "Audio" :
                url = req.getParameter("url");
                name = req.getParameter("name");
                content = new Content();
                content.setType("audio");
                content.setCreated(new Date());
                content.setModified(new Date());
                Media audio = new Media();
                audio.setName(name);
                audio.setUrl(url);
                content.setMedia(audio);
                OfyService.ofy().save().entity(content).now();
                break;
            case "Info" :
                infoText = req.getParameter("infoText");
                content = new Content();
                content.setType("info");
                content.setCreated(new Date());
                content.setModified(new Date());
                Info info = new Info();
                info.setText(infoText);
                content.setInfo(info);
                OfyService.ofy().save().entity(content).now();
                break;
            case "Route" :
                name = req.getParameter("name");
                projectIds = req.getParameter("projectIds");
                Route route = new Route();
                route.setCreated(new Date());
                route.setModified(new Date());
                route.setName(name);
                List<String> projIds = new ArrayList<>(Arrays.asList(projectIds.split(",")));
                List<Project> projects = new ArrayList<>();
                for(String proj : projIds){
                    projects.add(OfyService.ofy().load().type(Project.class).id(proj).now());
                }
                route.setProjects(projects);
                OfyService.ofy().save().entity(route).now();
                break;
            case "Project" :
                name = req.getParameter("name");
                projDepartment = req.getParameter("department");
                studentNames = req.getParameter("studentNames");
                studentEMails = req.getParameter("studentEMails");
                contentIds = req.getParameter("contentIds");
                Project project = new Project();
                project.setCreated(new Date());
                project.setModified(new Date());
                project.setName(name);
                project.setDepartment(projDepartment);
                List<String> studNames = new ArrayList<>(Arrays.asList(studentNames.split(",")));
                List<String> studEmails = new ArrayList<>(Arrays.asList(studentEMails.split(",")));
                List<String> conIds = new ArrayList<>(Arrays.asList(contentIds.split(",")));
                project.setContentIds(conIds);
                project.setStudentNames(studNames);
                project.setStudentEMail(studEmails);
                OfyService.ofy().save().entity(project).now();
                break;
            case "Department" :
                name = req.getParameter("name");
                Department department = new Department();
                imagesService = ImagesServiceFactory.getImagesService();
                department.setCreated(new Date());
                department.setModified(new Date());
                department.setName(name);
                department.setImageUrl(imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0))));
                OfyService.ofy().save().entity(department).now();
                break;
            default:
                break;
        }

        res.sendRedirect("/");
//        if (blobKeys == null || blobKeys.isEmpty()) {
//            res.sendRedirect("/");
//        } else {
//            res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
//        }
    }
}