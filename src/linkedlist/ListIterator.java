package linkedlist;

import java.util.NoSuchElementException;

/**
 * Created by Peth on 2/12/17.
 */
public class ListIterator implements Iterator {
    ListNode currentNode;

    public ListIterator(ListNode n) {
        currentNode = n;
    }

    public boolean hasNext() {
        return currentNode.nextNode != null;
    }

    public int next() {
        //Throw exception if the next data
        // does not exist.
        if (!hasNext())
            throw new NoSuchElementException();
        currentNode = currentNode.nextNode;
        return currentNode.data;
    }

    public void set(int value) {
        currentNode.data = value;
    }
}