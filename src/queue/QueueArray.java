package queue;

import java.io.*;

/**
 * Created by Peth on 2/27/17.
 */


public class QueueArray implements MyQueue {
    public int[] theArray;
    public int size;//number of currently stored data.
    public int front; //index of the first data.
    static final int DEFAULT_CAPACITY = 5;

    public QueueArray() {
        this(DEFAULT_CAPACITY);
    }

    public QueueArray(int capacity) {
        theArray = new int[capacity];
        size = 0;
        front = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == theArray.length;
    }

    public void makeEmpty() {
        size = 0;
        front = -1;
    }

    public int size() {
        return size;
    }

    public int front() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return theArray[front];
    }

    public int back() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return theArray[(front + size - 1) %
                theArray.length];
    }

    public int removeFirst() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        size--;
        int frontItem = theArray[front];
        front = (front + 1) % theArray.length;
        return frontItem;
    }

    public void insertLast(int data) throws EmptyQueueException {
        if (isFull())
            doubleCapacity();
        theArray[(front + size) % theArray.length] = data;
        size++;
    }

    // resize array to twice its original size.
    public void doubleCapacity() {
        int[] temp = new int[theArray.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = theArray[(front + i) % theArray.length];
        }
        theArray = temp;
        front = 0;
    }
}