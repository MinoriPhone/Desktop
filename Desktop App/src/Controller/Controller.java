package Controller;

/**
 * The Controller mediates input and converting it to commands for the Model or View
 */
public class Controller {

    // Variables
    private static Controller controller = null;

    /**
     * Overload the public Constructor
     */
    private Controller() {
    }

    /**
     * A synchronized creator to prevent multithreading problems
     */
    private synchronized static void createInstance() {
        if (controller == null) {
            controller = new Controller();
        }
    }

    /**
     * Get the one and only Controller
     */
    public static Controller getInstance() {
        if (controller == null) {
            createInstance();
        }
        return controller;
    }
}
