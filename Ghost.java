
public class Ghost extends MoveableGameObject
{
    int ghoststeps,count1,numbertime;
    double xdir,ydir, speed;
    boolean caneat, dideat,canmove,isanumber;
    String dd;
    /** Recives Location of Ghosts*/
    public Ghost(int xx, int yy)
    {
        super(xx,yy);
        dd="left";
    }
    public int getghosteps()
    {
        return ghoststeps;
    } 
    public double getspeed()
    {
        return speed;
    } 
    public void setLocation(int xx, int yy)
    {
        x = xx;
        y = yy;
    }
    public void moveGhost(GameBorder gb, boolean isanumberr, int levell)
     {
        int level=levell;
        if(!caneat){
         speed=1+(.2*(level-1));}
         else{
        if( dideat)
          {speed=1;
           }else{
             speed=.5;
            }}
        isanumber=isanumberr;
         if(isanumber)
          speed=0;
        moveIt((int)xdir,(int)ydir,speed);
         
        if(y < gb.getYStart())                                  
           y = gb.getYStart(); 
        if(y > gb.getYStart() + gb.getYDimension() - 20)     
           y = gb.getYStart() + gb.getYDimension() - 20;  
	if(x < gb.getXStart())                  
             x = gb.getXStart();                       
        if(x > gb.getXStart() + gb.getXDimension() - 20)    
           x = gb.getXStart() + gb.getXDimension() - 20;
        int[] xarray=gb.getinXStart();
        int[] yarray=gb.getinYStart();
        int[] xxarray=gb.getinXDimension();
        int[] yyarray=gb.getinYDimension(); 
        /** Old code that made Boxes Bounderies */
        /*for(int i=0;i<9;i++)
       {
        if(x >= xarray[i]-18 && x<= (xxarray[i]+xarray[i]) && y >= yarray[i]-18 && y<= (yyarray[i]+yarray[i]))           
          {              
            if(xdir==1)
             x=xarray[i]-19;
            if(xdir==-1)
             x=(xxarray[i]+xarray[i])+1;
            if(ydir==1)
             y=yarray[i]-19;
            if(ydir==-1)
             y=(yyarray[i]+yarray[i])+1;
            }        
        }
        
       */
       
     }
    public void changeDirectionRandom(String ddd)
    {
        xdir = 0; 
        ydir = 0;       
        int randomdirection = (int)(Math.random()  * 4 + 1);
       /** Makes Ghost move Randomly*/
        if(randomdirection==1)
         dd="up";
         if(randomdirection==2)
         dd="down";
         if(randomdirection==3)
         dd="left";
         if(randomdirection==4)
         dd="right";
         if(((int)x==340 && (int)y==201)||((int)x==310 && (int)y==201)||((int)x==280 && (int)y==201)||((int)x==370 && (int)y==201))
          dd=ddd;
           if(dd.equals("up"))    
            {
              xdir =  0;
              ydir = -1;
              if( y<=5)
              y=5;
             }
           if(dd.equals("down"))    
            {
              xdir =  0;
              ydir = 1;
              if(y>=593)
              y=593;
             }
           if(dd.equals("left"))    
            {
              xdir = -1;
              ydir =   0;
              if( x<=5)
              x=5;
             }
           if(dd.equals("right"))    
            {
              xdir = 1;
              ydir =  0;
              if( x>=653)
              x=653;
             }
         
       
     }
    public void changeDirectionChase(PacMan pac)
    {
        xdir = 0; 
        ydir = 0; 
        int randomdirection = (int)(Math.random()  * 2 + 1);
        if(randomdirection == 1)
        {
            if((int)x < pac.getx())
                 xdir = 1;    //Ghost will move right since ghost is left of Pacman
             else xdir = -1;     //Ghost moves left
        }
         if(randomdirection == 2)
        {
          if((int)y < pac.gety())
                 ydir = 1;    //Ghost will move down since ghost is above Pacman
             else ydir = -1;     //Ghost moves up  
        }
     }
     public void changeDirectionRun(PacMan pac)
    {
        xdir = 0; 
        ydir = 0; 
        int randomdirection = (int)(Math.random()  * 2 + 1);
        if(randomdirection == 1)
        {
            if((int)x > pac.getx())
                 xdir = 1;    //Ghost will move right since ghost is left of Pacman
             else xdir = -1;     //Ghost moves left
        }
         if(randomdirection == 2)
        {
          if((int)y > pac.gety())
                 ydir = 1;    //Ghost will move down since ghost is above Pacman
             else ydir = -1;     //Ghost moves up  
        }
     }
     public void changeDirectiongohome(PacMan pac)
    {
        
        xdir = 0; 
        ydir = 0; 
        int randomdirection =0;
       if(Math.abs(325-x)>=Math.abs(201-y))
        randomdirection = 1;
        else
        randomdirection = 2;
       
        if(randomdirection == 1)
        {
            if(x < 325)
                 xdir = 1;    //Ghost will move right since ghost is left of Pacman
             else xdir = -1;     //Ghost moves left
        }
         if(randomdirection == 2)
        {
          if(y < 201)
                 ydir = 1;    //Ghost will move down since ghost is above Pacman
             else ydir = -1;     //Ghost moves up  
        }
        
        if((int)y==201)
         count1++;
        
        if(dideat && count1>=5){
            dideat=false;
            caneat=false;
            count1=0;
        }

     }
    public boolean getCanEat()
     {
       return caneat;
      }      
    public void setCanEat(boolean ce)
    {
        caneat = ce;
    }    
    public boolean getDidEat()
    {
        return dideat;
    }    
    public void setDidEat(boolean de)
    {
        dideat = de;
    } 
}
