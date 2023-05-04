import greenfoot.*;

/**
 * Write a description of class Flappy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flappy extends Actor
{
    double dy = 0;
    double g = .3;
    final double BOOST_SPEED = -4;
    
    GreenfootImage image1 = null;
    GreenfootImage image2 = null;
    GreenfootImage image3 = null;
    GreenfootImage image4 = null;
    int frame = 1;
    int animationCounter = 0;
    final int ANIMATION_SPEED = 1;
    
    public Flappy()
    {
         image1 = new GreenfootImage("flappybird1.png");
         image2 = new GreenfootImage("flappybird2.png");
         image3 = new GreenfootImage("flappybird3.png");
         image4 = image2;
    }
    
    public void act() 
    {
       
        if(getOneIntersectingObject(Pipe.class) != null)
        {
            displayGameOver();
        }
        
        setLocation(getX(), (int)(getY() + dy));
        
        rotateFlappyBird();
        
        
        if(Greenfoot.isKeyDown("up") == true)    // "up".equals(Greenfoot.getKey())
        {
            dy = BOOST_SPEED;
            animateFlappy();
        }
       
        if(getY() > getWorld().getHeight())
        {
            displayGameOver();
        }
        
        dy = dy + g;
    
    }    
    
    public void animateFlappy()
    {
        animationCounter++;
        if(animationCounter % ANIMATION_SPEED == 0)
        {
            if(frame == 1)
            {
                setImage(image1);
                frame = 2;
            }
            else if(frame == 2)
            {
                setImage(image2);
                frame = 3;
            }
            else if(frame == 3)
            {
                setImage(image3);
                frame = 4;
            }
            else if(frame == 4)
            {
                setImage(image4);
                frame = 1;
            }
        }
    }
    
    public void rotateFlappyBird()
    {
        if(dy < -6)
        {
            setRotation(300);
        }
        else if(dy >= -6 && dy < -2)
        {
            setRotation(320);
        }
        else if(dy >= -2 && dy <= 2)
        {
            setRotation(0);
        }
        else if(dy > 2 && dy <= 6)
        {
            setRotation(40);
        }
        else if(dy > 6)
        {
            setRotation(80);
        }
    }
    
    public void displayGameOver()
    {
        GameOver gameOver = new GameOver();
        getWorld().addObject(gameOver, getWorld().getWidth()/2, getWorld().getHeight()/2);
        Greenfoot.stop();
    }
}
