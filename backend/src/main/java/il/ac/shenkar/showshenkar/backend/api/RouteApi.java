package il.ac.shenkar.showshenkar.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import il.ac.shenkar.showshenkar.backend.OfyService;
import il.ac.shenkar.showshenkar.backend.model.Department;
import il.ac.shenkar.showshenkar.backend.model.Route;

/**
 * Created by:  Gregory Kondratenko on 11/06/2016.
 * Description: Backend api for app projects
 */
@Api(
        name = "routeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.showshenkar.shenkar.ac.il",
                ownerName = "backend.showshenkar.shenkar.ac.il",
                packagePath=""
        )
)
public class RouteApi {

    @ApiMethod(
            name = "getRoute",
            path = "routeApi/{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Route getRoute(@Named("id") Long id){
        return OfyService.ofy().load().type(Route.class).id(id).now();
    }

    @ApiMethod(
            name = "getRoutes",
            path = "routeApi",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<Route> getRoutes(){
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        String key = "getRoutes";

        if (syncCache.contains(key)){
            return (List<Route>) syncCache.get(key);
        }

        List<Route> routes = OfyService.ofy().load().type(Route.class).list();

        Expiration expiration =  Expiration.byDeltaSeconds((int) TimeUnit.HOURS.toSeconds(3));
        syncCache.put(key,routes,expiration);


        return routes;
    }

    @ApiMethod(
            name = "setRoute",
            path = "routeApi",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public Route setRoute(Route route){
        if (route == null){
            throw new IllegalStateException("Route is null");
        }

        if (route.getId() != null) {
            throw new IllegalStateException("Route already exits");
        }

        OfyService.ofy().save().entity(route).now();
        return route;
    }
}
