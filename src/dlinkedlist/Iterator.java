package dlinkedlist;

/**
 * Created by Peth on 2/12/17.
 */
public interface Iterator {
    public boolean hasNext();

    public boolean hasPrevious();

    // moves iterator to the next position,
    // then returns the value at that new position.
    public int next() throws Exception;

    // returns the value at current position,
    // then moves the iterator back one position.
    public int previous() throws Exception;

    public void set(int value);
}
