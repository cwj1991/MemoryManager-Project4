import java.nio.ByteBuffer;

// -------------------------------------------------------------------------
/**
 * This class implements a bufferobject with a block,dirty bit, and block ID.
 *
 *
 * @author Curtis Johnson
 * @version Oct 24, 2013
 */
public class BufferObject
{
    private ByteBuffer byteBuffer;
    private boolean dirtyBit;
    private int BufferId;
    private byte[] tempRec;
    private byte[] tempBlock;
    private int blockSize = 4096;
    private int recordSize = 4;

    // ----------------------------------------------------------
    /**
     * Create a new BufferObject object.
     */
    public BufferObject()
    {
        this.byteBuffer = ByteBuffer.allocate( 4096 );
        this.dirtyBit = false;
        this.BufferId = -1;
        this.tempRec = new byte[recordSize];
        this.tempBlock = new byte[blockSize];
    }

    // ----------------------------------------------------------
    /**
     * Create a new BufferObject object.
     *
     * @param b
     *            input buffer
     * @param bId
     *            input block ID
     */
    public BufferObject( byte[] b, int bId )
    {
        this.byteBuffer = ByteBuffer.wrap( b );
        this.dirtyBit = false;
        this.BufferId = bId;
        this.tempRec = new byte[recordSize];
        this.tempBlock = new byte[blockSize];

    }

    // ----------------------------------------------------------
    /**
     * Checks if dirtyBit is set.
     *
     * @return returns the boolean for the dirtyBit
     */
    public boolean isDirtyBit()
    {
        return dirtyBit;
    }

    // ----------------------------------------------------------
    /**
     * Sets the dirtyBit.
     *
     * @param dirtyBit
     */
    public void setDirtyBit( boolean dirtyBit )
    {
        this.dirtyBit = dirtyBit;
    }

    // ----------------------------------------------------------
    /**
     * Gets the BufferId.
     *
     * @return Id of the buffer.
     */
    public int getBufferId()
    {
        return BufferId;
    }

    // ----------------------------------------------------------
    /**
     * Sets the BufferId.
     *
     * @param bufferId
     */
    public void setBufferId( int bufferId )
    {
        BufferId = bufferId;
    }

    // ----------------------------------------------------------
    /**
     * Reads a record at the given posiion.
     *
     * @param position
     *            position to read record from
     * @return returns the record read.
     */
    public byte[] readRecord( int position )
    {
        tempRec = new byte[recordSize];
        this.byteBuffer.position( position - this.blockSize * this.BufferId );
        this.byteBuffer.get( this.tempRec, 0, recordSize );
        return this.tempRec;
    }

    // ----------------------------------------------------------
    /**
     * Writes the new record to the given position.
     *
     * @param rec
     *            new record to be written.
     * @param position
     *            position of the new record.
     */
    public void writeRecord( byte[] rec, int position )
    {
        this.byteBuffer.position( position - this.blockSize * this.BufferId );
        this.byteBuffer.put( rec, 0, recordSize );
        this.dirtyBit = true;
    }

    // ----------------------------------------------------------
    /**
     * Returns a block to be written to the file.
     *
     * @return returns one buffer block.
     */
    public byte[] writeBlock()
    {
        this.byteBuffer.position( 0 );
        this.byteBuffer.get( tempBlock, 0, this.blockSize );
        return tempBlock;
    }

}

