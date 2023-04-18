import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GraphicsPanel4  extends JPanel
{   
     Color[] colors;
     PacMan ppac;
     boolean eendgame,gghostcontact,isanumber;
     ArrayList<Pellet1> pell,ppowpel;
     Ghost[] ggho;
     Cherry cherry;
     int ghostcount,level,lives,numbertime,multiplier;  
     GameBorder ggb;
     /** Takes Starting Values of Pellets, Ghosts, and Pacman*/
    public GraphicsPanel4(PacMan pac, ArrayList<Pellet1> pel, Ghost[] gho, boolean endgame,ArrayList<Pellet1> powpel, GameBorder gb, int llevel, Cherry ccherry )              
    {
      setBackground(Color.black);
      ppac=pac;
      pell=pel;
      ggho=gho;
      eendgame = endgame;
      ppowpel=powpel;
      ggb=gb;
      level=llevel;
      lives=3;
      cherry=ccherry;
    }
     /** Updates Loaction of Pacman */
    public void updateLocation(PacMan pac)
    {
      ppac=pac;
    }
    public void updatenumbertime(int numbertimee)
    {
      numbertime=numbertimee;
    }
    /** Updates whether Pellets exist and how Long Ghosts have been Edible*/
    public void updateStuff(ArrayList<Pellet1> pel,ArrayList<Pellet1> powpel, int ghocount,int llevel,int livess,int mmultiplier, Cherry ccherry)
    {
      pell=pel;
      ppowpel=powpel;
      ghostcount=ghocount;
      level=llevel;
      lives=livess;
      multiplier=mmultiplier;
      cherry=ccherry;
    }
    /** Updates Status of Endgame*/
    public void hitGhost(boolean endgame,boolean ghostcontact)
    {
        eendgame = endgame;
        gghostcontact=ghostcontact;
    }
    
    public void paint (Graphics g)         
    {
        super.paint(g); 
        /** Makes Colors for Ghosts*/
       colors = new Color[4];
       colors[0]=Color.yellow;
       colors[1]=Color.red;
       colors[2]=Color.cyan;
       colors[3]=Color.pink;
       /**Draws Cherry */
       g.setColor(Color.red); 
       if(cherry.getexists() ){
        g.fillOval(cherry.getx(),cherry.gety()+7,10,10); 
        g.setColor(new Color(101,67,33)); 
        g.fillRect(cherry.getx()+4, cherry.gety()+3, 3, 7);
      }
       /**Draws Pacman */
       g.setColor(Color.yellow); 
       g.fillOval(ppac.getx(),ppac.gety(),20,20); 
       /** Turns Game Boxes into Int Arrays*/
       int[] xarray=ggb.getinXStart();
       int[] yarray=ggb.getinYStart();
       int[] xxarray=ggb.getinXDimension();
       int[] yyarray=ggb.getinYDimension(); 
       /**Gets Values For Mouth*/
       int [] mup= ppac.getmouthup();
       int [] mdown= ppac.getmouthdown();
       int [] mmid= ppac.getmouthmid();
       /**Draws Mouth*/
       g.setColor(Color.black); 
       if(!gghostcontact)
         g.fillPolygon(new int[] {mmid[0], mdown[0], mup[0]}, new int[] {mmid[1], mdown[1], mup[1]}, 3);
       else{
           g.fillArc(mmid[0]-11, mmid[1]-11, 22, 22, 0, mup[0]);
        }
       /** Draws Pellets */
       g.setColor(Color.white);
     for(int xao=0;xao<pell.size();xao++)
     {
        for(int i=0;i<9;i++)
       {
       if(pell.get(xao).geteat() == false && ((pell.get(xao).getx() <= xarray[i] && pell.get(xao).getx() >= xarray[i]-20) || (pell.get(xao).getx()>= (xxarray[i]+xarray[i]) && pell.get(xao).getx()<= (xxarray[i]+xarray[i])+20) || ((pell.get(xao).gety() <= yarray[i] && pell.get(xao).gety() >= yarray[i]-20) || (pell.get(xao).gety()>= (yyarray[i]+yarray[i]) && pell.get(xao).gety()<= (yyarray[i]+yarray[i])+20))))       
          { 
         g.fillOval(pell.get(xao).getx(),pell.get(xao).gety(),10,10);
        }
     }
    }
       /** Draws ScoreBoard */
       g.setColor(Color.blue);
       g.drawString("Score:" + Math.round(ppac.getscore()), 10,650);
       g.drawString("Level:" + level, 150,650);
       
       /** Draws Life Counter */
       g.drawString("Lives:", 365,650);
       g.setColor(Color.yellow);
       for(int q=0; q<lives; q++)
        g.fillArc(400+(q*25), 635, 20, 20, 35, 290);       
        
       /** Draws Game Over Screen*/
       g.setColor(Color.blue);
       if(lives==0)
       {
          g.setFont(new Font("Arial", Font.BOLD, 60));
          g.drawString("Game Over",200,300);
        }
        /** Draws Ghosts */
        
       for(int index = 0; index < ggho.length; index++)
      {
          if(ggho[index].getDidEat() == false)
          {
            if(ggho[index].getCanEat() == false)
              {
               g.setColor(colors[index]);
               g.fillArc(ggho[index].getx(), ggho[index].gety(), 20, 40, 0, 180);
               
               g.setColor(Color.white);
               g.fillOval(ggho[index].getx()+3, ggho[index].gety()+5,7,7); 
               g.fillOval(ggho[index].getx()+10, ggho[index].gety()+5,7,7); 
               g.setColor(Color.black);
               
               g.fillOval(ggho[index].getx()+6, ggho[index].gety()+6,4,4); 
               g.fillOval(ggho[index].getx()+13, ggho[index].gety()+6,4,4);  
               
              }
            if(ggho[index].getCanEat() == true)
             {
               if( ghostcount > 500 && ghostcount % 50 > 25)
                  g.setColor(Color.white);
              else
              g.setColor(Color.blue);
              g.fillArc(ggho[index].getx(), ggho[index].gety(), 20, 40, 0, 180); 
              g.setColor(Color.white);
              g.fillRect(ggho[index].getx()+6, ggho[index].gety()+6,4,4); 
              g.fillRect(ggho[index].getx()+13, ggho[index].gety()+6,4,4);
              g.fillRect(ggho[index].getx()+8, ggho[index].gety()+13,8,2);
              
             }                          
         }else{
             if(numbertime<100)
             {   g.setColor(Color.white);
                 g.drawString(""+100*(int)(Math.pow(2,multiplier)),ggho[index].getx(),ggho[index].gety()+10);
                 numbertime++;
               isanumber=true;
                }
                else{
               g.setColor(Color.white);
               g.fillOval(ggho[index].getx()+3, ggho[index].gety()+5,7,7); 
               g.fillOval(ggho[index].getx()+10, ggho[index].gety()+5,7,7); 
               
               g.setColor(Color.black);
               g.fillOval(ggho[index].getx()+6, ggho[index].gety()+6,4,4); 
               g.fillOval(ggho[index].getx()+13, ggho[index].gety()+6,4,4); 
               isanumber=false;
               
            }
        }
     }
     /** Draws Power Pellets */
     for(int index = 0; index < ppowpel.size(); index++)
      {
          g.setColor(Color.white);
          if(ppowpel.get(index).geteat() == false)
          g.fillOval(ppowpel.get(index).getx(), ppowpel.get(index).gety(), 20,20); 
       } 
       /** Draws Game Boxes */
     g.setColor(Color.blue);
     g.drawRect(ggb.getXStart(), ggb.getYStart(), ggb.getXDimension(), ggb.getYDimension());
     int[] axarray=ggb.getinXStart();
     int[] ayarray=ggb.getinYStart();
     int[] axxarray=ggb.getinXDimension();
     int[] ayyarray=ggb.getinYDimension();
     for(int i=0;i<xarray.length;i++)
     {
      g.drawRect(axarray[i], ayarray[i], axxarray[i], ayyarray[i]);
     }  
        g.drawLine(0, 397, 5, 397);
        g.drawLine(0, 417, 5, 417); 
        g.drawLine(681, 397, 685, 397);
        g.drawLine(681, 417, 685, 417); 
        g.setColor(Color.black); 
        g.drawLine(5, 417, 5, 397);
        g.drawLine(680, 417, 680, 397);
    }     
    public boolean getisanumber()
     {
       return isanumber;
      }  
}
