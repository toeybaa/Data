package queue;

import dlinkedlist.CDLinkedList;
import stack.MyStack;
import stack.StackLinkedList;

/**
 * Created by Peth on 2/28/17.
 */
public class CustomQueue extends QueueLinkedList {

    public CustomQueue() throws Exception {
    }

    public void removeOdd() throws Exception {
        MyQueue temp = new QueueLinkedList();
        while (!isEmpty()) {
            int i = removeFirst();
            if (i % 2 == 0) {
                temp.insertLast(i);
            }
        }
        while (!temp.isEmpty()) {
            insertLast(temp.removeFirst());
        }
    }

    public void removeOddIndex() throws Exception {
        int size = size();
        for (int i = 0; i < size; i++) {
            int temp = removeFirst();
            if (i % 2 != 0) {
                insertLast(temp);
            }
        }
    }

    public void moveBackToFront() throws Exception {
        for (int i = 0; i < size() - 1; i++) {
            insertLast(removeFirst());
        }
    }

    public void moveToFront(int x) throws Exception {
//        for (int i =0; i < size(); i++){
//            if (front() == x){
//                break;
//            } else {
//                insertLast(removeFirst());
//            }
//        }

//        boolean found = false;
//        int size = size();
//        for (int i = 0; i < size; i++) {
//            int temp = removeFirst();
//            if (temp == x && !found) {
//                found = true;
//            } else {
//                insertLast(temp);
//            }
//        }
//        if (found) {
//            insertLast(x);
//            moveBackToFront();
//        }

        MyQueue queue = new QueueLinkedList();
        int size = size();
        for (int i = 0; i < size; i++) {
            int temp = removeFirst();
            if (temp == x) {
                while (!isEmpty()) {
                    queue.insertLast(removeFirst());
                }
                insertLast(temp);
                while (!queue.isEmpty()) {
                    insertLast(queue.removeFirst());
                }
                break;
            } else {
                queue.insertLast(temp);
            }
        }
    }

    public void reverseQueue() throws Exception {
        MyStack stack = new StackLinkedList();
        while (!isEmpty()) {
            stack.push(this.removeFirst());
        }
        while (!stack.isEmpty()) {
            this.insertLast(stack.top());
            stack.pop();
        }
    }

    public static MyQueue merge(MyQueue q1, MyQueue q2) throws Exception {
        MyQueue temp = new QueueLinkedList();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int i = q1.removeFirst();
            int j = q2.removeFirst();

            if (j >= i) {
                temp.insertLast(i);
            } else {
                temp.insertLast(j);
            }

        }
        while (!q1.isEmpty()) {
            temp.insertLast(q1.removeFirst());
        }
        while (!q2.isEmpty()) {
            temp.insertLast(q2.removeFirst());
        }
        return temp;
    }

//    public MyQueue sortQueue() throws Exception {
//        MyQueue q1 = new QueueLinkedList();
//        MyQueue q2 = new QueueLinkedList();
//        while ()
//            return q1;
//
//    }

    public void mergeSort(MyQueue q1, MyQueue q2) throws Exception {


    }

    private static void sort(MyQueue queue) throws Exception {
        if (queue.size() < 2) {
            return;
        }

        if (queue.size() == 2) {
            if (queue.front() > queue.back()) {
                queue.insertLast(queue.removeFirst());
            }
            return;
        }

        els:
        {
            MyQueue queue1 = new QueueLinkedList();
            int size = queue.size();
            for (int i = 0; i < size / 2; i++) {
                queue1.insertLast(queue.removeFirst());
            }
            MyQueue queue2 = new QueueLinkedList();
            for (int i = size / 2; i < size; i++) {
                queue2.insertLast(queue.removeFirst());
            }

            sort(queue1);
            sort(queue2);

            MyQueue merged = merge(queue1, queue2);
            while (!merged.isEmpty()) {
                queue.insertLast(merged.removeFirst());
            }
        }
    }

    public void sort() throws Exception {
        sort(this);
    }

    public void bubbleSort(MyQueue q1) throws Exception {
        int a[] = new int[q1.size()];
        int i = 0;
        int temp;
        while (!q1.isEmpty()) {
            a[i] = q1.removeFirst();
            i++;
        }
        int j;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
        }
        for (int k = 0; k < a.length - 1; k++) {
            q1.insertLast(a[k]);
        }


    }

    public void swap(int a, int b) throws Exception {
        if (a > b){
            int temp = a;
            a = b;
            b = temp;
        }
        if (a == b){
            return;
        }

        int size = size();
        int j;
        int m;
        for (int i = 0; i < a; i++) {
            j = removeFirst();
            insertLast(j);
        }
        j = removeFirst();

        for (int k = a + 1; k < b; k++) {
            m = removeFirst();
            insertLast(m);
        }
        m = removeFirst();

        for (int k = b + 1; k < size; k++) {
            insertLast(removeFirst());
        }

        for (int i = 0; i < a; i++) {
            insertLast(removeFirst());
        }
        insertLast(m);

        for (int x = a + 1; x < b; x++) {
            insertLast(removeFirst());
        }
        insertLast(j);

        for (int x = b + 1; x < size ; x++) {
            insertLast(removeFirst());
        }

    }

}
