import java.nio.ByteBuffer;

import java.awt.geom.Point2D;

import junit.framework.TestCase;

/**
 * Tests the Bintree functionality
 * @version Oct 12, 2013
 */
public class BintreeTest extends TestCase
{
    /**
     * Tests the default constructor
     */
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testBintree()
    {


        @SuppressWarnings( "unused" )
        Bintree treeTest = new Bintree();

        Point2D.Double coordinate= new Point2D.Double( 86.2, 53.8 );
        String name = "Jimmy Daggerstein's Code is so modular";
        byte[] watcherByte = treeTest.watcherAsByte( coordinate, name);
        double coordinateX = ByteBuffer.wrap( watcherByte ).getDouble( 0 );
        double coordinateY = ByteBuffer.wrap( watcherByte ).getDouble( 8 );
        int current = 16;
        byte[] nameByte = new byte[watcherByte.length-16];
        for (int i = 0; i< watcherByte.length-16; i++ ,current++)
        {
           nameByte[i] = watcherByte[current];
        }

        String proccesedName = new String(nameByte);
        assertEquals(coordinate.getX(), 86.2);
        assertEquals(coordinate.getY(), 53.8);
        assertEquals(proccesedName, "Jimmy Daggerstein's Code is so modular");

        // Bintree<Point2D.Double, String> testTree =
        // new Bintree<Point2D.Double, String>();
        //
        // assertEquals( 0, testTree.treeDepth() );
    }

    /**
     * tests insert
     */
    public void testInsert0()
    {
        // Bintree<Point2D.Double, String> testTree =
        // new Bintree<Point2D.Double, String>();
        //
        // Point2D.Double testPoint = new Point2D.Double();
        // testPoint.setLocation( -100, -80 );
        // String name = "John";
        //
        // Point2D.Double testPoint2 = new Point2D.Double();
        // testPoint2.setLocation( 10, -80 );
        // String name2 = "Paul";
        //
        // testTree.insert( testPoint, name );
        //
        // assertEquals( 0, testTree.treeDepth() );
        //
        // testTree.insert( testPoint2, name2 );
        //
        // assertEquals( true, testTree.contains( testPoint2 ) );

    }

    /**
     * Tests the insert to a depth of 6
     */
    public void testInsert()
    {
        // Bintree<Point2D.Double, String> testTree =
        // new Bintree<Point2D.Double, String>();
        //
        // Point2D.Double testPoint = new Point2D.Double();
        // testPoint.setLocation( -140, -80 );
        // String name = "John";
        //
        // Point2D.Double testPoint2 = new Point2D.Double();
        // testPoint2.setLocation( -140, -50 );
        // String name2 = "Paul";
        // TODO
        // testTree.insert( testPoint, name );
        //
        // assertEquals( 0, testTree.treeDepth() );
        //
        // testTree.insert( testPoint2, name2 );
        //
        // assertEquals( 6, testTree.treeDepth() );

    }

    /**
     * tests going right when equal to divide
     */
    public void testInsertLimits()
    {
        // Bintree<Point2D.Double, String> testTree =
        // new Bintree<Point2D.Double, String>();
        //
        // // (0,0) input actual coordiantes( -180, -90 )
        // Point2D.Double testPoint = new Point2D.Double();
        // testPoint.setLocation( 0, 0 );
        // String name = "DoubleRight";
        //
        // Point2D.Double testPoint1 = new Point2D.Double();
        // testPoint1.setLocation( 0, -1 );
        // String name1 = "RightLeft";
        //
        // Point2D.Double testPoint2 = new Point2D.Double();
        // testPoint2.setLocation( -1, -1 );
        // String name2 = "DoubleLeft";
        //
        // Point2D.Double testPoint3 = new Point2D.Double();
        // testPoint3.setLocation( -1, 0 );
        // String name3 = "LeftRight";
        // TODO
        // testTree.insert( testPoint, name );
        // testTree.insert( testPoint1, name1 );
        // testTree.insert( testPoint2, name2 );
        // testTree.insert( testPoint3, name3 );
        //
        // assertEquals( true, testTree.contains( testPoint ) );

    }

    /**
     * Test the contain function
     */
    public void testContains()
    {
        // Bintree<Point2D.Double, String> testTree =
        // new Bintree<Point2D.Double, String>();
        //
        // Point2D.Double testPoint = new Point2D.Double();
        // testPoint.setLocation( -140, -80 );
        // String name = "John";
        //
        // Point2D.Double testPoint2 = new Point2D.Double();
        // testPoint2.setLocation( -140, -50 );
        // String name2 = "Paul";
        //
        // testTree.insert( testPoint, name );
        //
        // assertEquals( true, testTree.contains( testPoint ) );
        //
        // testTree.insert( testPoint2, name2 );
        //  TODO
        // testTree.contains( testPoint2 );
        //
        // assertEquals( true, testTree.contains( testPoint2 ) );
        //
        // Point2D.Double testPointWrong = new Point2D.Double();
        // testPointWrong.setLocation( 0, 0 );
        //
        // assertEquals( false, testTree.contains( testPointWrong ) );

    }

    /**
     * Tests contains again
     */
    public void testContains1()
    {
        // Bintree<String, String> testTree = new Bintree<String, String>();
        //
        // testTree.insert( "YES", "NO" );
        //  TODO
        // testTree.remove( "hello" );
        //
        // assertEquals( false, testTree.contains( "hello" ) );
    }
}
