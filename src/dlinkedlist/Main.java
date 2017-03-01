package dlinkedlist;

/**
 * Created by Peth on 2/12/17.
 */
public class Main {
    public static void main (String [] args) throws Exception {
        CDLinkedList a = new CDLinkedList();
        DListIterator p = new DListIterator(a.header);
        DListIterator i = new DListIterator(a.header);
        a.insert(6,p);
        p.next();
        a.insert(3,p);
        p.next();
        a.insert(4,p);
        p.next();
        a.insert(7,p);
        print(a);
        print (a.reverseList());
    }
    public static void print(CDLinkedList cd) {
        if (cd.header.nextNode == null) return;
        for (DListNode d = cd.header.nextNode; d.nextNode != null && d != cd.header; d = d.nextNode) {
            System.out.print(d.data + " ");
        }
        System.out.println();
    }
}
