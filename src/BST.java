/**
 * Binary Search Tree (BST) class that stores instances of AccountNodes.
 * The class provides methods for inserting, deleting, and searching nodes in the tree.
 *
 * @author Thabang Mokoena
 * 15 April 2023
 */

public class BST {
    /**
     * The root node of the BST.
     */
    protected AccountNode root;

    /**
     * Creates an empty BST with a null root.
     */
    public BST() {
        root = null;
    }

    /**
     * Inserts a new node with the specified account name and description into the BST.
     *
     * @param accountName the account name of the new node to be inserted
     * @param desc the description of the new node to be inserted
     */
    public void insert(String accountName, String desc) {
        AccountNode newNode = new AccountNode(accountName,desc);
        if (root == null) {
            root = newNode;
        } else {
            root.insert(newNode);
        }
    }

    /**
     * Deletes the node with the specified account name from the BST.
     *
     * @param accountName the account name of the node to be deleted
     */
    public void delete(String accountName) {
        if (root == null) {
            return;
        }
        root = root.delete(accountName);
    }
    /**
     * Searches the BST for the node with the specified account name.
     *
     * @param accountName the account name of the node to be searched for
     * @return the node with the specified account name, or null if not found
     */
    public AccountNode search(String accountName) {
        if (root == null) {
            return null;
        }
        return root.search(accountName);
    }
    /**
     * Prints the contents of the BST in order (left subtree, root, right subtree).
     *
     * @param root the root node of the BST to be printed
     */
    public void inOrder(AccountNode root){
        if (root==null)  return;

        inOrder(root.leftChild);
        System.out.println(root);
        inOrder(root.rightChild);


    }

}