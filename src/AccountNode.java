import java.util.Stack;
/**
 * AccountNodes represents a node in a Binary Search Tree (BST) that stores
 * information about a user account, including its name, a description of the
 * account, and a stack of videos that the user has uploaded.
 * It also implements the Comparable interface to allow sorting based on the account name.
 *
 * @author Thabang Mokoena
 * 15 April 2023
 */
public class AccountNode implements Comparable<AccountNode>{
    /**
     * The name of the user account.
     */
    protected String accountName ;
    /**
     * The stack of videos uploaded by the user.
     */
    protected Stack<Video> Videos;

    /**
     * A description of the user account.
     */
    protected String description;

    /**
     * The left child of this node in the BST.
     */
    protected AccountNode leftChild;

    /**
     * The right child of this node in the BST.
     */
    protected AccountNode rightChild;

    /**
     * Constructs an AccountNodes object with the specified name and description.
     * @param Name the account name.
     * @param desc the account description.
     */
    public AccountNode(String Name, String desc) {
        this.accountName = Name;
        this.description=desc;
        this.Videos = new Stack<>();
        this.leftChild=null;
        this.rightChild=null;
    }
    /**
     * Compares this AccountNodes object with the specified AccountNodes object for order.
     * @param name the AccountNodes object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(AccountNode name) {
        return this.accountName.compareTo(name.accountName);
    }

    /**
     * Inserts a new video into the stack of videos.
     * @param name the video name.
     * @param numOfLikes the number of likes the video has.
     * @param desc the video description.
     */
    public void insert(String name , int numOfLikes, String desc ) {
        Videos.push(new Video(name,numOfLikes,desc));
    }
    /**
     * Prints all videos in the stack with the most recent video being printed first.
     */
    public void printVideos(Stack<Video> videos) {
        if(videos.isEmpty())
            return;
        Video recent = videos.peek();
        videos.pop();
        printVideos(videos);
        System.out.println(recent.toString());
        videos.push(recent);
    }
    /**
     * Inserts the specified AccountNodes object into the binary search tree.
     * @param newNode the AccountNodes object to be inserted.
     */
    public void insert(AccountNode newNode) {
        if (newNode.compareTo(this) <= 0) {
            if (leftChild == null) {
                leftChild = newNode;
            } else {
                leftChild.insert(newNode);
            }
        } else {
            if (rightChild == null) {
                rightChild = newNode;
            } else {
                rightChild.insert(newNode);
            }
        }
    }
    /**
     * Deletes the specified account from the binary search tree.
     * @param accountName the name of the account to be deleted.
     * @return the deleted AccountNodes object.
     */
    public AccountNode delete( String accountName) {
        if (accountName.compareTo(this.accountName) < 0) {
            if (leftChild != null) {
                leftChild = leftChild.delete(accountName);
            }
            return this;
        } else if (accountName.compareTo(this.accountName) > 0) {
            if (rightChild != null) {
                rightChild = rightChild.delete(accountName);
            }
            return this;
        } else {
            if (leftChild == null && rightChild == null) {
                return null;
            } else if (leftChild == null) {
                return rightChild;
            } else if (rightChild == null) {
                return leftChild;
            } else {
                AccountNode minNode = rightChild.getMinNode();
                this.accountName = minNode.accountName;
                this.Videos = minNode.Videos;
                rightChild = rightChild.delete(this.accountName);
                return this;
            }
        }
    }
    /**
     * Searches for the specified account from the binary search tree.
     * @param accountName the name of the account to be deleted.
     * @return the searched AccountNodes object.
     */
    public AccountNode search(String accountName) {
        if (accountName.equals(this.accountName)) {
            return this;
        } else if (accountName.compareTo(this.accountName) < 0) {
            if (leftChild == null) {
                return null;
            } else {
                return leftChild.search(accountName);
            }
        } else {
            if (rightChild == null) {
                return null;
            } else {
                return rightChild.search(accountName);
            }
        }
    }
    /**
     * Returns a string representation of the `AccountNode` object.
     *
     * @return A string representation of the `AccountNode` object.
     */
    public String toString(){

        return "AccountName: "+accountName+"\n"+"The decription is: "+description;
    }

    public AccountNode getMinNode() {
        if (leftChild == null) {
            return this;
        } else {
            return leftChild.getMinNode();
        }
    }

    /**
     * The `Video` class represents a video object that is used in the `AccountNodes` class to store video details.
     */

    private static class Video {
        private final int likes;
        private final String title;
        private final String Description;

        /**
         * Constructor to create a new `Video` object.
         *
         * @param name The title of the video.
         * @param numOfLikes The number of likes for the video.
         * @param desc The description of the video.
         */
        public Video(String name , int numOfLikes, String desc ){

            title = name;
            likes= numOfLikes;
            Description=desc;
        }

        /**
         * Returns a string representation of the `Video` object.
         *
         * @return A string representation of the `Video` object.
         */
        public String toString(){


            return"Title: "+this.title+"\nVideo: "+this.Description+"\nNumber of likes: "+this.likes;
        }
    }
}
