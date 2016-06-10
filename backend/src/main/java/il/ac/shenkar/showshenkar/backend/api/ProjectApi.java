package il.ac.shenkar.showshenkar.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.List;

import il.ac.shenkar.showshenkar.backend.OfyService;
import il.ac.shenkar.showshenkar.backend.model.Project;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Backend api for app projects
 */
@Api(
        name = "projectApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.showshenkar.shenkar.ac.il",
                ownerName = "backend.showshenkar.shenkar.ac.il",
                packagePath=""
        )
)
public class ProjectApi {

    @ApiMethod(
            name = "getProject",
            path = "projectApi/{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    Project getProject(@Named("id") String id){
        return OfyService.ofy().load().type(Project.class).filter("id", id).first().now();
    }

    @ApiMethod(
            name = "getProjects",
            path = "projectApi",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    List<Project> getProjects(){
        return OfyService.ofy().load().type(Project.class).list();
    }

    @ApiMethod(
            name = "setProject",
            path = "projectApi",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    Project setProject(Project project){
        if (project == null){
            throw new IllegalStateException("Project is null");
        }

        if (project.getId() != null) {
            throw new IllegalStateException("Project already exits");
        }

        OfyService.ofy().save().entity(project).now();
        return project;
    }

    @ApiMethod(
            name = "deleteProject",
            path = "projectApi/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE
    )
    Project deleteProject(@Named("id") String id){
        //TODO: update stub
        return null;
    }

    @ApiMethod(
            name = "updateProject",
            path = "projectApi/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT
    )
    Project updateProject(@Named("id") String id){
        //TODO: update stub
        return null;
    }

}
