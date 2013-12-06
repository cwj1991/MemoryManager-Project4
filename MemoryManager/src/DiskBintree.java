public class DiskBintree
{

    public static void main( String[] args )
    {

        startController( args[0], args[1], args[2] );

    }
/**
 *  Starts a controller
 * @param textFile
 * @param numBuffers
 * @param buffSize
 */
    private static void startController( String textFile, String numBuffers, String buffSize )
    {
        Controller myController = new Controller( textFile, numBuffers, buffSize);

    }

}