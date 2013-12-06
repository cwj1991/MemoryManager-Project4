import java.nio.ByteBuffer;

import junit.framework.TestCase;


public class MemoryManagerTest extends TestCase
{

    public void testInsert()
    {
    MemoryManager manage = new MemoryManager();
    byte[] data = new byte[16];
    byte[] dataH = new byte[4];
    byte[] empty = new byte[4];
    dataH =  manage.insert(data);
    assertEquals(ByteBuffer.wrap( empty).getInt(), ByteBuffer.wrap(dataH).getInt());
    }

    public void testGetNode()
    {
    MemoryManager manage = new MemoryManager();
    byte[] data = new byte[16];
    byte[] dataH = new byte[4];
    byte[] empty = new byte[4];
    dataH =  manage.insert(data);
    NodeLeaf newLeaf = new NodeLeaf(dataH);
    byte[] currH = new byte[4];
    currH = manage.insert(newLeaf.Serialize());
    newLeaf.setCurrentH( currH );
    byte[] node = new byte[5];
    node = manage.getNode( newLeaf.getCurrentH() );
    assertEquals( node[0], 1);

    byte[] a;
    byte[] b;
    a = ByteBuffer.allocate( 4 ).putInt( 5 ).array();
    b = ByteBuffer.allocate( 4 ).putInt( 3).array();
    boolean equal = true;
    for (int i = 0; i<4; i++)
    {
       if (a[i]!= b[i])
       {
           equal = false;
       }
    }

   assertEquals( equal, false);
    }
}
