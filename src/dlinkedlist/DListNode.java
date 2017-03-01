package dlinkedlist;

/**
 * Created by Peth on 2/12/17.
 */
public class DListNode {
    public int data;
    public DListNode nextNode;
    public DListNode previousNode;

    // Constructors
    DListNode(int data) {
        this(data, null, null);
    }

    DListNode(int theElement, DListNode n, DListNode p) {
        data = theElement;
        nextNode = n;
        previousNode = p;
    }
}