package kz.edu.nu.cs.se.hw;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import javax.ws.rs.Path;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/services")
public class SearchApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public SearchApplication() {
        singletons.add(new SearchService());
    }


    public Set<Class<?>> getClasses() {
        return empty;
    }


    public Set<Object> getSingletons() {
        return singletons;
    }

}
