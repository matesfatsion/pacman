import java.util.ArrayList;

public class PacMan extends MoveableGameObject
{
    double ydir,xdir,speed;
    double score,i;
    int [] mouthup,mouthmid,mouthdown;
    boolean ghostcontact,ghostcontactendgame,isanumber;
    ArrayList<Pellet1> pel;
    
    /** Recives Location of Pacman */
    public PacMan(int x, int y, int sscore, int xdirection, int ydirection)
    {
        super(x,y);
        score=sscore;
        ydir=ydirection;
        xdir=xdirection;
        mouthmid =new int[2];
        mouthup =new int[2];
        mouthdown =new int[2];
    }
    public void moving(int xdir, GameBorder gb,int ydir)
    {
        /** Old code that Boxes Boundaries*/
        /*if(y < gb.getYStart())                                     
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
        for(int i=0;i<9;i++)
       {
        if(x >= xarray[i]-20 && x<= (xxarray[i]+xarray[i]) && y >= yarray[i]-20 && y<= (yyarray[i]+yarray[i]))           
          {              
            if(xdir==1)
             x=xarray[i]-21;
            if(xdir==-1)
             x=(xxarray[i]+xarray[i])+1;
            if(ydir==1)
             y=yarray[i]-21;
            if(ydir==-1)
             y=(yyarray[i]+yarray[i])+1;
            }        
        }
        */
    } 
    public void checkNewDirection(String dd, ArrayList<Pellet1> ppel, boolean isanumberr)
    {
        isanumber=isanumberr;
        if(!isanumber){
         speed=1.2;}else{
             speed=0;}
        
        pel=ppel;
        /** Allows Pacman to Move at Start*/
        if(pel.size()>=99)
        {
            if(dd.equals("left"))    
            {
              xdir = -1;
              ydir =   0;
             }
           if(dd.equals("right"))    
            {
              xdir = 1;
              ydir =  0;
             }
           
        }
        /** Moves Pacman any Direction if at a Corner */
      //if(((int)x <= 5 || (int)x == 221 || (int)x == 437 || (int)x >= 653) && ((int)y <= 5 || (int)y == 201 || (int)y == 397 || (int)y >= 593))
      if((x <= 5 || (x >= 220.1 && x <= 221.9) || (x >= 436.1 && x <= 437.9) || x >= 653) && (y <= 5 || (y >= 200.1 && y <= 201.9) || (y >= 396.1 && y <= 397.9) || y >= 593))
       { 
           
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
              if( x<=-20 && (y >= 396.1 && y <= 397.9))
              x=677;
              if( x<=5 && !(y >= 396.1 && y <= 397.9))
              x=5;
             }
           if(dd.equals("right"))    
            {
              xdir = 1;
              ydir =  0;
              if( x>=678 && (y >= 396.1 && y <= 397.9))
              x=-19;
              if( x>=653 && !(y >= 396.1 && y <= 397.9))
              x=653;
             }
         }
         /** Moves Pacman*/
        if(!ghostcontact)
         moveIt((int)xdir,(int)ydir,speed);
        
        /** Allows Pacman to Move While not at Corner */
        if(dd.equals("left") && xdir==1) 
         {xdir=-1;
          ydir=0;
            }
        if(dd.equals("right") && xdir==-1) 
         {xdir=1;
         ydir=0;
            }
        if(dd.equals("up") && ydir==1) 
         {ydir=-1;
          xdir=0;
            }  
        if(dd.equals("down") && ydir==-1) 
         {ydir=1;
         xdir=0;
            }  
     }  
     public void PacManMouth(boolean gghostcontact)
    {
         
        ghostcontact=gghostcontact;
        
        mouthmid[0]=(int)x+10;
        mouthmid[1]=(int)y+10; 

          
       if(!ghostcontact)
       {
         if(!((x <= 5 ||  x >= 653) && (y <= 5 ||  y >= 593)) && !isanumber)
          i=i+0.3;
         if(i>=20)
          i=0;
          int ii = (int) i;
          if(xdir==-1)
        {
         mouthup[0]=(int)x;
         mouthup[1]=(int)y+ii;
         mouthdown[0]=(int)x;
         mouthdown[1]=(int)y+20-ii;         
        }           
        if(xdir==1)
        {
         mouthup[0]=(int)x+20;
         mouthup[1]=(int)y+ii;
         mouthdown[0]=(int)x+20;
         mouthdown[1]=(int)y+20-ii;
        }
        if(ydir==-1)
        {
         mouthup[0]=(int)x+ii;
         mouthup[1]=(int)y;
         mouthdown[0]=(int)x+20-ii;
         mouthdown[1]=(int)y;
        }
        if(ydir==1)
        {
         mouthup[0]=(int)x+ii;
         mouthup[1]=(int)y+20;
         mouthdown[0]=(int)x+20-ii;
         mouthdown[1]=(int)y+20;
         
        }
      }
      else
      {
          int ii = (int) i;
          if(i<=360) {
            //*PlayDead();
            i=i+1.5;
            mouthup[0]=ii;
        }
        else{
          ghostcontactendgame=true;  
        }
        }
    } 
    public void reset()
    {
        x=340;
        y=397;
        xdir=0;
        ydir=0;
    }
    public double getscore()
    {
        return score;
    } 
    public double getspeed()
    {
        return speed;
    } 
    public void scored(double scorecard)
    {
        score=score +scorecard;
    } 
    public int[] getmouthup()
    {
        return mouthup;
    }
    public int[] getmouthdown()
    {
        return mouthdown;
    } 
    public int[] getmouthmid()
    {
        return mouthmid;
    } 
    public boolean getghostcontactendgame()
    {
        return ghostcontactendgame;
    } 
    public void ghostcontactendgamefalse()
    {
        ghostcontactendgame=false;
    } 
}