import java.util.List;
import java.util.LinkedList;
import java.awt.Image;
import javax.swing.ImageIcon;
    
/**
 * A shuttle is able to carry multiple passengers.
 * This implementation is non-functional.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Shuttle extends Vehicle implements DrawableItem
{
    // The list of destinations for the shuttle.
    private List<Location> destinations;
    // The list of passengers on the shuttle.
    private List<Passenger> passengers;
    
    private int jumlahPenumpang;
    
    private Passenger passenger;
    
    private Image emptyImage, passengerImage;
   

    /**
     * Constructor for objects of class Shuttle
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public Shuttle(TaxiCompany company, Location location)
    {
        super(company, location);
        destinations = new LinkedList<Location>();
        passengers = new LinkedList<Passenger>();
        
        emptyImage = new ImageIcon(getClass().getResource(
                                "images/bus.jpg")).getImage();
        passengerImage = new ImageIcon(getClass().getResource(
                                "images/bus+persons.jpg")).getImage();
    }
    
    public Image getImage()
    {
        if(passenger != null) {
            return passengerImage;
        }
        else {
            return emptyImage;
        }
    }
    
    private Passenger FetchListPenumpang(int indexPassenger)
    {
        passenger = passengers.get(indexPassenger);
        return passenger;
    }

    /**
     * Carry out a shuttle's actions.
     */
    public void act()
    {
        Location target = getTargetLocation();
        if(target != null) {
            // Find where to move to next.
            Location next = getLocation().nextLocation(target);
            setLocation(next);
            if(next.equals(target)) {
                // Cek apakah List Passenger isi?
                if(passengers != null) {
                    jumlahPenumpang++;
                    passenger = FetchListPenumpang(jumlahPenumpang);
                    // Ambil Lokasi dari passenger tersebut
                    // Hitung distance dari target lokasi passenger
                    // Cek apakah ada request penumpang baru
                    // Kalau ada, bandingkan jaraknya dengan jarak target sebelumnya
                    // Kalau, lebih dekat. Pickup penumpang baru
                    // Kalau, lebih jauh. Antar dulu
                    
                    
                }
                else {
                    notifyPickupArrival();
                }
            }
        }
        else {
            incrementIdleCount();
        }
    }

    /**
     * Is the shuttle free?
     * @return Whether or not this vehicle is free.
     */
    public boolean isFree()
    {
        return true;
    }
    
    /**
     * Receive a pickup location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        destinations.add(location);
        chooseTargetLocation();
    }
    
    /**
     * Receive a passenger.
     * Add their destination to the list.
     * @param passenger The passenger.
     */
    public void pickup(Passenger passenger)
    {
        passengers.add(passenger);
        destinations.add(passenger.getDestination());
        chooseTargetLocation();
    }

    /**
     * Decide where to go next, based on the list of
     * possible destinations.
     */
    private void chooseTargetLocation()
    {
    }

    /**
     * Offload a passenger whose destination is the
     * current location.
     */
    public void offloadPassenger()
    {
    }
}
