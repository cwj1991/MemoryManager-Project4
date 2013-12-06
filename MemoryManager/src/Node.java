/**
 * Node class
 *
 * @version Oct 12, 2013
 */
public interface Node
{
    /**
     * determines if node is a leaf
     *
     * @return boolean true if leaf, false otherwise
     */
    public boolean isLeaf();


    // ----------------------------------------------------------
    /**
     * Returns a byte array with the node Representation.
     * @return
     *          returns a byte array
     */
    public byte[] Serialize();

    public void setCurrentH(byte[] handle);

    public byte[] getCurrentH();


}
