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
import il.ac.shenkar.showshenkar.backend.model.Project;

/**
 * Created by:  Gregory Kondratenko on 10/06/2016.
 * Description: Backend api for app projects
 */
@Api(
        name = "departmentApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.showshenkar.shenkar.ac.il",
                ownerName = "backend.showshenkar.shenkar.ac.il",
                packagePath=""
        )
)
public class DepartmentApi {

    @ApiMethod(
            name = "getDepartment",
            path = "departmentApi/{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Department getDepartment(@Named("id") Long id){
        return OfyService.ofy().load().type(Department.class).id(id).now();
    }

    @ApiMethod(
            name = "getDepartments",
            path = "departmentApi",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<Department> getDepartments(){
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        String key = "getDepartments";

        if (syncCache.contains(key)){
            return (List<Department>) syncCache.get(key);
        }

        List<Department> departments =  OfyService.ofy().load().type(Department.class).list();

        Expiration expiration =  Expiration.byDeltaSeconds((int) TimeUnit.HOURS.toSeconds(3));
        syncCache.put(key,departments,expiration);

        return departments;
    }

    @ApiMethod(
            name = "setDepartment",
            path = "departmentApi",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public Department setDepartment(Department department){
        if (department == null){
            throw new IllegalStateException("Department is null");
        }

        if (department.getName() != null) {
            throw new IllegalStateException("Department already exits");
        }

        OfyService.ofy().save().entity(department).now();
        return department;
    }

}
