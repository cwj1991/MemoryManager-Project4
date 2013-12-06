/**
 * Node that contains only two pointer
 *
 * @version Oct 12, 2013
 */
@SuppressWarnings( "all" )
public class NodeInternal implements Node
{

    byte[] nodeRepresentation = new byte[9];
    private byte[] leftH = new byte[4];
    private byte[] rightH = new byte[4];
    private byte[] currentH = new byte[4];

    public byte[] getCurrentH()
    {
        return currentH;
    }

    public void setCurrentH( byte[] currentH )
    {
        this.currentH = currentH;
    }

    /**
     * pointer to left child
     */
    private Node left;

    /**
     * pointer to right child
     */
    private Node right;

    /**
     * default constructor
     * @param currentHandle
     * @param leftHandle
     * @param rightHandle
     */
 //   public NodeInternal(byte[] )
    public NodeInternal(byte[] currentHandle, byte[] leftHandle, byte[] rightHandle)
    {
        currentH = currentHandle;
        leftH = leftHandle;
        rightH = rightHandle;
    }

    public byte[] getDataRepresentation()
    {
        return nodeRepresentation;
    }


    public byte[] getLeft()
    {
        return leftH;
    }

    public void setLeft( byte[] leftH )
    {
        this.leftH = leftH;
    }

    public byte[] getRight()
    {
        return rightH;
    }

    public void setRight( byte[] rightH )
    {
        this.rightH = rightH;
    }

    /**
     * parameterized constructor with two pointer
     * @param flyWeight
     *
     * @param leftChild
     *            pointer to left child
     * @param rightChild
     *            pointer to right child
     */
    public NodeInternal( Node flyWeight )
    {
        left = flyWeight;
        right = flyWeight;
    }


    /**
     * determines if node is a leaf
     */
    public boolean isLeaf()
    {
        return false;
    }

    @Override
    public byte[] Serialize()
    {
        byte[] nodeRepresentation = new byte[9];
        nodeRepresentation[0] = 0;

        nodeRepresentation[1] = leftH[0];
        nodeRepresentation[2] = leftH[1];
        nodeRepresentation[3] = leftH[2];
        nodeRepresentation[4] = leftH[3];

        nodeRepresentation[5] = rightH[0];
        nodeRepresentation[6] = rightH[1];
        nodeRepresentation[7] = rightH[2];
        nodeRepresentation[8] = rightH[3];

        return nodeRepresentation;
    }

}
