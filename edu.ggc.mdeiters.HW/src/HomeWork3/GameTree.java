package HomeWork3;

import java.util.ArrayList;

/**
 * Class: GameTree
 * @author Mike Deiters
 * @version 1.0
 * October 06, 2015
 * ITEC 3150-01
 *
 * Description: Binary Search Tree of Games
 *
 * Purpose: Create, add, remove, and order games in a binary search tree
 */
public class GameTree {

    private GameTreeNode< Game > overallRoot;
    private ArrayList< Game > gameList;

    /**
     * Constructor: GameTree
     */
    public GameTree() {

        this.overallRoot = null;
        this.gameList = new ArrayList<>();
    }

    /**
     * Method: add
     * @param element Game to be added to the binary search tree
     * @return boolean of if the element was added or not
     */
//    public boolean add( Game element ) {
//
//        if ( overallRoot == null ) { // If overallRoot is null then create the binary tree
//
//            overallRoot = new GameTreeNode<>(element);
//            return true;
//        }
//        else {
//
//            // Find the parent
//
//            GameTreeNode< Game > current = overallRoot;
//            GameTreeNode< Game > parent = null;
//
//            while ( current != null ) { // Until current is assigned to a null node
//
//                if ( element.compareTo(current.key) < 0 ) { // Move to the left
//
//                    // Assign current to parent and assign current to the left node
//
//                    parent = current;
//                    current = current.left;
//                }
//                else if ( element.compareTo(current.key) > 0 ) { // Move to the right
//
//                    // Assign current to parent and assign current to the right node
//
//                    parent = current;
//                    current = current.right;
//                }
//                else { // Duplicate Node was not inserted
//
//                    return false;
//                }
//            }
//
//            // Create a new Node and attach it to the parent
//
//            if ( element.compareTo(parent.key) < 0 ) { // If element is less than parent then assign to the left node
//
//                parent.left = new GameTreeNode<>(element);
//            }
//            else if ( element.compareTo(parent.key) > 0 ) { // If element is greater than parent then assign to the right node
//
//                parent.right = new GameTreeNode<>(element);
//            }
//        }
//
//        // New Element was inserted
//
//        return true;
//    }

    /**
     * Method: insert
     * @param element Game to insert into the tree
     * Description: Create a new node and add to the tree
     */
    public void insert( Game element ) {

        // Create a new node

        GameTreeNode< Game > node = new GameTreeNode<>(element);

        // Start the recrusive procedure to add the node

        insertAVL(this.overallRoot, node);
    }

    /**
     * Method: insertAVL
     * @param parent GameTreeNode of the parent node
     * @param current GameTreeNode of the current node
     * Description: Recursively searches the tree for where to insert the node
     */
    public void insertAVL( GameTreeNode< Game > parent, GameTreeNode< Game > current ) {

        if ( overallRoot == null ) { // If overallRoot is null then create the binary tree

            overallRoot = current;
        }
        else {

            if ( current.key.compareTo(parent.key) < 0 ) { // Is current less than parent

                if ( parent.left == null ) { // If parent doesn't have a left node

                    parent.left = current;
                    current.parent = parent;

                    // Current has been inserted, now check the balance

                    recursiveBalance(parent);
                }
                else {

                    // Move to the left

                    insertAVL(parent.left, current);
                }
            }
            else if ( current.key.compareTo(parent.key) > 0 ) { // Is current greater than parent

                if ( parent.right == null ) {

                    parent.right = current;
                    current.parent = parent;

                    // Current has been inserted, now check the balance

                    recursiveBalance(parent);
                }
                else {

                    // Move to the right

                    insertAVL(parent.right, current);
                }
            }
            else {
                // Duplicate Node was not inserted
            }
        }
    }

    /**
     * Method: recursiveBalance
     * @param current GameTreeNode to balance
     * Description: Reorganize the tree to balance it
     */
    public void recursiveBalance( GameTreeNode current ) {

        setBalance(current);
        int balance = current.balance;

        // Check to see if balanced

        if ( balance == -2 ) { // Balance is -2

            if ( height(current.left.left) >= height(current.left.right) ) {

                current = rotateRight(current);
            }
            else {

                current = doubleRotateLeftRight(current);
            }
        }
        else if ( balance == 2 ) { // Balance is 2

            if ( height(current.right.right) >= height(current.right.left) ) {

                current = rotateLeft(current);
            }
            else {

                current = doubleRotateRightLeft(current);
            }
        }

        // we did not reach the root yet

        if ( current.parent != null ) {

            recursiveBalance(current.parent);
        }
        else {

            this.overallRoot = current;
        }
    }

    /**
     * Method: remove
     * @param key Game to be removed
     * Description: Removes a Game from the tree, if it exists.
     */
    public void remove( Game key ) {

        // Find the Game, then delete it

        removeAVL(this.overallRoot, key);
    }

    /**
     * Method: removeAVL
     * @param current The node to start the search.
     * @param key The KEY of node to remove.
     * Description: Finds the node with the key and calls a method to remove the node.
     */
    public void removeAVL( GameTreeNode< Game > current, Game key ) {

        if ( current == null ) {

            return;
        }
        else {

            if ( current.key.compareTo(key) > 0 ) { // Current is greater than key

                removeAVL(current.left, key);
            }
            else if ( current.key.compareTo(key) < 0 ) { // Current is lass than key

                removeAVL(current.right, key);
            }
            else if ( current.key == key ) { // Found the key

                // we found the node in the tree.. now lets go on!

                removeFoundNode(current);
            }
        }
    }

    /**
     * Method: removeFoundNode
     * @param node The node to be removed.
     * Description: Removes a node from a AVL-Tree, while balancing will be done if necessary
     */
    public void removeFoundNode( GameTreeNode< Game > node ) {

        GameTreeNode< Game > root;

        if ( node.left == null || node.right == null ) { // At least one child node is null

            // The root is deleted

            if ( node.parent == null ) {

                this.overallRoot = null;
                node = null;
                return;
            }
            root = node;
        }
        else {

            // Node has two children --> will be replaced by successor

            root = successor(node);
            node.key = root.key;
        }

        GameTreeNode< Game > parent;

        if ( root.left != null ) { // Assign the left root to parent

            parent = root.left;
        }
        else { // Assign the right root to parent

            parent = root.right;
        }

        if ( parent != null ) { // Assign the parent of root to parent.parent

            parent.parent = root.parent;
        }

        if ( root.parent == null ) { // Assign parent to overallRoot

            this.overallRoot = parent;
        }
        else {

            if ( root == root.parent.left ) { // Assign parent to root.parent.left

                root.parent.left = parent;
            }
            else { // Assign parent to root.parent.right

                root.parent.right = parent;
            }

            // Balancing must be done until the root is reached.

            recursiveBalance(root.parent);
        }
        root = null;
    }

    /**
     * Method: rotateLeft
     * @param node The node for the rotation
     * @return The root of the rotated tree
     * Description: Left rotation using the given node
     */
    public GameTreeNode< Game > rotateLeft( GameTreeNode< Game > node ) {

        // Assign the right node to right

        GameTreeNode< Game > right = node.right;

        // Assign the node parent to the right parent

        right.parent = node.parent;

        // Assign the left node to the right node

        node.right = right.left;

        if ( node.right != null ) { // Assign node to node.right.parent

            node.right.parent = node;
        }

        // Assign node to the left node of right

        right.left = node;

        // Assign right to the parent node

        node.parent = right;

        if ( right.parent != null ) { // If the right parent is not null

            if ( right.parent.right == node ) { // Assign right to right.parent.right

                right.parent.right = right;
            }
            else if ( right.parent.left == node ) { // Assign node to right.parent.left

                right.parent.left = right;
            }
        }

        // Set the balance

        setBalance(node);
        setBalance(right);

        return right;
    }

    /**
     * Method: rotateRight
     * @param node The node for the rotation
     * @return The root of the new rotated tree.
     * Description: Right rotation using the given node
     */
    public GameTreeNode< Game > rotateRight( GameTreeNode< Game > node ) {

        // Assign the left node to left

        GameTreeNode< Game > left = node.left;

        // Assign the parent node to the left parent node

        left.parent = node.parent;

        // Assign left.right to the left node

        node.left = left.right;

        if ( node.left != null ) { // Assign node to the left node parent

            node.left.parent = node;
        }

        // Assign node to the right node of left

        left.right = node;

        // Assign left to the parent of node

        node.parent = left;

        if ( left.parent != null ) { // If the parent of left is null

            if ( left.parent.right == node ) { // Assign left to left.parent.right

                left.parent.right = left;
            }
            else if ( left.parent.left == node ) { // Assign  left to left.parent.left

                left.parent.left = left;
            }
        }

        // Set the balance

        setBalance(node);
        setBalance(left);

        return left;
    }

    /**
     * Method: doubleRotateLeftRight
     * @param node The node for the rotation.
     * @return The root after the double rotation.
     * Description: Rotate the left node left then rotate the node right
     */
    public GameTreeNode< Game > doubleRotateLeftRight( GameTreeNode< Game > node ) {

        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    /**
     * Method: doubleRotateRightLeft
     * @param node The node for the rotation.
     * @return The root after the double rotation
     * Description: Rotate the right node right then rotate the node left
     */
    public GameTreeNode< Game > doubleRotateRightLeft( GameTreeNode< Game > node ) {

        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    /**
     * Method: height
     * @param current
     * @return The height of a node
     * Description: Calculating the "height" of the node (-1, if node is not existent eg. NULL).
     */
    private int height( GameTreeNode< Game > current ) {

        if ( current == null ) { // Return -1 if null

            return -1;
        }
        if ( current.left == null && current.right == null ) { // Return 0 if left and right are null

            return 0;
        }
        else if ( current.left == null ) { // Return 1 + height of the the right node if left is null

            return 1 + height(current.right);
        }
        else if ( current.right == null ) { // Return 1 + height of the the left node if right is null

            return 1 + height(current.left);
        }
        else { // Return 1 + the max between the height of the left node and the height of the right node

            return 1 + maximum(height(current.left), height(current.right));
        }
    }

    /**
     * Method: maximum
     * @param left int
     * @param right int
     * @return the larger of the two ints
     * Description: Figure out which int is larger
     */
    private int maximum( int left, int right ) {

        if ( left >= right ) {

            return left;
        }
        else {

            return right;
        }
    }

    /**
     * Method: successor
     * @param node The predecessor.
     * @return The successor of node.
     * Description: Returns the successor of node in the tree
     */
    public GameTreeNode< Game > successor( GameTreeNode< Game > node ) {

        if ( node.right != null ) { // Right node is not null

            // Assign the right node to right

            GameTreeNode< Game > right = node.right;

            while ( right.left != null ) { // Until the left node is null

                right = right.left;
            }
            return right;
        }
        else { // Right Node is null

            // Assign node.parent to parent

            GameTreeNode< Game > parent = node.parent;

            while ( parent != null && node == parent.right ) { // Until node is not the right node of parent and parent is not null

                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * Method:setBalance
     * @param node GameTreeNode to set the balance of
     * Description: subtract the height of the right from the height of the left
     */
    private void setBalance( GameTreeNode< Game > node ) {

        node.balance = height(node.right) - height(node.left);
    }

    /**
     * Method: exists
     * @param element Game to check to see if it exists
     * @return boolean if element exists
     * Description: iterate through the bst to see if a game exist in it
     */
    public boolean exists( Game element ) {

        // Check to see if a Game exist

        GameTreeNode< Game > current = overallRoot;

        while ( current != null ) { // Until current is assigned to a null node

            if ( element.compareTo(current.key) < 0 ) { // Move to the left

                current = current.left;
            }
            else if ( element.compareTo(current.key) > 0 ) { // Move to the right

                current = current.right;
            }
            else { // Game found

                return true;
            }
        }

        // Game not found

        return false;
    }

    /**
     * Method: exists
     * @param gameName String to check to see if the game name exists
     * @return boolean if element exists
     * Description: iterate through the bst to see if a game exist in it
     */
    public boolean exists( String gameName ) {

        // Check to see if a Game exist

        GameTreeNode< Game > current = overallRoot;

        while ( current != null ) { // Until current is assigned to a null node

            if ( gameName.compareTo(current.key.getName()) < 0 ) { // Move to the left

                current = current.left;
            }
            else if ( gameName.compareTo(current.key.getName()) > 0 ) { // Move to the right

                current = current.right;
            }
            else { // Game found

                return true;
            }
        }

        // Game not found

        return false;
    }

    /**
     * Method: rightMostNode
     * @param element GameTreeNode<Game> Node to remove
     * @return rightMost the rightMost node
     * Description: Searching the left tree of element for the rightMost node
     */
//    public GameTreeNode< Game > rightMostNode( GameTreeNode< Game > element ) {
//
//        // Find the rightMost
//
//        GameTreeNode< Game > current = element.left;
//        GameTreeNode< Game > parent = element;
//        GameTreeNode< Game > rightMost = null;
//
//        while ( rightMost == null ) { // Until rightMost is assigned
//
//            if ( current.right == null ) { // rightMost found
//
//                // Assign rightMost to current and parentRightMost to parent
//
//                rightMost = current;
//                rightMost.parent = parent;
//            }
//            else { // Move farther right
//
//                // Assign current to parent and assign current to the right node
//
//                parent = current;
//                current = current.right;
//            }
//        }
//
//        return rightMost;
//    }

    /**
     * Method: remove
     * @param element Game to be removed
     * @return boolean if element was removed
     * Description: Remove a game from the tree
     */
//    public boolean remove( Game element ) {
//
//        if ( exists(element) ) { // Check to see if the element exists
//
//            // Find the parent
//
//            GameTreeNode< Game > current = overallRoot;
//            GameTreeNode< Game > parent = null;
//            GameTreeNode< Game > node = null;
//
//            while ( node == null ) { // Until node is assigned
//
//                if ( element.compareTo(current.key) < 0 ) { // Move to the left
//
//                    // Assign current to parent and assign current to the left node
//
//                    parent = current;
//                    current = current.left;
//                }
//                else if ( element.compareTo(current.key) > 0 ) { // Move to the right
//
//                    // Assign current to parent and assign current to the right node
//
//                    parent = current;
//                    current = current.right;
//                }
//                else { // element was found
//
//                    node = current;
//                }
//            }
//
//            // Remove node
//
//            if ( node.left == null && node.right != null ) { // Case 1: Left node null
//
//                if ( node.right.key.compareTo(parent.key) < 0 ) { // If right node is less than parent
//
//                    // Assign parent.left to right node
//
//                    parent.left = node.right;
//                }
//                else if ( node.right.key.compareTo(parent.key) > 0 ) { // If right node is greater than parent
//
//                    // Assign parent.right to right node
//
//                    parent.right = node.right;
//                }
//            }
//            else if ( node.left != null ) { // Case 2: Right Most Node
//
//                GameTreeNode< Game > rightMost = rightMostNode(node);
//
//                // Assign current to rightMost
//
//                current.key = rightMost.key;
//
//                // Assign rightMost.parent.right to rightMost.left
//
//                rightMost.parent.right = rightMost.left;
//
//                // Remove rightMost
//
//                rightMost = null;
//            }
//            else { // Else left and right node is null
//
//                if ( parent.left.key.equals(node.key) ) { // If node = parent.left
//
//                    // Remove parent.left
//
//                    parent.left = null;
//                }
//                else if ( parent.right.key.equals(node.key) ) { // If node = parent.right
//
//                    // Remove parent.right
//
//                    parent.right = null;
//                }
//
//                // Remove node
//
//                node = null;
//            }
//
//            // Element was removed
//
//            return true;
//        }
//        else { // Element does not exist in tree so it was not deleted
//
//            return false;
//        }
//    }

    /**
     * Method getPreOrder
     * @return gameList Arraylist of game in perOrder
     * Description: Handles the output of pre order
     */
    public ArrayList< Game > getPreOrder() {

        if ( overallRoot != null ) { // If overallRoot is not null

            // Clear gameList

            gameList.clear();
            _preOrder(overallRoot);
        }
        return gameList;
    }

    /**
     * Method: _preOrder
     * @param node GameTreeNode<Game>
     * @return void
     * Description: Iterate through the tree to output in pre order
     */
    private void _preOrder( GameTreeNode< Game > node ) {

        if ( node == null ) { // If node is null then just return

            return;
        }

        // Add the element to gameList

        gameList.add(node.key);

        // Move left

        _preOrder(node.left);

        // Move right

        _preOrder(node.right);
    }

    /**
     * Method: getInOrder
     * @return gameList Arraylist inOrder
     * Description: Handles the output of in order
     */
    public ArrayList< Game > getInOrder() {

        if ( overallRoot != null ) { // If overallRoot is not null

            // Clear gameList

            gameList.clear();
            _inOrder(overallRoot);
        }
        return gameList;
    }

    /**
     * Method: _inOrder
     * @param node GameTreeNode<Game>
     * @return void
     * Description: Iterate through the tree to output in order
     */
    private void _inOrder( GameTreeNode< Game > node ) {

        if ( node == null ) { // If node is null then just return

            return;
        }

        // Move left

        _inOrder(node.left);

        // Add the element to gameList

        gameList.add(node.key);

        // Move right

        _inOrder(node.right);
    }

    /**
     * Method: getPostOrder
     * @return gameList Arraylist in postOrder
     * Description: Handles the output of post order
     */
    public ArrayList< Game > getPostOrder() {

        if ( overallRoot != null ) { // If overallRoot is not null

            // Clear gameList

            gameList.clear();
            _postOrder(overallRoot);
        }
        return gameList;
    }

    /**
     * Method: _postOrder
     * @param node GameTreeNode<Game>
     * @return void
     * Description: Iterate through the tree to output in post order
     */
    private void _postOrder( GameTreeNode< Game > node ) {

        if ( node == null ) { // If node is null then just return

            return;
        }

        // Move left

        _postOrder(node.left);

        // Move right

        _postOrder(node.right);

        // Add the element to gameList

        gameList.add(node.key);

    }
}

