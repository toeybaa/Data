package tree;

/**
 * Created by Peth on 3/1/17.
 */
public class BSTRecursive {
    BSTNode root;
    int size;

    public BSTRecursive(BSTNode root, int size) {
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
        return findMin(root);
    }

    public Iterator findMin(BSTNode n) {
        if (n == null)
            return null;
        if (n.left == null) {
            Iterator itr = new TreeIterator(n);
            return itr;
        }
        return findMin(n.left);
    }

    public Iterator find(int v) {
        return find(v, root);
    }

    public Iterator find(int v, BSTNode n) {
        if (n == null)
            return null;
        if (v == n.data)
            return new TreeIterator(n);
        if (v < n.data)
            return find(v, n.left);
        else
            return find(v, n.right);
    }

    public BSTNode insert(int v) {
        return insert(v, root, null);
    }

    // return the node n after v was added into the
    // tree.
    public BSTNode insert(int v, BSTNode n, BSTNode parent) {
        if (n == null) {
            n = new BSTNode(v, null, null, parent);
            size++;
        } else if (v < n.data) {
            n.left = insert(v, n.left, n);
        } else if (v > n.data) {
            n.right = insert(v, n.right, n);
        }
        return n;
    }

    public BSTNode remove(int v) {
        return remove(v, root, null);
    }

    // return the node n after v was removed from the
    //tree
    public BSTNode remove(int v, BSTNode n, BSTNode parent) {
        if (n == null) {
        } // do nothing, there is nothing to be removed
        else if (v < n.data) {
            n.left = remove(v, n.left, n);
        } else if (v > n.data) {
            n.right = remove(v, n.right, n);
        } else {
            if (n.left == null && n.right == null) {
                n.parent = null; //disconnect from above
                n = null; //disconnect from below
                size--;
            } else if (n.left != null && n.right == null) {
                BSTNode n2 = n.left;
                n2.parent = parent;
                n.parent = null; //disconnect from above
                n.left = null; //disconnect from below
                n = n2; //change to the node below
                //to prepare for connection from parent
                size--;
            } else if (n.right != null && n.left == null) {
                BSTNode n2 = n.right;
                n2.parent = parent;
                n.parent = null; //disconnect from above
                n.right = null; //disconnect from below
                n = n2; //change to the node below
                //to prepare for connection from parent
                size--;
            } else {
                TreeIterator i;
                i = (TreeIterator) findMin(n.right);
                int minInRightSubtree = i.currentNode.data;
                n.data = minInRightSubtree;
                n.right = remove(minInRightSubtree, n.right, n);
            }
        }
        return n;
    } // end of method.

    public int[] treeToArray(BSTNode n) {
        if (size == 0) return null;
        int a[] = new int[size];
        TreeIterator itr = (TreeIterator) findMin();
        int i = 0;
        while (!isEmpty()) {
            a[i] = itr.currentNode.data;
            remove(a[i]);
            itr = (TreeIterator) findMin();
            i++;
        }
        return a;
    }

    public int[] treetoArray2(BSTNode n) throws Exception {
        if (size == 0) return null;
        int a[] = new int[size];
        TreeIterator itr = (TreeIterator) findMin();
        int i = 0;
        while (itr.hasNext()) {
            a[i] = itr.currentNode.data;
            itr.next();
            i++;
        }
        return a;
    }

    public boolean sameData(BSTRecursive t1, BSTRecursive t2) {
        int a[] = treeToArray(t1.root);
        int b[] = treeToArray(t2.root);
        return true;

    }


    public void constructMostBalanced() {
        int a[] = treeToArray(root);
        constructMostBalanced(a, 0, a.length - 1);
    }

    private void constructMostBalanced(int a[], int start, int end) {
        if (start < 0 || start > a.length - 1 || end < 0 || end > a.length - 1 || end < start) {
            return;
        }
        int mid = (start + end) / 2;
        insert(a[mid]);
        constructMostBalanced(a, start, mid - 1);
        constructMostBalanced(a, mid + 1, end);
    }

    public BSTNode createMirror(BSTNode n, BSTNode p) {
        if (n == null)
            return null;
        BSTNode n2 = new BSTNode(n.data);
        n2.parent = p;
        n2.parent = n.parent;
        n2.left = createMirror(n.right, n2);
        n2.right = createMirror(n.left, n2);

        return n2;
    }

    public boolean isBST() {
        return isBST(this.root);
    }

    public boolean isBST(BSTNode n) {
        if (n == null) {
            return true;
        }
        boolean isLeft = isBST(n.left);
        boolean isRight = isBST(n.right);
        boolean isLess = isMoreThanOrEqual(n, n.left);
        boolean isMore = isLessThanOrEqual(n, n.right);
        return isLeft && isRight && isLess && isMore;
    }

    public boolean isMoreThanOrEqual(BSTNode n, BSTNode n2) {
        if (n2 == null) return true;
        boolean lessOrEqual = n2.data <= n.data;
        return lessOrEqual && isMoreThanOrEqual(n, n2.left) && isMoreThanOrEqual(n, n2.right);
    }

    public boolean isLessThanOrEqual(BSTNode n, BSTNode n2) {
        if (n2 == null) return true;
        boolean moreOrEqual = n2.data >= n.data;
        return moreOrEqual && isLessThanOrEqual(n, n2.left) && isLessThanOrEqual(n, n2.right);
    }

    private int height(BSTNode n) {
        if (n == null) return -1;
        if (n.left == null && n.right == null) return 0;
        if (height(n.left) >= height(n.right)) {
            return height(n.left) + 1;
        } else {
            return height(n.right) + 1;
        }
    }

    public int numNodes(){
        return numNodes(this.root);
    }
    public int numNodes(BSTNode n){
        if (n == null) return 0;
        if (n.left == null && n.right == null) return 1;
        return(numNodes(n.left) + numNodes(n.right) + 1);
    }

    public int numLeaves() {
        return numLeaves(this.root);
    }

    public int numLeaves(BSTNode n){
        if (n == null) return 0;
        if (n.left == null && n.right == null) return 1;
        return(numLeaves(n.left) + numLeaves(n.right));

    }

    public BSTNode findParent(BSTNode n, BSTNode d, BSTNode parent) {
        if (n == null) return null;
        if (d == null) return null;
        if (n == d) return parent;
        if (d.data >= n.data) {
            return findParent(n.right, d, n);
        } else {
            return findParent(n.left, d, n);
        }
    }
}// end of class BSTRecursive.



