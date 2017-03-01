package tree;

/**
 * Created by Peth on 3/1/17.
 */
public class BST {
    BSTNode root;
    int size;

    public BST() {
        root = null;
        size = 0;
    }

    public BST(BSTNode root, int size) {
        this.root = root;
        this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        root = null;
        size = 0;
    }

    public Iterator findMin() {
        BSTNode temp = root;
        if (temp == null)
            return null;
        while (temp.left != null) {
            temp = temp.left;
        }
        Iterator itr = new TreeIterator(temp);
        return itr;
    }

    public Iterator findMax() {
        BSTNode temp = root;
        if (temp == null)
            return null;
        while (temp.right != null) {
            temp = temp.right;
        }
        Iterator itr = new TreeIterator(temp);
        return itr;
    }

    public Iterator find(int v) {
        BSTNode temp = root;

        while (temp != null && temp.data != v) {
            if (v < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (temp == null) // not found
            return null;
        return new TreeIterator(temp);
    }

    public Iterator insert(int v) {
        BSTNode parent = null;
        BSTNode temp = root;

        // This first part is almost the same as find,
        // but it has an extra pointer called parent.
        while (temp != null && temp.data != v) {
            if (v < temp.data) {
                parent = temp;
                temp = temp.left;

            } else {
                parent = temp;
                temp = temp.right;

            }
        }

        if (temp == null) {
            BSTNode n = new BSTNode(v, null, null, parent);
            if (parent == null) {
                root = n;
            } else if (v < parent.data) {
                parent.left = n;
            } else {
                parent.right = n;
            }
            size++;
            return new TreeIterator(n);
        } else {
            // we do nothing since
            // we don't want to add duplicated data.
            return null;
        }

    }

    public void remove(int v) {
        BSTNode parent = null;
        BSTNode temp = root;

        TreeIterator i = (TreeIterator) find(v);
        if (i == null) { // not found, we can not remove it
            return;
        }

        temp = i.currentNode;
        parent = temp.parent;

        // otherwise, we remove the value.
        size--;
        if (temp.left == null && temp.right == null) {// both subtrees are empty
            if (parent == null) {
                root = null;
            } else if (parent.left == temp) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (temp.left == null && temp.right != null) {// only right child
            if (parent == null) {
                root = temp.right;
                root.parent = null;
            } else if (parent.right == temp) {
                BSTNode n = temp.right;
                n.parent = parent;
                parent.right = n;
                // temp.right = null;
                // temp.parent = null;
            } else {// parent.left == temp
                BSTNode n = temp.right;
                n.parent = parent;
                parent.left = n;
            }
        } else if (temp.right == null && temp.left != null) {
            if (parent == null) {
                root = temp.left;
                root.parent = null;
            } else if (parent.right == temp) {
                BSTNode n = temp.left;
                n.parent = parent;
                parent.right = n;
            } else {
                BSTNode n = temp.left;
                n.parent = parent;
                parent.left = n;

            }

        } else {// temp has two subtrees
            BSTNode n = temp.right;
            TreeIterator itr = findMin(n);
            BSTNode minInSubtree = itr.currentNode;

            temp.data = minInSubtree.data;

            BSTNode parentOfMin = minInSubtree.parent;
            if (parentOfMin.left == minInSubtree) {
                parentOfMin.left = minInSubtree.right;

            } else { // parentOfMin.right == minInSubtree
                parentOfMin.right = minInSubtree.right;

            }

            if (minInSubtree.right != null) {
                minInSubtree.right.parent = parentOfMin;
            }

        }
    }

    private TreeIterator findMin(BSTNode n) {
        BSTNode temp = n;
        while (temp.left != null) {
            temp = temp.left;
        }
        TreeIterator itr = new TreeIterator(temp);
        return itr;
    }

    public void union(BST t) throws Exception {
        //write code for this method


    }

    public void intersect(BST t) throws Exception {
        //write code for this method

    }

    public int removeBefore(int v) throws Exception
    {
        TreeIterator i = (TreeIterator) (find(v));
        if (i == null) {
            throw new Exception();
        }
        if (i.hasPrevious()) {
            i.previous();
        } else {
            throw new Exception();
        }
        if (i.hasPrevious()) {
            return i.previous();
        } else {
            throw new Exception();
        }
    }

    public boolean isBST() throws Exception {
        TreeIterator i = (TreeIterator)(findMin());
        if (i == null){
            return true;
        }
        int currentdata = i.currentNode.data;
        while(i.hasNext()){
            int nextData = i.next();
            if(currentdata > nextData){
                return false;
            }
            currentdata = nextData;

        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        BSTNode r = new BSTNode(7);
        BST t = new BST(r, 1);
        t.insert(3);
        t.insert(11);
        t.insert(2);
        t.insert(5);
        t.insert(8);

        BSTNode r2 = new BSTNode(4);
        BST t2 = new BST(r2, 1);
        t2.insert(8);
        t2.insert(1);
        t2.insert(10);
        t2.insert(5);
        t2.insert(2);
        t2.insert(0);

        System.out.println("Testing Union - The first tree is:");
        BTreePrinter.printNode(t.root);
        System.out.println("The second tree is:");
        BTreePrinter.printNode(t2.root);
        t.union(t2);

        System.out.println("The tree after union is:");
        BTreePrinter.printNode(t.root);

        System.out.println("Making the first tree empty before union, then union with second tree,");
        System.out.println("The union result is:");
        t.makeEmpty();
        t.union(t2);
        BTreePrinter.printNode(t.root);

        System.out.println("Now testing intersection!, t and t2 as before");
        r = new BSTNode(7);
        t = new BST(r, 1);
        t.insert(3);
        t.insert(11);
        t.insert(2);
        t.insert(5);
        t.insert(8);

        r2 = new BSTNode(4);
        t2 = new BST(r2, 1);
        t2.insert(8);
        t2.insert(1);
        t2.insert(10);
        t2.insert(5);
        t2.insert(2);
        t2.insert(0);

        System.out.println("The intersection result is:");
        t.intersect(t2);
        BTreePrinter.printNode(t.root);


        System.out.println("Now testing intersection!, t is empty, t2 is as before");
        t.makeEmpty();
        System.out.println("t is:");
        BTreePrinter.printNode(t.root);
        System.out.println("t2 is:");
        BTreePrinter.printNode(t2.root);
        t.intersect(t2);
        System.out.println("The intersection is: ");
        BTreePrinter.printNode(t.root);


        System.out.println("Now testing intersection!, t2 is empty but t has data as before,");
        t2 = new BST();
        System.out.println("t2 is:");
        BTreePrinter.printNode(t2.root);
        r = new BSTNode(7);
        t = new BST(r, 1);
        t.insert(3);
        t.insert(11);
        t.insert(2);
        t.insert(5);
        t.insert(8);
        t.insert(6);
        t.insert(10);
        System.out.println("t is:");
        BTreePrinter.printNode(t.root);
        t.intersect(t2);
        System.out.println("The intersection is: ");
        BTreePrinter.printNode(t.root);

        System.out.println("Now testing intersection!, t and t2 as new tree");
        r = new BSTNode(11);
        t = new BST(r, 1);
        t.insert(8);
        t.insert(15);
        t.insert(3);
        t.insert(9);
        t.insert(13);
        t.insert(19);
        System.out.println("t is:");
        BTreePrinter.printNode(t.root);

        r2 = new BSTNode(8);
        t2 = new BST(r2, 1);
        t2.insert(4);
        t2.insert(19);
        t2.insert(3);
        t2.insert(6);
        t2.insert(11);
        t2.insert(20);
        t2.insert(15);
        System.out.println("t2 is:");
        BTreePrinter.printNode(t2.root);

        System.out.println("The intersection result is:");
        t.intersect(t2);
        BTreePrinter.printNode(t.root);


    }
}

