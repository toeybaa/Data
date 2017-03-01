package linkedlist;

/**
 * Created by Peth on 2/12/17.
 */
public class ListNode {
    int data;
    ListNode nextNode;

    ListNode(int data){
        this(data,null);
    }
    ListNode(int data, ListNode n){
        this.data = data;
        nextNode = n;
    }
}
