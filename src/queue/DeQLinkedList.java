package queue;

import dlinkedlist.DListIterator;

/**
 * Created by Peth on 2/27/17.
 */
public class DeQLinkedList extends QueueLinkedList implements DeQ {

    public DeQLinkedList() throws Exception {
    }

    public int removeLast() throws Exception {
        if (isEmpty())
            throw new EmptyQueueException();
        DListIterator itr = new DListIterator(theList.header);
        itr.previous();
        int data = itr.previous();
        theList.remove(itr);
        return data;
    }

    public void insertFirst(int data) throws Exception {
        DListIterator itr = new DListIterator(theList.header);
        theList.insert(data, itr);
    }
}
