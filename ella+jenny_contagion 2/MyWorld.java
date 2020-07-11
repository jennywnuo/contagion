import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The world in which the people will move. 
 * 
 * @author Ella Deguzman
 * @version 1.0
 */
public class MyWorld extends World
{
    private static final int NUMBER_OF_PEOPLE = 200;
    private static final int PERCENT_ISOLATING = 50;

    private static final int SCALE_Y = 3;
    private static final int WORLD_WIDTH = 1000;
    private static final int WORLD_HEIGHT = 600;
    private int maxInfected = 0;
    private int xOffset = 0;
    
    /**
     * Create the world and populate it with people. 
     * 
     */
    public MyWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        populate(PERCENT_ISOLATING);
    }
    /**
     * Do the action for every act step. 
     */
    public void act()
    {
        displayInfo();
    }
    
    /**
     * Display the statistics of the population.
     */
    private void displayInfo()
    {
        int numInfected = 0;
        
        if (Person.getNumberInfected() > maxInfected) {
            maxInfected = Person.getNumberInfected();
        }
        
        showText("Infected: " + Person.getNumberInfected(), 100, 20);
        showText("Max: " + maxInfected, 300, 20);
        
        plotValue(Person.getNumberInfected());
    }
    /**
     * Plot a single value by drawing on the world background. 
     */
    private void plotValue(int value)
    {
        int yOffset = WORLD_HEIGHT - value * SCALE_Y; 
        getBackground().drawLine(xOffset, yOffset, xOffset, yOffset-4);
        
        xOffset++;
    }
    
    /**
     * Populate the world with all the initial actors. 
     */
    public void populate(int percentIsolating)
    {   
        removeObjects(getObjects(Person.class));
        Person.reset();
        
        Person person = new Person(false);
        person.infect();
        addObject(person, 3*WORLD_WIDTH/4 , WORLD_HEIGHT/2);
        for (int i=0; i<NUMBER_OF_PEOPLE; i++) {
            int x = Greenfoot.getRandomNumber(490);
            int y = Greenfoot.getRandomNumber(getHeight());

            boolean isolate = Greenfoot.getRandomNumber(100) < percentIsolating;
            
            addObject(new Person(isolate), x, y);
        }
        showText("Isolating: " + percentIsolating + "%", 700, 20);
    }
    
}
