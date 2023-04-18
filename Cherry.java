public class Cherry extends GameObject
{
    int x,y,count;
    boolean eat,exists;
    public Cherry(int xx, int yy)
    {
        super(xx,yy);        
    }
    public boolean geteat()
    {
        return eat;
    }
    public boolean getexists()
    {
        return exists;
    } 
    public void exists()
    {
        exists=true;
    } 
    public void doesntexists()
    {
        exists=false;
    } 
    /**public void cherryeat()
    {
        eat=true;
    }   
    public void cherrynoteat()
    {
        eat=false;
    } 
    public void updatecount(int counter)
    {
        count=counter;
    } */
    /**public void cherryexists()
    {       
       if(count>=0){
        exists=true;
        }else{
        exists=false;   
        }
    }   
    */
}