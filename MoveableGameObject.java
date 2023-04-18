
public class MoveableGameObject extends GameObject
{
    
    public MoveableGameObject(double xx, double yy)
    {
       super(xx,yy);
    }
 
   public void moveIt(int xdir, int ydir, double speed)
   {
       x = x + (xdir*speed);
       y = y + (ydir*speed);
    }   
    
}
