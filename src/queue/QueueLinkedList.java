package queue;

import dlinkedlist.CDLinkedList;
import dlinkedlist.DListIterator;

/**
 * Created by Peth on 2/27/17.
 */
public class QueueLinkedList implements MyQueue {
    CDLinkedList theList;

    public QueueLinkedList() throws Exception {
        this(new CDLinkedList());
    }

    public QueueLinkedList(CDLinkedList theList) {
        this.theList = theList;
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return theList.size();
    }

    public int front() throws Exception {
        if (isEmpty())
            throw new EmptyQueueException();
        return theList.findKth(0);
    }

    public int back() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return theList.header.previousNode.data;
    }

    public int removeFirst() throws Exception {
        if (isEmpty())
            throw new EmptyQueueException();
        DListIterator itr;
        itr = new DListIterator(theList.header);
        int data = itr.next();
        theList.removeAt(itr);
        return data;
    }

    public void insertLast(int data) throws Exception {
        DListIterator itr;
        itr = new DListIterator(theList.header);
        itr.previous();
        theList.insert(data, itr);
    }
}
