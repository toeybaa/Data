package stack;

/**
 * Created by Peth on 2/26/17.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        StackLinkedList a = new StackLinkedList();
        StackLinkedList b = new StackLinkedList();
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);
        b.push(3);
        b.push(1);
        b.push(3);
        b.push(4);
        b.push(1);
        print(b);
//        System.out.print(b.isContain(3));
        b.removeBottom();
        print(b);


    }


    public static void print(MyStack s) throws Exception {
        if (s.isEmpty()) System.out.print("\nStack is Empty\n");

        MyStack temp = new StackLinkedList();

        while (!s.isEmpty()) {
            int t = s.top();
            temp.push(t);
            System.out.println(t);
            s.pop();
        }

        while (!temp.isEmpty()) {
            int j = temp.top();
            s.push(j);
            temp.pop();
        }
    }
}

