import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A person in a virus simulation. 
 * 
 * @author Ella DeGuzman
 * @version 1.0
 */
public class Person extends Actor
{
    private static final int INFECTION_TIME = 200;
    
    private int infection = 0;
    private boolean isImmune = false;
    private boolean isIsolating; 
    
    private static int numInfected = 0;
    
    /**
     * Return the number of infected persons 
     */
    public static int getNumberInfected ()
    {
        return numInfected;
    }
    
    /** 
     * Reset the Person class to be used again for another run.
     */public static void reset()
    {
        numInfected = 0;
    }
    
    /**
     * Create a person with random direction of movement. 
     */
    public Person(boolean isolating)
    {
        turn(Greenfoot.getRandomNumber(360));
        isIsolating = isolating; 
    }
        
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!isIsolating) {
            move(); 
            if (getX()<500) {
                setLocation(500, getY());
                turn(87);
            }
        }
        if (isInfected()) {
          infectOthers();
          heal();
        }
    }
    
    /**
     * Move the person randomly on screen.
     */
    private void move()
    {
        move(4);
        if (isAtEdge()) {
            turn(87);
        }
        if (Greenfoot.getRandomNumber(100) < 20) {
            turn(Greenfoot.getRandomNumber(61)-30);
        }
    }
    
    /** 
     * Check whether we are touching anther person. If we are, infect them.
     */
    private void infectOthers()
    {
        Person other = (Person) getOneIntersectingObject(Person.class);
        
        if (other != null) {
            other.infect();
        }
    }
    /**
     * Try to infect this person. If we are not immune or infected already, we will be infected now. 
     */
    public void infect()
    {
        if (!isImmune) {
            infection = INFECTION_TIME;
            numInfected++;
            setImage("infected.png");
            isImmune = true;
        }
    }
    
    /**
     * Makes infection time a value greater than 0 if the person is infected. 
     */
    public boolean isInfected()
    {
        return infection > 0; 
    }
  
    /**
     * If we are infected, execute the healing process. If infection reaches zero, we are immune.
     */
    private void heal()
    {
        if (isInfected()) {
            infection--;
            if (infection == 0) {
                setImage("immune.png");
                numInfected--;
            }
        }
    }
}
