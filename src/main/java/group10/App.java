package group10;

/**
 * <code>App</code>
 * Holds the main function that runs the program.
 */
public class App 
{
    /**
     * Main function.
     * Starts the program.
     * @param args passed in arguments for running.
     */
    public static void main( String[] args )
    {
        Window window = new Window();
        Mainmenu m = new Mainmenu(window);
        window.add(m);
        window.pack();
        window.setLocationRelativeTo(null);

    }
}
