
public class MandlebrotSettings {

	private int width, height, hueAdj;
	private double xmin, xmax, ymin, ymax;
	private double xscale, yscale;
	private boolean lightBack;
	
	public MandlebrotSettings(int sWidth, int sHeight){
		width = sWidth;
		height = sHeight;
		xmin = -2.24;
		xmax = 2.26;
		ymin = -2.24;
		ymax = 2.26;
		xscale = (xmax-xmin) / (double)width;
		yscale = (ymax-ymin) / (double)height;
		hueAdj = 0;
		lightBack = false;
	}
	
	public MandlebrotSettings(int sWidth, int sHeight, double sXmin, double sXmax, double sYmin, double sYmax, int sHueAdj, boolean sLightBack){
		width = sWidth;
		height = sHeight;
		xmin = sXmin;
		xmax = sXmax;
		ymin = sYmin;
		ymax = sYmax;
		xscale = (xmax-xmin) / (double)width;
		yscale = (ymax-ymin) / (double)height;
		hueAdj = sHueAdj;
		sLightBack = false;
	}
	
	public boolean getLightBack()
	{
		return lightBack;
	}
	
	public void setLightBack(boolean value)
	{
		lightBack = value;
	}
	public int getWidth()
	{
		return width;
	}
	
	public int getHueAdjustmentFactor()
	{
		return hueAdj;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setImageDimensions(int sWidth, int sHeight)
	{
		if(xmax == width && xmin == 0)
		{
			xmax = sWidth;
		}
		if(ymax == height && ymin == 0)
		{
			ymax = sHeight;
		}
		
		width = sWidth;
		height = sHeight;
		
		xscale = (xmax-xmin) / (double)width;
		yscale = (ymax-ymin) / (double)height;
	}
	
	public double getXScale()
	{
		return xscale;
	}
	
	public double getYScale()
	{
		return yscale;
	}
	
	public double getXmin()
	{
		return xmin;
	}
	
	public double getXmax()
	{
		return xmax;
	}
	
	public double getYmax()
	{
		return ymax;
	}
	
	public double getYmin()
	{
		return ymin;
	}
	
	
	

}
