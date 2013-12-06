import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class Controller
{

    protected String commandFile;
    protected Integer bufferSize;
    protected Integer numberOfBuffers;

    protected static Bintree<Point2D.Double, String> binTree =
            new Bintree<Point2D.Double, String>();

    /**
     * Parameterized constructor
     *
     * @param textFile
     * @param numBuffers
     * @param buffSize
     */
    protected Controller( String textFile, String numBuffers, String buffSize )
    {
        commandFile = textFile;
        bufferSize = Integer.parseInt( buffSize );
        numberOfBuffers = Integer.parseInt( numBuffers );
        // TODO: Create bintree

        try
        {
            readFromFile( commandFile );
        } catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Reads the command file
     *
     * @param file
     * @throws IOException
     */
    public void readFromFile( String file ) throws IOException
    {
        // Loads users from .txt file
        BufferedReader br = new BufferedReader( new FileReader( file ) );
        String line;
        while ( (line = br.readLine()) != null )
        {

            if ( line.startsWith( "add" ) )

            {
                line = line.substring( 4 );
                // split by spaces
                String[] parameters = line.split( "\\s+" );
                Double x = Double.parseDouble( parameters[0] );
                Double y = Double.parseDouble( parameters[1] );
                Point2D.Double coordinates = new Point2D.Double();
                coordinates.setLocation( x, y );
                String name = parameters[2];
                // two doubles and string
                // bintree.add(coordinates,name);
                System.out.println( coordinates  + " " + name  );
            }

            else if ( line.startsWith( "search" ) )
            {
                line = line.substring( 7 );
            }

            else if ( line.startsWith( "debug" ) )
            {
                System.out.println( line );
            }

            else if ( line.startsWith( "delete" ) )
            {
                line = line.substring( 7 );
                String[] parameters = line.split( "\\s+" );
                Double x = Double.parseDouble( parameters[0] );
                Double y = Double.parseDouble( parameters[1] );
                Point2D.Double coordinates = new Point2D.Double();
                coordinates.setLocation( x, y );
                String name = parameters[2];
                // two doubles and string
                // bintree.add(coordinates,name);
                System.out.println( coordinates  + " " + name  );
            }

        }
        br.close();

    }

}

