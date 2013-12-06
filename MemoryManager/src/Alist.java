/** Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)"
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer
 */

/**
 * Array-based list implementation
 *
 * @author Curtis Johnson (modified)
 * @param <E>
 *            Generic Type E
 * */
class AList<E>
{
    private static final int defaultSize = 10; // Default size
    private int maxSize; // Maximum size of list
    private int listSize; // Current # of list items
    private int curr; // Position of current element
    private E[] listArray; // Array holding list elements

    /** Constructors */
    /** Create a list with the default capacity. */
    AList()
    {
        this( defaultSize );
    }

    /**
     * Create a new list object.
     *
     * @param size
     *            Max # of elements list can contain.
     */
    @SuppressWarnings( "unchecked" )
    // Generic array allocation
    AList( int size )
    {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[size]; // Create listArray
    }

    // ----------------------------------------------------------
    /**
     * Clears the Alist.
     */
    public void clear() // Reinitialize the list
    {
        listSize = curr = 0;
    } // Simply reinitialize values

    /**
     * Insert "it" at current position
     *
     * @param it
     *            item to be inserted.
     * */
    public void insert( E it )
    {
        // check if list is full
        if ( listSize == maxSize )
        {
            moveToEnd();
            remove();
        }

        moveToStart();
        // shift down items
        for ( int i = listSize; i > curr; i-- ) // Shift elements up
        {
            listArray[i] = listArray[i - 1]; // to make room
        }
        moveToStart();
        listArray[curr] = it;
        listSize++; // Increment list size
    }

    /**
     * Append "it" to list
     *
     * @param it
     */
    public void append( E it )
    {
        listArray[listSize++] = it;
    }

    /**
     * Remove and return the current element
     *
     * @return returns item removed
     */
    public E remove()
    {
        if ( (curr < 0) || (curr >= listSize) ) // No current element
        {
            return null;
        }
        E it = listArray[curr]; // Copy the element

        for ( int i = curr; i < listSize - 1; i++ ) // Shift them down
        {
            listArray[i] = listArray[i + 1];
        }
        listSize--; // Decrement size
        return it;
    }

    // ----------------------------------------------------------
    /**
     * Moves cursor to start.
     */
    public void moveToStart()
    {
        curr = 0;
    } // Set to front

    // ----------------------------------------------------------
    /**
     * Moves cursor to end.
     */
    public void moveToEnd()
    {
        curr = listSize - 1;
    } // Set at end

    // ----------------------------------------------------------
    /**
     * Moves cursor to previous.
     */
    public void prev()
    {
        if ( curr != 0 )
        {
            curr--;
        } // Back up
    }

    // ----------------------------------------------------------
    /**
     * Moves cursor to next.
     */
    public void next()
    {
        if ( curr < listSize )
        {
            curr++;
        }
    }

    /** @return List size */
    public int length()
    {
        return listSize;
    }

    /** @return Current position */
    public int currPos()
    {
        return curr;
    }

    /**
     * Set current list position to "pos"
     *
     * @param pos
     *            position
     */
    public void moveToPos( int pos )
    {
        curr = pos;
    }

    /** @return Current element */
    public E getValue()
    {
        return listArray[curr];
    }

    // ----------------------------------------------------------
    /**
     * Puts the most recently used item at the top of the list.
     *
     * @param position
     *            position of item to move to the top.
     */
    public void mostRecentUsed( int position )
    {
        E temp;

        this.moveToPos( position );
        temp = this.getValue();

        moveToStart();
        // shift down items
        for ( int i = position; i > 0; i-- ) // Shift elements up
        {
            listArray[i] = listArray[i - 1]; // to make room
        }
        moveToStart();
        listArray[curr] = temp;
        // Increment list size
    }

}
