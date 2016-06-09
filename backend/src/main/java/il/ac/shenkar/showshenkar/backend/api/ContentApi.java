package il.ac.shenkar.showshenkar.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import il.ac.shenkar.showshenkar.backend.model.Content;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Backend api for app content
 */
@Api(
        name = "contentApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.shoeshenkar..shenkar.ac.il",
                ownerName = "backend.shoeshenkar..shenkar.ac.il",
                packagePath=""
        )
)
public class ContentApi {

    @ApiMethod(
            name = "getContent",
            path = "contentApi/{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    Content getContent(@Named("id") String id){
        //TODO
        return null;
    }

    @ApiMethod(
            name = "SetContent",
            path = "contentApi/{id}/{type}",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    Content setContent(@Named("id") String id, @Named("type") String type){
        //TODO
        return null;
    }

}
