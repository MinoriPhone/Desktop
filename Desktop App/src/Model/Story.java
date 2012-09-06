package Model;

import java.util.ArrayList;

/**
 * A Story that the user is going to watch. This class holds all the different Routes an user can make.
 */
public class Story {
    
    // Variables
    private ArrayList<Route> routes;
    
    /**
     * Constructor
     */
    public Story() {
    }
    
    /**
     * Overload Constructor
     */
    public Story(ArrayList<Route> routes) {
        this.routes = routes;
    }
}
