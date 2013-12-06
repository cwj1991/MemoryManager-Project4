import java.nio.ShortBuffer;

import java.nio.ByteBuffer;

import java.io.IOException;

import java.io.RandomAccessFile;

// -------------------------------------------------------------------------
/**
 * This class is a BufferPool (Cache) used for heap sorting a binary file.
 *
 * @author Curtis Johnson
 * @version Oct 30, 2013
 */
public class BufferPool
{

    private AList<BufferObject> pool;
    private RandomAccessFile disk;
    private int recordSize;
    private int blockSize;
    private int maxBuffs;
    private int cacheHits = 0;
    private int cacheMisses = 0;
    private int diskReads = 0;
    private int diskWrites = 0;
    /**
     * temporary position.
     */
    private int tempPos;
    /**
     * this is the byte array for a new bufferObject.
     */
    private byte[] tempBlock;
    /**
     * temporary record
     */
    private byte[] tempRecord;

    // ----------------------------------------------------------
    /**
     * Create a new BufferPool object.
     *
     * @param file
     *            file to be sorted
     * @param maxNumBufs
     *            maximum number of buffers in bufferpool.
     * @param bSize
     *            blocks size
     * @param recSize
     *            record size
     */
    public BufferPool( RandomAccessFile file, int maxNumBufs, int bSize,
            int recSize )
    {
        this.recordSize = recSize;
        this.blockSize = bSize;
        this.maxBuffs = maxNumBufs;
        this.pool = new AList<BufferObject>( maxNumBufs );
        this.tempBlock = new byte[blockSize];
        this.tempRecord = new byte[recordSize];
        disk = file;

    }

    // ----------------------------------------------------------
    /**
     * Closes the file.
     *
     * @throws IOException
     */
    public void close() throws IOException
    {
        disk.close();
    }

    // ----------------------------------------------------------
    /**
     * Creates a new bufferobject in the pool and marks it as most recently
     * used.
     *
     * @param pos
     *            position of the record we want for heap sort
     * @throws IOException
     */
    public void createNewBlock( int pos ) throws IOException
    {
        this.diskReads++;
        int start = (pos / this.blockSize) * this.blockSize;
        tempBlock = new byte[blockSize];

        // read from file
        disk.seek( start );
        disk.read( tempBlock, 0, this.blockSize );
        // set the block id
        int blockId = pos / this.blockSize;
        // insert the new block in the array list.
        pool.insert( new BufferObject( this.tempBlock, blockId ) );
    }

    // ----------------------------------------------------------
    /**
     * Determines if the block need is in the bufferpool already.
     *
     * @param position
     * @return
     */
    private int containsBlock( int position )
    {
        int contains = -1;
        for ( int i = 0; i < pool.length(); i++ )
        {
            pool.moveToPos( i );
            if ( (position) == pool.getValue().getBufferId() )
            {
                contains = i;
                this.cacheHits++;
            }
        }
        if ( contains == -1 )
        {
            this.cacheMisses++;
        }
        return contains;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param position
     *            position to read the record from
     * @return returns the byte array containing the record at the given
     *         position.
     * @throws IOException
     */
    public byte[] readRecord( int position ) throws IOException
    {
        this.tempPos = position;
        tempRecord = new byte[recordSize];
        int contained = containsBlock( this.tempPos / this.blockSize );
        if ( contained >= 0 )
        {
            pool.moveToPos( contained );
            tempRecord = pool.getValue().readRecord( this.tempPos );
            pool.mostRecentUsed( contained );
        }
        else
        {
            if ( pool.length() == this.maxBuffs )
            {
                pool.moveToEnd();
                if ( pool.getValue().isDirtyBit() )
                {
                    this.diskWrites++;
                    disk.seek( pool.getValue().getBufferId() * blockSize );
                    disk.write( pool.getValue().writeBlock(), 0, this.blockSize );
                }
            }
            createNewBlock( this.tempPos );
            pool.moveToStart();
            tempRecord = pool.getValue().readRecord( this.tempPos );
        }

        return tempRecord;
    }

    // ----------------------------------------------------------
    /**
     * Writes a record to the bufferObject in the bufferpool.
     *
     * @param position
     *            position for the write to occur
     * @param record
     *            record to be written.
     * @throws IOException
     */
    public void writeRecord( int position, byte[] record ) throws IOException
    {
        this.tempPos = position * recordSize;
        int contained = containsBlock( this.tempPos / this.blockSize );

        if ( contained >= 0 )
        {
            pool.moveToPos( contained );
            pool.getValue().writeRecord( record, this.tempPos );
            pool.mostRecentUsed( contained );
        }
        else
        {
            if ( pool.length() == this.maxBuffs )
            {
                pool.moveToEnd();
                if ( pool.getValue().isDirtyBit() )
                {
                    this.diskWrites++;
                    disk.seek( pool.getValue().getBufferId() * blockSize );
                    disk.write( pool.getValue().writeBlock(), 0, this.blockSize );
                }
            }
            createNewBlock( this.tempPos );
            pool.moveToStart();
            pool.getValue().writeRecord( record, this.tempPos );
        }
    }

    // ----------------------------------------------------------
    /**
     * Flush a block from the bufferPool and writes it to the file.
     *
     * @throws IOException
     */
    public void flushAll() throws IOException
    {
        for ( int i = 0; i < pool.length(); i++ )
        {
            pool.moveToPos( i );
            if ( pool.getValue().isDirtyBit() )
            {
                disk.seek( pool.getValue().getBufferId() * blockSize );
                disk.write( pool.getValue().writeBlock(), 0, this.blockSize );
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Fetches the number of blocks in the bufferpool.
     *
     * @return returns the size of bufferpool in blocks
     */
    public int size()
    {
        return pool.length();
    }

    // ----------------------------------------------------------
    /**
     * Prints out the statistics of the bufferpool.
     */
    public void printStats()
    {
        System.out.println( "Number of cache hits: " + (this.cacheHits) );
        System.out.println( "Number of cache misses: " + this.cacheMisses );
        System.out.println( "Number of disk reads:  " + this.diskReads );
        System.out.println( "Number of disk writes: " + this.diskWrites );
    }

    // ----------------------------------------------------------
    /**
     * Prints the first element of every block in the file with eight records
     * per line.
     *
     * @throws IOException
     */
    public void printRecs() throws IOException
    {
        int numRecs = (int) this.disk.length() / 4;
        int i = 0;
        while ( i < 100 )
        {
            if ( i % 8 == 0 && i != 0 )
            {
                System.out.println();
            }
            byte[] toPrint = new byte[recordSize];
            disk.seek( i * blockSize );
            disk.read( toPrint, 0, recordSize );
            ByteBuffer bb = ByteBuffer.wrap( toPrint );
            ShortBuffer sb = bb.asShortBuffer();
            System.out.print( sb.get( 0 ) + "  " );
            System.out.print( sb.get( 1 ) + "  " );
            i++;
        }
        System.out.println();
        System.out.println( numRecs + " records processed" );
    }
}
