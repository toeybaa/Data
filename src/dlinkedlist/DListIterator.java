package dlinkedlist;

/**
 * Created by Peth on
 */
import java.util.NoSuchElementException;

public class DListIterator implements Iterator {
    public DListNode currentNode; // interested position

    public DListIterator(DListNode theNode) {
        currentNode = theNode;
    }

    public boolean hasNext() {
        // always true for circular list.
        return currentNode.nextNode != null;
    }

    public boolean hasPrevious() {
        // always true for circular list.
        return currentNode.previousNode != null;
    }

    public int next(){
        if (!hasNext())
            throw new NoSuchElementException();
        currentNode = currentNode.nextNode;
        return currentNode.data;
    }

    public int previous() throws Exception {
        if (!hasPrevious())
            throw new NoSuchElementException();
        int data = currentNode.data;
        currentNode = currentNode.previousNode;
        return data;
    }

    public void set(int value) {
        currentNode.data = value;
    }
}