
// -------------------------------------------------------------------------
/**
 * This class creates and implements a watcher record and it's methods
 *
 *  @author Curtis Johnson
 *  @author Miguel Suarez
 *  @version Sep 16, 2013
 */
public class Watcher
{
    // create fields of watcher
    private double Latitude;
    private double Longitude;
    private String Name;

    {
        this.Latitude = 0.0;
        this.Longitude = 0.0;
        this.Name=" ";

    }

    //set the fields
    // ----------------------------------------------------------
    /**
     * Sets the latitude.
     * @param Lat new latitude
     */
    public void setLat( Double Lat )
    {

            this.Latitude = Lat;

    }

    // ----------------------------------------------------------
    /**
     * Sets the name.
     * @param NameP new name
     */
    public void setName( String NameP )
    {
        if ( NameP != "" )
        {
            this.Name = NameP;
        }
    }

    // ----------------------------------------------------------
    /**
     * Sets longitude.
     * @param Longg new longitude
     */
    public void setLong( Double Longg )
    {

            this.Longitude = Longg;

    }


    //get the fields.
    // ----------------------------------------------------------
    /**
     * Gets the longitude.
     * @return returns longitude
     */
    public double getLong()
    {
        return this.Longitude;
    }

    // ----------------------------------------------------------
    /**
     * Gets the name.
     * @return returns the name.
     */
    public String getName()
    {
        return this.Name;
    }

    // ----------------------------------------------------------
    /**
     * Get the longitude.
     * @return returns longitude
     */
    public double getLat()
    {
        return this.Latitude;
    }

}

