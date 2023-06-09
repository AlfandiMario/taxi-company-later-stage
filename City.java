import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
    
/**
 * A collection of items in the city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class City
{
    private List<Object> items;
    private int width;
    private int height;
    private List<Object> harga;
    
    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 30;

    /**
     * Constructor for objects of class City
     * @param width The city's width.
     * @param height The city's height.
     */
    public City(int width, int height)
    {
        if(width < 1) {
            throw new IllegalArgumentException(
                        "Width must be positive: " +
                        width);
        }
        if(height < 1) {
            throw new IllegalArgumentException(
                        "Height must be positive: " +
                        height);
        }
        this.width = width;
        this.height = height;
        items = new LinkedList<Object>();
    }
    
    /**
     * Create a city of default size.
     */
    public City()
    {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    /**
     * @return An iterator over the items.
     */
    public Iterator getItems()
    {
        return items.iterator();
    }

    public Iterator getHarga()
    {
        return harga.iterator();
    }
    
    /**
     * Add the given item to the city's collection.
     * @param item The item to be added.
     */
    public void addItem(Object item)
    {
        items.add(item);
    }

    /**
     * Remove the given item from the city's collection.
     * @param item The item to be removed.
     */
    public void removeItem(Object item)
    {
        items.remove(item);
    }
        
    /**
     * @return A string representation of the city.
     */
    public String toString()
    {
        return "City size " + width + " by " + height;
    }
    
    /**
     * @return The width.
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * @return The height.
     */
    public int getHeight()
    {
        return height;
    }
}
