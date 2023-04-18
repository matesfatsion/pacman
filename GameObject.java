public class GameObject
{
      double x,y;
      
    public GameObject(double xx, double yy)
     {
      x = xx;
      y = yy;
     }

    public int getx()
     {
      return (int)x;  
     }
    
    public int gety()
     {
      return (int)y; 
     } 
     
    public void moveIt(int xdir, int ydir, double speed)
   {
       x = x + (xdir*speed);
       y = y + (ydir*speed);
    }     
    
}