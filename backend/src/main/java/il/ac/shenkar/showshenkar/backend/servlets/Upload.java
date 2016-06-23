package il.ac.shenkar.showshenkar.backend.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import il.ac.shenkar.showshenkar.backend.OfyService;
import il.ac.shenkar.showshenkar.backend.model.Content;
import il.ac.shenkar.showshenkar.backend.model.Department;

public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
            String imageUrl = ImagesServiceFactory.getImagesService().getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)));
            String id = req.getParameter("id");
            String type = req.getParameter("type");
            if(!Objects.equals(type, "dep")){
                Content content = OfyService.ofy().load().type(Content.class).id(id).now();
                if(content != null){
                    content.getMedia().setUrl(imageUrl);
                    OfyService.ofy().save().entity(content).now();
                }
            }
            else{
                Department department = OfyService.ofy().load().type(Department.class).id(id).now();
                if(department != null){
                    department.setImageUrl(imageUrl);
                    OfyService.ofy().save().entity(department).now();
                }
            }
            res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
        }
    }
}
