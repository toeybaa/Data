package queue;

/**
 * Created by Peth on 2/27/17.
 */
public class DeQArray extends QueueArray implements DeQ {
    public int removeLast() throws Exception {
        int data = back();
        size--; //change all fields to protected!
        return data;
    }

    public void insertFirst(int data) throws Exception {
        if (isFull())
            doubleCapacity();
        front = front - 1;
        if (front < 0)
            front = theArray.length - 1;
        theArray[front] = data;
        size++;
    }
}
