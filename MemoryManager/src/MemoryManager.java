import java.nio.ByteBuffer;

public class MemoryManager
{
    private static byte[] array;
    private static int current;

    MemoryManager()
    {
        array = new byte[1000];
        current = 0;
    }

    public byte[] insert( byte[] data )
    {
        Integer currentPos = current;
        if ( data.length > 15 )
        {
            byte[] bb =
                    ByteBuffer.allocate( 2 ).putShort( (short) data.length )
                            .array();
            array[current] = bb[0];
            current++;
            array[current] = bb[1];
            current++;
            ByteBuffer getBack = ByteBuffer.wrap( bb );
            int position = (int) getBack.getShort();
            System.out.println( position );

        }

        for ( int i = 0; i < data.length; i++, current++ )
        {
            array[current] = data[i];
        }

        return ByteBuffer.allocate( 4 ).putInt( currentPos ).array();

    }

    public byte[] getNode( byte[] handle )
    {
        byte[] dataReturned = null;
        int position = ByteBuffer.wrap( handle ).getInt();
        // return internal node
        if ( array[position] == 1 )
        {
            dataReturned = new byte[5];
            for ( int i = 0; i < 5; i++, position++ )
            {
                dataReturned[i] = array[position];
            }
        }

        else if ( array[position] == 0 )
        {
            dataReturned = new byte[9];
            for ( int i = 0; i < 9; i++, position++ )
            {
                dataReturned[i] = array[position];
            }
        }
        // return leaf node

        return dataReturned;
    }
}
