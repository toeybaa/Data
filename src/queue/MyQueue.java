package queue;

/**
 * Created by Peth on 2/27/17.
 */
public interface MyQueue {

         //return the first data.
         public int front() throws Exception, EmptyQueueException;

         //return the last data.
         public int back() throws Exception;

         //remove the first data (return its value too).
         public int removeFirst() throws Exception;

         //insert new data after the last data.
         public void insertLast(int data) throws Exception;

         //check if the queue is empty.
         public boolean isEmpty();

         //check if the queue has no more space to store new
         //data.
         public boolean isFull();

         //return the number of data currently stored in the
         //queue.
         public int size();
 }