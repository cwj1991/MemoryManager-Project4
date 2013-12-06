/**
 * Defines the bounds of the box of the point being inserted
 *
 * @author Curtis Johnson
 * @version Oct 15, 2013
 *
 */
public class WorldMap
{
    /**
     * upper x bound
     */
    private double upperX;
    /**
     * upper y bound
     */
    private double upperY;

    /**
     * lower x bound
     */
    private double lowerX;

    /**
     * lower y bound
     */
    private double lowerY;

    /**
     * Default constructor
     */
    public WorldMap()
    {
        lowerX = 0.0;
        lowerY = 0.0;
        upperX = 360.0;
        upperY = 180.0;
    }

    /**
     * gets upper x bound
     *
     * @return upper x bound
     */
    public double getUpXBound()
    {
        return upperX;
    }

    /**
     * sets upper x bound
     *
     * @param upXBound
     *            new upper x bound
     */
    public void setUpXBound( double upXBound )
    {
        this.upperX = upXBound;
    }

    /**
     * gets upper y bound
     *
     * @return upper y bound
     */
    public double getUpYBound()
    {
        return upperY;
    }

    /**
     * sets upper y bound
     *
     * @param upYBound
     *            new upper y bound
     */
    public void setUpYBound( double upYBound )
    {
        this.upperY = upYBound;
    }

    /**
     * gets lower x bound
     *
     * @return lower x bound
     */
    public double getLowXBound()
    {
        return lowerX;
    }

    /**
     * sets lower x bound
     *
     * @param lowXBound
     *            new lower x bound
     */
    public void setLowXBound( double lowXBound )
    {
        this.lowerX = lowXBound;
    }

    /**
     * gets lower y bound
     *
     * @return lower y bound
     */
    public double getLowYBound()
    {
        return lowerY;
    }

    /**
     * sets lower y bound
     *
     * @param lowYBound
     *            new lower y bound
     */
    public void setLowYBound( double lowYBound )
    {
        this.lowerY = lowYBound;
    }
}

