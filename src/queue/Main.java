package queue;

/**
 * Created by Peth on 2/28/17.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        CustomQueue a = new CustomQueue();
        a.insertLast(7);
        a.insertLast(5);
        a.insertLast(3);
        a.insertLast(6);
        a.insertLast(9);
        a.insertLast(8);
        a.insertLast(4);
        a.insertLast(1);
        a.insertLast(2);
        print(a);
        a.swap(1,1);
        print(a);
    }

    public static void print(MyQueue q) throws Exception {
        MyQueue temp = new QueueLinkedList();
        while (!q.isEmpty()) {
            int i = q.removeFirst();
            System.out.print(i+" ");
            temp.insertLast(i);
            System.out.print("");

        }
        while (!temp.isEmpty()) {
            q.insertLast(temp.removeFirst());
        }

    }
}
