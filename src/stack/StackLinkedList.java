package stack;

import dlinkedlist.CDLinkedList;
import dlinkedlist.DListIterator;
import dlinkedlist.Iterator;

/**
 * Created by Peth on 2/26/17.
 */
public class StackLinkedList implements MyStack {
    private CDLinkedList theList;

    // create an empty stack
    public StackLinkedList() throws Exception {
        theList = new CDLinkedList();
    }

    public StackLinkedList(CDLinkedList l) throws Exception {
        super();
        DListIterator iparam;
        iparam = new DListIterator(l.header);
        DListIterator ithis;
        ithis = new DListIterator(theList.header);
        while (iparam.hasNext()) {
            int v = iparam.next();
            if (iparam.currentNode == l.header)
                return;
            theList.insert(v, ithis);
            ithis.next();
        }
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public boolean isFull() {
        return false;
    }

    public void makeEmpty() {
        theList.makeEmpty();
    }

    public int top() throws Exception {
        if (isEmpty())
            throw new Exception();
        return theList.header.nextNode.data;
    }

    public void pop() throws Exception {
        if (isEmpty())
            throw new Exception();
        Iterator itr;
        itr = new DListIterator(theList.header);
        theList.remove(itr);
    }

    public void push(int data) throws Exception {
        Iterator itr;
        itr = new DListIterator(theList.header);
        theList.insert(data, itr);
    }

    public void removeDuplicate() throws Exception {
        StackLinkedList s1 = new StackLinkedList();
        StackLinkedList s2 = new StackLinkedList();
        while (!isEmpty()) {

            s1.push(top());
            s2.push(top());
            pop();
        }
        while (!s1.isEmpty()){
            int i = s1.top();
            s1.pop();
            if(s1.isContain(i)){
            } else {
                this.push(i);
            }
        }
    }

    public void removeBottom() throws Exception {
        StackLinkedList s1 = new StackLinkedList();
        while (!isEmpty()){
            s1.push(this.top());
            pop();
        }
        s1.pop();
        while (!s1.isEmpty()){
            this.push(s1.top());
            s1.pop();
        }
    }

    public void removeMin() throws Exception {
        int min = Integer.MAX_VALUE;
        StackLinkedList s1 = new StackLinkedList();
        while (!isEmpty()){
            int i = top();
            if (i <= min){
                min = i;
            }
            s1.push(i);
            this.pop();
        }
        while (!s1.isEmpty()){
            int j = s1.top();
            s1.pop();
            if (j == min){

            } else {
                this.push(j);
            }
        }
    }

    public boolean isContain(int a) throws Exception {
        StackLinkedList test1 = new StackLinkedList();
        StackLinkedList test2 = new StackLinkedList();
        while (!isEmpty()) {
            test1.push(this.top());
            test2.push(this.top());
            this.pop();
        }
        while (!test2.isEmpty()) {
            this.push(test2.top());
            test2.pop();
        }
        while (!test1.isEmpty()) {
            int b = test1.top();
            test1.pop();
            if (b == a) {
                return true;
            }
        }
        return false;
    }

    public void addNoDuplicate(StackLinkedList s2) throws Exception {
        StackLinkedList ok = new StackLinkedList();
        StackLinkedList dup = new StackLinkedList();
        while (!s2.isEmpty()) {
            int i = s2.top();
            s2.pop();
            if (this.isContain(i)) {
                dup.push(i);
                //    System.out.print("dup");
            } else {
                ok.push(i);
                //    System.out.print("ok");
            }
        }
        while (!ok.isEmpty()) {
            this.push(ok.top());
            ok.pop();
        }
        while (!dup.isEmpty()) {
            s2.push(dup.top());
            dup.pop();
        }
    }


}
