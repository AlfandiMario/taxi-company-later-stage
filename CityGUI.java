import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;
import javax.swing.*;
    
/**
 * Provide a view of the vehicles and passengers in the city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class CityGUI extends JFrame implements Actor
{
    // The dimensions of the GUI.
    public static final int CITY_VIEW_WIDTH = 600;
    public static final int CITY_VIEW_HEIGHT = 600;
    private City city;
    private CityView cityView;
    /**
     * Constructor for objects of class CityGUI
     * @param city The city whose state is to be displayed.
     */
    public CityGUI(City city)
    {
        this.city = city;
        cityView = new CityView(city.getWidth(), city.getHeight());
        getContentPane().add(cityView);
        setTitle("Kelompok 1");
        setSize(CITY_VIEW_WIDTH, CITY_VIEW_HEIGHT);
        setVisible(true);
        cityView.preparePaint();
        cityView.repaint(); 
    }
    
    /**
     * Display the current state of the city.
     */
    public void act()
    {
        cityView.preparePaint();
        Iterator items = city.getItems();
        while(items.hasNext()) {
            Object obj = items.next();
            if(obj instanceof DrawableItem){
                DrawableItem item = (DrawableItem) obj;
                Location location = item.getLocation();
                cityView.drawImage(location.getX(), location.getY(), item.getImage());
            }
            else{
                cityView.drawString(obj.toString());
            }
        }
        cityView.repaint();    
        
    }
    
    /**
     * Provide a graphical view of a rectangular city. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the city.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class CityView extends JPanel
    {
        private final int VIEW_SCALING_FACTOR = 6;

        private int cityWidth, cityHeight;
        private int xScale, yScale;
        private Dimension size;
        private Graphics g;
        private Image cityImage;
        /**
         * Create a new CityView component.
         */
        public CityView(int width, int height)
        {
            cityWidth = width;
            cityHeight = height;
            setBackground(Color.white);
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(cityWidth * VIEW_SCALING_FACTOR,
                                 cityHeight * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(!size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                cityImage = cityView.createImage(size.width, size.height);
                g = cityImage.getGraphics();
                
                xScale = (size.width-150) / cityWidth;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = size.height / cityHeight;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, size.width-150, size.height);
            g.setColor(Color.red);
            g.setColor(Color.black);
            g.setColor(Color.white);
            g.fillRect(size.width-150, 0, size.width, size.height);
            g.setColor(Color.black);
            g.drawString("Queen Gambit Company",size.width-149 ,15);
            g.drawString("Tarif : Rp.3000/km",size.width-149 ,30);
        }
        
        /**
         * Draw the image for a particular item.
         */
        public void drawImage(int x, int y, Image image)
        { 
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }
        public void drawString( String text)
        {
            String textFull = "Profit : Rp."+ text;
            g.setColor(Color.black);
            g.drawString(textFull,size.width-149 ,45);
        }
        /**
         * The city view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(cityImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(cityImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(cityImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
