/**
 *
 * @author
 * @version Oct 12, 2013
*/

public class NodeLeaf implements Node
{
    private byte[] currentH = new byte[4];
    private byte[] dataH = new byte[4];

    /**
     * Default Constructor
     * @param dataHandle
     */
    public NodeLeaf( byte[] dataHandle)
    {
        currentH = null;
        dataH = dataHandle;
    }

    public byte[] getCurrentH()
    {
        return currentH;
    }

    public void setCurrentH( byte[] currentH )
    {
        this.currentH = currentH;
    }

    public byte[] getDataH()
    {
        return dataH;
    }


    /**
     * Sets the data handle.
     * @param dataH
     */
    public void setDataH( byte[] dataH )
    {
        this.dataH = dataH;
    }


    /**
     * Returns true because this is a leaf node
     */
    @Override
    public boolean isLeaf()
    {
        return true;
    }

    @Override
    public byte[] Serialize()
    {
        byte[] nodeRepresentation = new byte[5];
        nodeRepresentation[0] = 1;

        nodeRepresentation[1] = dataH[0];
        nodeRepresentation[2] = dataH[1];
        nodeRepresentation[3] = dataH[2];
        nodeRepresentation[4] = dataH[3];

        return nodeRepresentation;
    }

}
