package il.ac.shenkar.showshenkar.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import il.ac.shenkar.showshenkar.backend.model.Content;
import il.ac.shenkar.showshenkar.backend.model.Department;
import il.ac.shenkar.showshenkar.backend.model.Info;
import il.ac.shenkar.showshenkar.backend.model.Location;
import il.ac.shenkar.showshenkar.backend.model.Media;
import il.ac.shenkar.showshenkar.backend.model.Project;
import il.ac.shenkar.showshenkar.backend.model.Route;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 *
 */
public class OfyService {

    static {
        ObjectifyService.register(RegistrationRecord.class);
        ObjectifyService.register(Content.class);
        ObjectifyService.register(Project.class);
        ObjectifyService.register(Department.class);
        ObjectifyService.register(Route.class);

    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
