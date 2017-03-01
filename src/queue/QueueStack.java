package queue;

import stack.MyStack;
import stack.StackLinkedList;

/**
 * Created by Peth on 2/28/17.
 */
public class QueueStack implements MyQueue {

    private MyStack stack;
    private int size;

    public QueueStack() throws Exception {
        stack = new StackLinkedList();
    }

    @Override
    public int front() throws Exception {
        MyStack temp = new StackLinkedList();
        while (!stack.isEmpty()) {
            temp.push(stack.top());
            stack.pop();
        }
        int ret = temp.top();
        while (!temp.isEmpty()) {
            stack.push(temp.top());
            temp.pop();
        }
        return ret;
    }

    @Override
    public int back() throws Exception {
        return stack.top();
    }

    @Override
    public int removeFirst() throws Exception {
        MyStack temp = new StackLinkedList();
        while (!stack.isEmpty()) {
            temp.push(stack.top());
            stack.pop();
        }
        int ret = temp.top();
        temp.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.top());
            temp.pop();
        }
        size--;
        return ret;
    }

    @Override
    public void insertLast(int data) throws Exception {
        stack.push(data);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return stack.isFull();
    }

    @Override
    public int size() {
        return size;
    }
}
