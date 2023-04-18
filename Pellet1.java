
public class Pellet1 extends GameObject
{
    int x,y;
    boolean eat;
    public Pellet1(int xx, int yy)
    {
        super(xx,yy);
        eat=false;
    }
    public boolean geteat()
    {
        return eat;
    }
    public void pelleteat()
    {
        eat=true;
    }   
}
