/**
 * David Li 
 * V00818631
 * BSTRefBased.java
 * Implementation based off of textbook and lecture slides
 * This program inserts, deletes and retrieves items into the binary search tree
 * The Binary Search Tree is organized alphabetical
 *
 */
import java.util.Iterator;

public class BSTRefBased extends AbstractBinaryTree 
    implements Iterable<WordRefs>
{
    private TreeNode root;

	//constructor
    public BSTRefBased() {
        root = null;
    }

	//constructor with attached subtrees
	//calls the attach subtree methods
    public BSTRefBased(WordRefs item,
        AbstractBinaryTree left,
        AbstractBinaryTree right)
    {
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }

	//check if the root null
    public boolean isEmpty() {
        return (root == null);
    }

	//sets the root to null
    public void makeEmpty() {
        root = null;
    }

	//returns the root 
    protected TreeNode getRoot() {
        return root;
    }

	//sets the root 
    protected void setRoot(TreeNode r) {
        this.root = r;
    }

	//gets the data of the root node
    public WordRefs getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("getRootItem() on empty tree");
        }

        return root.item;
    }

	//changes the root's data
    public void setRootItem(WordRefs item) {
        if (root == null) {
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }

	//attachs left treenode
	//throws exception if a left treenode is already present
    public void attachLeft(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachLeft to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new TreeException("attachLeft to occupied left child");
        }

        root.left = new TreeNode(item, null, null);

        return;
    } 

	//attack left subtree to a node
	//throws an exception if an left subtree is already present
    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) {
            throw new TreeException("attachLeftSubtree to empty tree");
        }

        if (!isEmpty() && root.left != null) {
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }

        root.left = left.getRoot();
        left.makeEmpty();

        return;    
    }

	//attachs right node
	//throws exception if a left node is already present
    public void attachRight(WordRefs item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachRight to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new TreeException("attachRight to occupied right child");
        }

        root.right = new TreeNode(item, null, null);

        return;
    } 

	//attachs right subtree
	//throws an exception if an right subtree is already present
    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) {
            throw new TreeException("attachRightSubtree to empty tree");
        }

        if (!isEmpty() && root.right != null) {
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }

        root.right = right.getRoot();
        right.makeEmpty();

        return;
    }

	//removes the left subtree
	//throws exception if the tree is empty
    public AbstractBinaryTree detachLeftSubtree()
        throws TreeException 
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.left);
        root.left = null;

        return result;
    }

	//removes the right subtree
	//throws exception if the tree is empty
    public AbstractBinaryTree detachRightSubtree()
        throws TreeException
    {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.right);
        root.right = null;

        return result;
    }

	//inserts a new node into the Binary Search Tree
    public void insert(String word) {
		this.root=insertItem(this.root,word);
    }

	//inserts a new item at a certain TreeNode
    protected TreeNode insertItem(TreeNode r, String word) {
		
        if(r == null) {
			return new TreeNode(new WordRefs(word));
		}
		//word is equal to the data in r
		if(word.equals(r.item.getWord())) {
			return r;
		}
		//word is less than to the data in r
		if(word.compareTo(r.item.getWord()) < 0) {
			r.left = insertItem(r.left,word);
		}
		//word is greater than to the data in r
		else {
			r.right = insertItem(r.right,word);
		}
		return r;
    }

	//retrieve WordRefs
    public WordRefs retrieve(String word) throws TreeException {
		try {
			return (retrieveItem(this.root, word)).item;
		}
		catch(NullPointerException e) {
			return null;
		}
    }

	//retrieve an item stored at a treeNode
    protected TreeNode retrieveItem(TreeNode r, String word) {

		//item not found
		if(r == null) {
			return null; //tree is empty
		}
		//word is equal to the data in r
		if(word.equals(r.item.getWord())) {
			return r;
		}
		//word is less than to the data in r
		if(word.compareTo(r.item.getWord()) < 0) {
			return retrieveItem(r.left, word);
		}
		//word is greater than to the data in r
		else {
			return retrieveItem(r.right, word);
		}
    }
	//deletes a word from the list
    public void delete(String word) {
		this.root =deleteItem(this.root,word);
    }

	//deletes an item from the list
	//relinks the binary tree
	//word is the search key
    protected TreeNode deleteItem(TreeNode r, String word) {
		
		//item not found
		if(r == null) {
			return null; 
		}
		else {
			//word is equal to the data in r
			if(word.equals(r.item.getWord())) {
				r= deleteNode(r);
			}
			//word is less than to the data in r
			else if(word.compareTo(r.item.getWord()) < 0) {
				r.left = deleteItem(r.left, word);
			}
			//word is greater than to the data in r
			else {
				r.right = deleteItem(r.right,word);

			}
			return r;
		}
    }

    //deletes an node from the list
    protected TreeNode deleteNode(TreeNode node) {
		
		//leaf node
		if(node.left == null && node.right == null){
			return null;
		}
		//test for no left child
		else if(node.left == null) {
			return node.right;
		}
		else if(node.right == null) {
			return node.left;
		}
		//node with two children
		//finds successor
		else {
			node.item = findLeftMost(node.right).item;
			node.right = deleteLeftMost(node.right);
			return node;
		}//end if
       // return null; 
    }

	//tranverses the tree to fing the leftmost node
    protected TreeNode findLeftMost(TreeNode node) {
		if(node.left == null) {
			return node;
		}
		else {
			return findLeftMost(node.left);
		}
        //return null;
    }

	//deletes the leftmost Node
	//returns subtree of the deleted node
    protected TreeNode deleteLeftMost(TreeNode node) {
		if(node.left == null) {
			return node.right;
		}
		else {
			TreeNode replacementChild = null;
			TreeNode replacementLchild = deleteLeftMost(node.left);
			node.left = replacementLchild;
			return node;
		}//end if 
        //return null;
    }

	//used to iterate
    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
    }
	
	//tests if all the words are in the BST by calling retrieve()
	public static boolean compareAndReport(BSTRefBased given,
         String expected[])
    {
        boolean passed = true;
        String result = "";

        for (int i = 0; i < expected.length; i++) {
			result = (given.retrieve(expected[i])).getWord();
            if (result != expected[i]) {
                break;
            }
        }
        return passed;
    }
	//tests
    public static void main(String args[]) {
        BSTRefBased t;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;

        message = "Test 1: inserting 'humpty' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));

        message = "Test 2: inserting 'humpty', 'dumpty', 'sat' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
		
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		//Test insertion of multiply items
		message = "Test 3: inserting 'humpty', 'dumpty', 'sat', 'a', 'had', 'great', 'fall', 'on', 'wall' --";
		t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
			t.insert("had");
			t.insert("a");
			t.insert("great");
			t.insert("fall");
			t.insert("wall");
			t.insert("on");


            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		message = "Test 4: inserting and retrival 'humpty', 'dumpty', 'sat', 'a', 'had'  --";
		t = new BSTRefBased();
		String test4after[] = {"humpty","dumpty","sat","a","had"};
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
			t.insert("a");
			t.insert("had");
			compareAndReport(t,test4after);
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		message = "Test 5: inserting and retrival 'humpty', 'dumpty', 'sat', 'a', 'had', 'great', 'fall', 'on', 'wall' --";
		t = new BSTRefBased();
		String test5after[] = {"humpty","dumpty","sat","a","had","great","fall","on","wall"};
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
			t.insert("a");
			t.insert("had");
			t.insert("great");
			t.insert("fall");
			t.insert("on");
			t.insert("wall");

			compareAndReport(t,test5after);
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		
		message = "Test 6: inserting and deletion 'humpty', 'dumpty', 'sat', 'a', 'had', 'great', 'fall', 'on', 'wall' --\n ";
		t = new BSTRefBased();
		String test6after[] = {"humpty","a","had","fall","on","wall"};
        try {
			//insertions
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
			t.insert("a");
			t.insert("had");
			t.insert("great");
			t.insert("fall");
			t.insert("on");
			t.insert("wall");

			//deletions
			t.delete("dumpty");

			t.delete("great");

			t.delete("sat");

			compareAndReport(t,test6after);

        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
		
		message = "Test 7: inserting and deletion 'humpty', 'dumpty', 'sat', 'a', 'had', 'great', 'fall', 'on', 'wall' --";
		t = new BSTRefBased();
		String test7after[] = {"humpty","a"};
        try {
			//insertions
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
			t.insert("a");
			t.insert("had");
			t.insert("great");
			t.insert("fall");
			t.insert("on");
			t.insert("wall");

			//deletions
			t.delete("dumpty");
			t.delete("great");
			t.delete("sat");
			t.delete("had");
			t.delete("on");
			t.delete("wall");
			compareAndReport(t,test7after);

        } catch (Exception e) {
            result = false;
        }
		System.out.println(message + (result ? "passed" : "FAILED"));
    }
} 
