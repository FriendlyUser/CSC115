import java.util.LinkedList;
/**
 * David Li 
 * V00818631
 * BSTIterator.java
 * This program is used to interate through the binary search tree
 * It can do inorder, postorder, or preorder tranversals
 *
 */

public class BSTIterator implements java.util.Iterator<WordRefs> {
    private BSTRefBased t;
    private WordRefs currentItem;
    private LinkedList<WordRefs> list;
	
	//constructor
    public BSTIterator(BSTRefBased t) {
        this.t = t;
        currentItem = null;
        list = new LinkedList<>();
        setInorder();
    }
	
	//checks if the next node exists
    public boolean hasNext() {
        return !list.isEmpty();
    }
	
	//go to next item
    public WordRefs next() throws java.util.NoSuchElementException {
        currentItem = list.remove();
        return currentItem;
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
	
	//set the Binary Search tree to preorder tranversal
    public void setPreorder() {
		list.clear();
        preorder(t.getRoot());
    }
	
	//set the Binary Search tree to Inorder tranversal
    public void setInorder() {
		list.clear();
        inorder(t.getRoot());
    }
	
	//set the Binary Search tree to Postorder tranversal
    public void setPostorder() {
		list.clear();
        postorder(t.getRoot());
    }
	
	//set the Binary Search tree to preorder tranversal
    private void preorder(TreeNode node) {
		if (node != null) {
            list.add(node.item);
            preorder(node.left);
            preorder(node.right);
        }
    }

    private void inorder(TreeNode node) {
		if (node != null) {
            inorder(node.left);
            list.add(node.item);
            inorder(node.right);
        }
    }

    private void postorder(TreeNode node) {
		if (node != null) {
            postorder(node.left);
            postorder(node.right);
            list.add(node.item);
        }
    }

}
