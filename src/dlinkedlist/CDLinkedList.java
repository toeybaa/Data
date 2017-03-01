package dlinkedlist;

import queue.EmptyQueueException;

/**
 * Created by Peth on 2/12/17.
 */
public class CDLinkedList {
    public DListNode header;
    int size;
    static final int HEADERVALUE = -9999999;

    public CDLinkedList() throws Exception {
        size = 0;
        header = new DListNode(HEADERVALUE);
        makeEmpty(); //necessary, otherwise
        // next/previous node will be null.
    }

    public boolean isEmpty() {
        return header.nextNode == header;
    }

    public void makeEmpty() {
        header.nextNode = header;
        header.previousNode = header;
    }

    public int size() {
        return size;
    }

    public int find(int value) throws Exception {
        Iterator itr = new DListIterator(header);
        int index = -1;
        while (itr.hasNext()) {
            int v = itr.next();
            index++;
            DListIterator itr2 = (DListIterator) itr;
            if (itr2.currentNode == header)//exit cond.
                return -1;
            if (v == value)
                //return position of the value.
                return index;
        }
        return -1;
    }

    public int findKth(int kthPosition) throws Exception {
        if (kthPosition < 0)
            throw new Exception();
        // exit the method if the position is
        // less than the first possible
        // position, throwing exception in the
        //process.
        Iterator itr = new DListIterator(header);
        int index = -1;
        while (itr.hasNext()) {
            int v = itr.next();
            index++;
            DListIterator itr2 = (DListIterator) itr;
            if (itr2.currentNode == header)
                throw new Exception();
            if (index == kthPosition)
                return v;
        }
        throw new Exception();
    }

    public void insert(int value, Iterator p) throws Exception {
        if (p == null || !(p instanceof DListIterator))
            throw new Exception();
        DListIterator p2 = (DListIterator) p;
        if (p2.currentNode == null)
            throw new Exception();

        DListIterator p3 = new
                DListIterator(p2.currentNode.nextNode);
        DListNode n;
        n = new DListNode(value, p3.currentNode, p2.currentNode);
        p2.currentNode.nextNode = n;
        p3.currentNode.previousNode = n;
        size++;
    }

    // remove the first instance of the given data.
    public void remove(int value) throws Exception {
        Iterator p = findPrevious(value);
        if (p == null)
            return;
        remove(p);
    }

    // Return iterator at position before the first
    // position that stores value.
    // If the value is not found, return null.
    public Iterator findPrevious(int value) throws Exception {
        if (isEmpty())
            return null;
        Iterator itr1 = new DListIterator(header);
        Iterator itr2 = new DListIterator(header);
        int currentData = itr2.next();
        while (currentData != value) {
            currentData = itr2.next();
            itr1.next();
            if (((DListIterator) itr2).currentNode ==
                    header)
                return null;
        }
        if (currentData == value)
            return itr1;
        return null;
    }

    //Remove content at position just after the given
    // iterator. Skip header if found.
    public void remove(Iterator p) {
        if (isEmpty())
            return;
        if (p == null || !(p instanceof DListIterator))
            return;
        DListIterator p2 = (DListIterator) p;
        if (p2.currentNode == null)
            return;
        if (p2.currentNode.nextNode == header)
            p2.currentNode = header;
        DListIterator p3;
        p3 = new
                DListIterator(p2.currentNode.nextNode.nextNode);

        p2.currentNode.nextNode = p3.currentNode;
        p3.currentNode.previousNode = p2.currentNode;
        size--;
    }

    // Remove data at position p.
    // if p points to header or the list is empty, do
    // nothing.
    public void removeAt(Iterator p) throws Exception {
        if (isEmpty() || p == null
                || !(p instanceof DListIterator)
                || ((DListIterator) p).currentNode == null
                || ((DListIterator) p).currentNode ==
                header)
            return;
        DListIterator p2
                = (DListIterator) (findPrevious(p));
        remove(p2);
    }

    //return iterator pointing to location before p.
    public Iterator findPrevious(Iterator p) throws Exception {
        if (p == null)
            return null;
        if (!(p instanceof DListIterator))
            return null;
        if (((DListIterator) p).currentNode == null)
            return null;
        DListIterator p1 = ((DListIterator) p);
        DListIterator p2 = new
                DListIterator(p1.currentNode.previousNode);

        return p2;
    }

    public CDLinkedList reverseList() throws Exception {
        DListIterator i = new DListIterator(header.previousNode);
        CDLinkedList list2 = new CDLinkedList();
        DListIterator j = new DListIterator(list2.header);
        for (int k = 1; k <= this.size; k++) {
            int v = i.previous();
            list2.insert(v, j);
            j.next();
        }
        return list2;
    }

    public void removeBefore(DListIterator p) throws Exception {
        DListIterator i = new DListIterator(p.currentNode.previousNode);
        if (!(p instanceof DListIterator)) return;
        if (i.currentNode == header) {
            return;
        }
        removeAt(i);
    }

    public void removeMin() throws Exception {
        DListIterator i = new DListIterator(header.nextNode);
        DListNode min = null;
        while (i.currentNode != header) {
            if (min == null || i.currentNode.data < min.data) {
                min = i.currentNode;
            }
            i.next();
        }
        if (min != null) {
            removeAt(new DListIterator(min));
        }
    }

    public CDLinkedList partition(int value) throws Exception {
        CDLinkedList list = new CDLinkedList();
        DListIterator i = new DListIterator(header.nextNode);
        DListIterator p = new DListIterator(list.header);
        while (i.currentNode != header) {
            if (i.currentNode.data > value) {
                int temp = i.currentNode.data;
                removeAt(i);
                list.insert(temp, p);
                p.next();
            }
            i.next();
        }
        return list;
    }

    public int removeAtLast() throws Exception {
        DListIterator i = new DListIterator(header);
        i.previous();
        int temp = i.previous();
        i.currentNode.nextNode = header;
        header.previousNode = i.currentNode;
        return temp;
    }

    public void swapChunk(DListIterator start, DListIterator end, DListIterator p) throws Exception {
        DListIterator beforep = new DListIterator(p.currentNode.previousNode);
        DListIterator beforestart = new DListIterator(start.currentNode.previousNode);
        while (start.currentNode != end.currentNode) {
            insert(start.currentNode.data, beforep);
            beforep.next();
            start.next();
            remove(beforestart);
        }
        insert(start.currentNode.data, beforep);
        beforep.next();
        start.next();
        remove(beforestart);
    }
}
