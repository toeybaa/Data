package linkedlist;

/**
 * Created by Peth on 2/12/17.
 */
public class LinkedList {
    ListNode header;
    static int HEADER_MARKER = -9999999;

    public LinkedList() {
        header = new ListNode(HEADER_MARKER);
    }

    public int find(int value) throws Exception {
        Iterator itr = new ListIterator(header);
        int index = -1;
        while (itr.hasNext()) {
            int v = itr.next();
            index++;
            if (v == value)
                //return the position of value.
                return index;
        }
        //return -1 if the value is not in the list.
        return -1;
    }

    public int findKth(int kthPosition) throws Exception {
        //If the position number is negative (impossible)
        if (kthPosition < 0)
            throw new Exception();

        Iterator itr = new ListIterator(header);
        int index = -1;
        while (itr.hasNext()) {
            int v = itr.next();
            index++;
            if (index == kthPosition)
                return v;
        }
        throw new Exception();
    }

    public void insert(int value, Iterator p) throws Exception {
        if (p == null || !(p instanceof ListIterator))
            throw new Exception();
        ListIterator p2 = (ListIterator) p;
        if (p2.currentNode == null) throw new Exception();
        ListNode n =
                new ListNode(value, p2.currentNode.nextNode);
        p2.currentNode.nextNode = n;
    }

    public void remove(int value) throws Exception {
        Iterator p = findPrevious(value);
        if (p == null)
            return;
        remove(p);
    }

    public Iterator findPrevious(int value) throws Exception {
        Iterator itr1 = new ListIterator(header);
        Iterator itr2 = new ListIterator(header);
        if (!itr2.hasNext())
            return null;
        int currentData = itr2.next();
        while (currentData != value && itr2.hasNext()) {
            currentData = itr2.next();
            itr1.next();
        }
        if (currentData == value)
            return itr1;
        return null;
    }

    public void remove(Iterator p) {
        if (p == null || !(p instanceof ListIterator))
            return;
        ListIterator p2 = (ListIterator) p;
        if (p2.currentNode == null ||
                p2.currentNode.nextNode == null)
            return;
        p2.currentNode.nextNode =
                p2.currentNode.nextNode.nextNode;
    }

    public boolean isEmpty() {
        return header.nextNode == null;
    }

    public void makeEmpty() {
        header.nextNode = null;
    }

    public int head() throws Exception {
        if (isEmpty())
            throw new Exception();
        return header.nextNode.data;

    }

    public LinkedList tail() throws Exception {
        if (isEmpty())
            throw new Exception();

        // Now create a copy of the list
        // so that the original does not change.
        // Copy everything except the first data
        // to the new list.
        LinkedList list2 = new LinkedList();
        Iterator p1 = new ListIterator(header.nextNode);
        Iterator p2 = new ListIterator(list2.header);
        while (p1.hasNext()) {
            int data = p1.next();
            list2.insert(data, p2);
            p2.next();
        }
        return list2;
    }

    public void append(LinkedList list2) throws Exception {
        Iterator p1 = new ListIterator(header);
        Iterator p2 = new ListIterator(list2.header);
        //move iterator to the end of our list.
        while (p1.hasNext())
            p1.next();

        //then copy everything from list2 to our list.
        while (p2.hasNext()) {
            insert(p2.next(), p1);
            p1.next();
        }
    }

    public void moveToFront(ListNode n) throws Exception {
        ListIterator p = (ListIterator) findPrevious(n.data);
        p.currentNode.nextNode = n.nextNode;
        n.nextNode = header.nextNode;
        header.nextNode = n;
    }

    public void setify(){
        ListIterator i = new ListIterator(header.nextNode);
        ListIterator j = new ListIterator(header.nextNode);
        while(i.hasNext()){
            int tempi = i.currentNode.data;
            while(j.hasNext()){
                int tempj = j.currentNode.nextNode.data;
                if(tempi == tempj){
                    remove(j);
                }
                if(j.hasNext()){
                    j.next();
                }
            }
            if(i.hasNext()){
                i.next();
            }
            j.currentNode = i.currentNode;
        }
    }

    public void swap(int x, int y) {
        ListIterator xp = new ListIterator(header);
        ListIterator yp = new ListIterator(header);
        int countx = -1;
        int county = -1;
        while (xp.hasNext() && countx < x) {
            xp.next();
            countx++;
        }
        if (countx != x) {
            return;
        }
        while (yp.hasNext() && county < y) {
            yp.next();
            county++;
        }
        if (county != y) {
            return;
        }
        int temp = yp.currentNode.data;
        yp.currentNode.data = xp.currentNode.data;
        xp.currentNode.data = temp;
    }

    public void evenOdds() throws Exception {
        ListIterator itr = new ListIterator(header);
        ListIterator itr2 = new ListIterator(header);
        ListIterator itrb = new ListIterator(header.nextNode);
        while (itr2.hasNext()) {
            itr2.next();
        }
        while (itr.hasNext() && itr2.hasNext() && itr.currentNode != itr2.currentNode) {
            int i = itrb.currentNode.data;
            if (i % 2 != 0) {
                itrb.next();
                remove(itr);
                insert(i, itr2);
            } else {
                itr.next();
                itrb.next();
            }
        }
    }
}
    