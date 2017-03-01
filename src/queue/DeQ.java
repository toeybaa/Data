package queue;

/**
 * Created by Peth on 2/27/17.
 */
public interface DeQ extends MyQueue {

    // remove the last data (return its value too).
    public int removeLast() throws Exception;

    // insert new data as the first data.
    public void insertFirst(int data) throws
            Exception;
}
