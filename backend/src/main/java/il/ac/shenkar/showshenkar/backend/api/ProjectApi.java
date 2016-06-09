package il.ac.shenkar.showshenkar.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.List;

import il.ac.shenkar.showshenkar.backend.model.Project;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Backend api for app projects
 */
@Api(
        name = "projectApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.shoeshenkar..shenkar.ac.il",
                ownerName = "backend.shoeshenkar..shenkar.ac.il",
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
        //TODO
        return null;
    }

    @ApiMethod(
            name = "getProject",
            path = "projectApi",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    List<Project> getProjects(){
        //TODO
        return null;
    }

    @ApiMethod(
            name = "setProject",
            path = "projectApi/{id}",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    Project setContent(@Named("id") String id){
        //TODO
        return null;
    }

}
