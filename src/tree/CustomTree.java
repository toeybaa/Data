package tree;

/**
 * Created by Peth on 3/1/17.
 */
public class CustomTree extends BST {


    public boolean same(BST t1, BST t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 != null && t2 == null) {
            return false;
        }
        if (t1 == null && t2 != null) {
            return false;
        }
        if (t1.size != t2.size){
            return false;
        } else {
            return same(t1.root, t2.root);
        }
    }

    public boolean same(BSTNode n1, BSTNode n2) {
        if (n1 == null && n2==null){
            return false;
        }
        if (n1!=null && n2==null){
            return false;
        }
        if (n1==null && n2!=null){
            return false;
        }
        if (n1.data != n2.data){
            return false;
        } else {
            return same(n1.left,n2.right) && same(n1.right,n2.right);
        }
    }
}
