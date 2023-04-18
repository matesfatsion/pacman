

public class GameBorder
{
    int xstart, ystart, xdimension, ydimension;
    int[] inxstart, inystart, inxdimension, inydimension;
    public GameBorder(int xs, int ys,  int xd, int yd)
    {
       xstart = xs;
       ystart = ys;
       xdimension = xd;
       ydimension = yd; 
       
    }

    public int getXStart()
    {
        return xstart;
    }
    
    public int getYStart()
    {
        return ystart;
    }
    
     public int getXDimension()
    {
        return xdimension;
    }
    
    public int getYDimension()
    {
        return ydimension;
    }
    
    public void instuff(int[] iinxstart,int[] iinystart,int[] iinxdimension,int[] iinydimension)
    {
     inxstart=iinxstart;  
     inystart=iinystart; 
     inxdimension=iinxdimension;
     inydimension=iinydimension;
    }
    public int[] getinXStart()
    {
        return inxstart;
    }
    public int[] getinYStart()
    {
        return inystart;
    }
    public int[] getinYDimension()
    {
        return inydimension;
    }
     public int[] getinXDimension()
    {
        return inxdimension;
    }
 
    
}
