/*Matthew Tesfatsion*/
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class PacManWithObjectClasses  implements ActionListener, KeyListener
{
        JFrame f1;
        JPanel main, sub;
        GraphicsPanel4 g1;
        JButton b1,b2;
        int xdir, ydir,ghocount,atecount,multiplier,levelpausecounter,level,lives,numbertime,cherrycount;
        boolean endgame, startgame,caneatghosts,ghostcontact;
        boolean[] ghoststeps;
        ArrayList<Pellet1> pel,powpel;
        Ghost[] gho;
        PacMan pac;
        GameBorder gb;
        Cherry cherry;
        int[] inxstart, inystart, inxdimension, inydimension;
        String desireddirection,ddd;
        Clip waka,startsound,ghosteatsound,dead,intermission;
    public PacManWithObjectClasses() 
    { 
        
        StartingValues();        
        MakePellets(); 
        MakePowPellets();
        MakeGhost();
        PlayMusic();
             
        FrameAndContainer();
        
        runGame();
    }
    
    public void runGame()
    {
        Thread runner = new Thread();
         
      while(endgame == false)
      {    
          
        
         try 
          { 
            runner.sleep(5); 
           }
          catch(InterruptedException e) {}  
          
       /** Need this section of code to slow computer down. */   
       if(startgame == true && levelpausecounter<=0 && (startsound.getMicrosecondPosition() >= startsound.getMicrosecondLength()))
         {            
           GhostFunctions();
           PelletInterception();
           PowPelletInterception();
           GhostInterception();
           CherryStuff();

            if(pel.size() == 36)     
            {  
              nextLevel();
              PlayIntermission();
             } 
             
        /** Sends Values*/
        pac.checkNewDirection(desireddirection,pel,g1.getisanumber());
        pac.PacManMouth(ghostcontact); 
        pac.moving(xdir, gb,ydir);
        g1.updateLocation(pac);
        g1.updateStuff(pel,powpel,ghocount,level,lives,multiplier,cherry);
        g1.repaint();
       } 
       else{
           levelpausecounter--;
        }
      
    }
}
    public void actionPerformed (ActionEvent event)
    {    
        /** Starts and Ends Game based on Button Pushes */
        if (event.getSource() == b1)
        {
           g1.requestFocus();
           startgame = true;
           PlayStartSound();
         }
         
        if (event.getSource() == b2)
        {
           endgame = true;
         }
         
         
    }
    public void keyReleased(KeyEvent evt)
    {}
    public void keyTyped(KeyEvent evt)
    {}
    
    public void FrameAndContainer() 
    {
        /** Creates Frame */
        f1 = new JFrame("Graphics Example");
          f1.setSize(700,750);
          f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
          
        Container c1 = f1.getContentPane();
          /**Sends Values to Graphics Class*/
        g1 = new GraphicsPanel4(pac,pel,gho,endgame,powpel,gb,level,cherry);
          g1.addKeyListener(this);
          /** Makes Buttons */
        b1 =  new JButton("Start");
           b1.addActionListener(this);
        
         b2 =  new JButton("End");
           b2.addActionListener(this);
         /**Creates Panel for Buttons*/
        sub = new JPanel(); 
          sub.add(b1);
          sub.add(b2);
                     
       main = new JPanel();
          main.setLayout(new BorderLayout());          
          main.setSize(600,600);
          main.add(g1,BorderLayout.CENTER);
          main.add(sub,BorderLayout.SOUTH);
        
        c1.add(main);
        f1.show(); 
    }
    
    public void StartingValues() 
    { 
        level = 1;
        lives= 3;
       gb = new GameBorder(5,5,675,615);
        desireddirection = "";
        ghoststeps=new boolean[4];
       inxstart= new int[9]; 
       inystart= new int[9]; 
       inxdimension= new int[9]; 
       inydimension= new int[9]; 
       
        /** Makes values for Boxes */  
        for(int i=1; i<=9; i++)
       { inxdimension[(i-1)]=(gb.getXDimension()-gb.getXStart()-80)/3;
         inydimension[(i-1)]=((gb.getYDimension()-gb.getYStart())-80)/3;
        if(i<=3)
        {
         inxstart[(i-1)]=((20*i)+((i-1)*inxdimension[(i-1)]))+gb.getXStart();
         inystart[(i-1)]=20+gb.getYStart();
        }
        if(i<=6 && i>3)
        {
         inxstart[(i-1)]=((20*(i-3))+((i-4)*inxdimension[(i-1)]))+gb.getXStart();
         inystart[(i-1)]=40+gb.getYStart()+ inydimension[(i-1)];
        }
        if(i<=9 && i>6)
        {
         inxstart[(i-1)]=((20*(i-6))+((i-7)*inxdimension[(i-1)]))+gb.getXStart();
         inystart[(i-1)]=60+gb.getYStart()+ 2*inydimension[(i-1)];
        }
        }
        /** Sends values of Boxes */
        gb.instuff(inxstart, inystart, inxdimension, inydimension);
        /** Starting point of Pacman */
        pac=new PacMan(340,397,0,xdir,ydir);
        
        atecount=1;
        xdir = 0;
        ydir = 0;
        multiplier=1;
        endgame = false;  
        
        pel= new ArrayList<Pellet1>();
        cherry=new Cherry(340,397);
        
    }
        
    public void CherryStuff()
    {
      //cherry.updatecount(cherrycount);
      cherrycount--;
      if(pel.size()==85 || pel.size()==62)
       cherrycount=2000;
      
      if(cherrycount>=0 && cherrycount<=1990){
         cherry.exists();
        }else{     
          cherry.doesntexists();           
     }
      if(pac.getx()+20 > cherry.getx() && pac.getx() < cherry.getx() + 10 && pac.gety()+20 > cherry.gety() && pac.gety() < cherry.gety() + 10 && cherry.getexists()){
          cherrycount=0;
          pac.scored(50);
          }
      
    }
    
    public void MakePowPellets()
    {
        /**Makes Power Pellets */
        powpel= new ArrayList<Pellet1>();
        powpel.add(new Pellet1(5,5));        
        powpel.add(new Pellet1(5,593));
        powpel.add(new Pellet1(653,5));
        powpel.add(new Pellet1(653,593));
    }
    
    public void PowPelletInterception()
    {
      /** Detects Interception of Pacman and Power Pellets */
       for(int index = 0; index < powpel.size(); index++)
            {
                if(pac.getx() > powpel.get(index).getx() - 20 && pac.getx() < powpel.get(index).getx() + 20 && pac.gety() > powpel.get(index).gety() - 20 && pac.gety() < powpel.get(index).gety() + 20 )
              {
                  powpel.get(index).pelleteat();
                  caneatghosts = true;
                  pac.scored(50);
                  ghocount=0;
                  powpel.remove(index);
                  multiplier=1;
                  for(int i2 = 0; i2 < gho.length; i2++)
                    {
                       gho[i2].setCanEat(true);
                    }
              }
            }  
    }    
    
    public void MakePellets()
    {
        /** Sets values of Pellets */
       for(int i=0;i<100;i++)
       {
         pel.add(new Pellet1(72*(i%10)+10,65*(i/10)+10));  
        }  
        
    }
    
    public void PelletInterception()
    {
      /** Detects Interception of Pacman and Pellets */
       for(int zip=0;zip<pel.size();zip++)
       {
        if(pac.getx() >= pel.get(zip).getx()-10 && pac.getx() <= pel.get(zip).getx() + 20 && pac.gety() >= pel.get(zip).gety() - 10 && pac.gety() <= pel.get(zip).gety() + 20 && !pel.get(zip).geteat())
        {
            pel.get(zip).pelleteat();
            pac.scored(10);
            pel.remove(zip);
            PlayWaka();
         }
        }  
    }   
    
    public void MakeGhost()
    {
        /** Sets values of Ghosts */
        gho = new Ghost[4];
        gho[1]= new Ghost(340,201);
        gho[2]= new Ghost(310,201);
        gho[3]= new Ghost(280,201);
        gho[0]= new Ghost(370,201);
         /** Ghosts begin goving Left or Right */
        ddd="left";
        gho[3].changeDirectionRandom(ddd);
        gho[2].changeDirectionRandom(ddd);
        ddd="right";
        gho[0].changeDirectionRandom(ddd);
        gho[1].changeDirectionRandom(ddd);
    }
    
    public void GhostInterception()
    {
     /** Detects Interception of Pacman and Ghosts if They are Able to Eat */
       if(caneatghosts) 
        {     
          ghocount++;
          for(int index = 0; index < gho.length; index++)
            {
                if(pac.getx() > gho[index].getx() - 20 && pac.getx() < gho[index].getx() + 20 && pac.gety() > gho[index].gety() - 20 && pac.gety() < gho[index].gety() + 20 && gho[index].getCanEat() == true && gho[index].getDidEat() == false)
              {
                  double dascore=100*(Math.pow(2,multiplier));
                  pac.scored(dascore);
                  gho[index].setDidEat(true);
                  multiplier++;
                  numbertime=0;
                  g1.updatenumbertime(numbertime);
                  PlayGhostEatSound();
              }
           }
         } 
         
          
       /** Detects Interception of Pacman and Non-Edible Ghosts */
       for(int j=0;j<gho.length;j++)
       {
         if(pac.getx() >= gho[j].getx()-20 && pac.getx() <= gho[j].getx() + 20 && pac.gety() >= gho[j].gety() - 10 && pac.gety() <= gho[j].gety() + 20 && !gho[j].getCanEat() && !gho[j].getDidEat())
        {
           PlayDead();
           ghostcontact= true;
           g1.hitGhost(endgame,ghostcontact);
         } 
        }   
        if(pac.getghostcontactendgame())
       {           
           nextLevel();
           lives--;
           level--;
           ghostcontact= false;
           g1.hitGhost(endgame,ghostcontact);
           pac.ghostcontactendgamefalse();
        }
       if(lives==0) 
        endgame=true;
       
    }
    
    public void GhostFunctions() 
    {
        /**Makes Ghosts Move*/
        if(!ghostcontact){   
          for(int index = 0; index < gho.length; index++)
            {
                gho[index].moveGhost(gb,g1.getisanumber(),level);
            }
        }
            /** Allows Ghosts to Change Direction if at Corner*/
          for(int i = 0; i < gho.length; i++)
            {
            if(((gho[i].getx() <= 5 || gho[i].getx() == 221 || gho[i].getx() == 437 || gho[i].getx() >= 653) && (gho[i].gety() <= 5 || gho[i].gety() == 201 || gho[i].gety() == 397 || (gho[i].gety() >= 593)) ))
             ghoststeps[i]=true;
              
                
            /** Changes Method of Ghost Movment Based on Power Pellet */
       if(ghoststeps[i])
        {
         
           if(gho[i].getCanEat()){
            gho[i].changeDirectionRun(pac);
          }
          else{
           gho[i].changeDirectionRandom(ddd);
          }
          if(gho[i].getDidEat()){
             gho[i].changeDirectiongohome(pac); 
            }
         ghoststeps[i] =false;
        }
     } 
     /** Sets Ghosts Back to Normal After Becoming Edible */
       if(ghocount >= 800)
          {
               caneatghosts = false;

               for(int index = 0; index < gho.length; index++)
                 {  
                  gho[index].setCanEat(false);
                 }
           } 
    }
    
    private void nextLevel()
      {

          ResetGhosts();
          ResetPellets();
          pac.reset();
          level++;
          levelpausecounter = 1000;
        
      }
      
    private void ResetPellets()
      {
        pel.clear();
        powpel.clear();
         for(int i=0;i<100;i++)
        {
         pel.add(new Pellet1(72*(i%10)+10,65*(i/10)+10));  
        }  
        powpel.add(new Pellet1(5,5));        
        powpel.add(new Pellet1(5,593));
        powpel.add(new Pellet1(653,5));
        powpel.add(new Pellet1(653,593));
        }
        
    private void ResetGhosts()
      {
        gho[1].setLocation(340,201);
        gho[2].setLocation(310,201);
        gho[3].setLocation(280,201);
        gho[0].setLocation(370,201);
         /** Ghosts begin goving Left or Right */
        ddd="left";
        gho[3].changeDirectionRandom(ddd);
        gho[2].changeDirectionRandom(ddd);
        ddd="right";
        gho[0].changeDirectionRandom(ddd);
        gho[1].changeDirectionRandom(ddd);
        for(int uu=0;uu<4;uu++){
         gho[uu].setCanEat(false);
         gho[uu].setDidEat(false);
        }
     }
     
     private void PlayMusic()
      {
        try {
                    
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("pacman_beginning.wav"));
                 startsound = AudioSystem.getClip();
                 startsound.open(audioIn);
                   
                   
                AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(new File("WakaWakafinal3.0.wav"));
                waka = AudioSystem.getClip();
                waka.open(audioIn2);
    
                
                AudioInputStream audioIn3 = AudioSystem.getAudioInputStream(new File("pacman_eatghost.wav"));
                ghosteatsound = AudioSystem.getClip();
                ghosteatsound.open(audioIn3);
                
                
                AudioInputStream audioIn4 = AudioSystem.getAudioInputStream(new File("pacman_death.wav"));
                dead = AudioSystem.getClip();
                dead.open(audioIn4);
                
                
                AudioInputStream audioIn5 = AudioSystem.getAudioInputStream(new File("pacman_intermission.wav"));
                intermission = AudioSystem.getClip();
                intermission.open(audioIn5);
                
                
             } catch(Exception ex) 
                   {
                       System.out.println("Error with playing sound.");
                       ex.printStackTrace();
                    }
        
        }
    public void PlayIntermission()
    {

         if (intermission.getMicrosecondPosition() >= intermission.getMicrosecondLength())
        {
            intermission.setMicrosecondPosition(0);
        }
        
        intermission.start();
    } 
     public void PlayDead()
    {

         if (dead.getMicrosecondPosition() >= dead.getMicrosecondLength())
        {
            dead.setMicrosecondPosition(0);
        }
        
        dead.start();
    } 
     public void PlayGhostEatSound()
    {

         if (ghosteatsound.getMicrosecondPosition() >= ghosteatsound.getMicrosecondLength())
        {
            ghosteatsound.setMicrosecondPosition(0);
        }
        
        ghosteatsound.start();
    }    
       public void PlayWaka()
    {

         if (waka.getMicrosecondPosition() >= waka.getMicrosecondLength())
        {
            waka.setMicrosecondPosition(0);
        }
        
        waka.start();
    }  
     public void PlayStartSound()
    {

         if (startsound.getMicrosecondPosition() >= startsound.getMicrosecondLength())
        {
           // startsound.setMicrosecondPosition(0);
        }
        
        startsound.start();
    } 
    public void keyPressed(KeyEvent evt)
    {
          /** Sets Pacman Direction Based on Arrow Buttons*/
         if(!ghostcontact){ 
          if(evt.getKeyCode() == 38)
           {
              xdir = 0;
              ydir = -1;
              desireddirection = "up";
           }
           if(evt.getKeyCode() == 40)
           {
              xdir = 0;
              ydir = 1;
              desireddirection = "down";
           }
           if(evt.getKeyCode() == 37)
           {
              xdir = -1;
              ydir = 0;
              desireddirection = "left";
           }
           if(evt.getKeyCode() == 39)
           {
              xdir = 1;
              ydir = 0;
              desireddirection = "right";
           }
         }
        }
    
}