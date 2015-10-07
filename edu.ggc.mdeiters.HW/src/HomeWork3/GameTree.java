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
    public boolean add( Game element ) {

        if ( overallRoot == null ) { // If overallRoot is null then create the binary tree

            overallRoot = new GameTreeNode<>(element);
            return true;
        }
        else {

            // Find the parent

            GameTreeNode< Game > current = overallRoot;
            GameTreeNode< Game > parent = null;

            while ( current != null ) { // Until current is assigned to a null node

                if ( element.compareTo(current.element) < 0 ) { // Move to the left

                    // Assign current to parent and assign current to the left node

                    parent = current;
                    current = current.left;
                }
                else if ( element.compareTo(current.element) > 0 ) { // Move to the right

                    // Assign current to parent and assign current to the right node

                    parent = current;
                    current = current.right;
                }
                else { // Duplicate Node was not inserted

                    return false;
                }
            }

            // Create a new Node and attach it to the parent

            if ( element.compareTo(parent.element) < 0 ) { // If element is less than parent then assign to the left node

                parent.left = new GameTreeNode<>(element);
            }
            else if ( element.compareTo(parent.element) > 0 ) { // If element is greater than parent then assign to the right node

                parent.right = new GameTreeNode<>(element);
            }
        }

        // New Element was inserted

        return true;
    }

    /**
     * Method: exists
     * @param element Game to check to see if it exists
     * @return boolean if element exists
     */
    public boolean exists( Game element ) {

        // Check to see if a Game exist

        GameTreeNode< Game > current = overallRoot;

        while ( current != null ) { // Until current is assigned to a null node

            if ( element.compareTo(current.element) < 0 ) { // Move to the left

                current = current.left;
            }
            else if ( element.compareTo(current.element) > 0 ) { // Move to the right

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
     */
    public boolean exists( String gameName ) {

        // Check to see if a Game exist

        GameTreeNode< Game > current = overallRoot;

        while ( current != null ) { // Until current is assigned to a null node

            if ( gameName.compareTo(current.element.getName()) < 0 ) { // Move to the left

                current = current.left;
            }
            else if ( gameName.compareTo(current.element.getName()) > 0 ) { // Move to the right

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
     * @return nodes[] array of the rightMost node and the parentRightMost node
     */
    public GameTreeNode< Game >[] rightMostNode( GameTreeNode< Game > element ) {

        // Find the rightMost

        GameTreeNode< Game >[] nodes = new GameTreeNode[ 2 ];
        GameTreeNode< Game > current = element.left;
        GameTreeNode< Game > parent = element;
        GameTreeNode< Game > rightMost = null;
        GameTreeNode< Game > parentRightMost = null;

        while ( rightMost == null ) { // Until rightMost is assigned

            if ( current.right == null ) { // rightMost found

                // Assign rightMost to current and parentRightMost to parent

                rightMost = current;
                parentRightMost = parent;
            }
            else { // Move farther right

                // Assign current to parent and assign current to the right node

                parent = current;
                current = current.right;
            }
        }

        // Assign rightMost and parentRightMost to nodes[]

        nodes[ 0 ] = rightMost;
        nodes[ 1 ] = parentRightMost;

        return nodes;
    }

    /**
     * Method: remove
     * @param element Game to be removed
     * @return boolean if element was removed
     */
    public boolean remove( Game element ) {

        if ( exists(element) ) { // Check to see if the element exists

            // Find the parent

            GameTreeNode< Game > current = overallRoot;
            GameTreeNode< Game > parent = null;
            GameTreeNode< Game > node = null;

            while ( node == null ) { // Until node is assigned

                if ( element.compareTo(current.element) < 0 ) { // Move to the left

                    // Assign current to parent and assign current to the left node

                    parent = current;
                    current = current.left;
                }
                else if ( element.compareTo(current.element) > 0 ) { // Move to the right

                    // Assign current to parent and assign current to the right node

                    parent = current;
                    current = current.right;
                }
                else { // element was found

                    node = current;
                }
            }

            // Remove node

            if ( node.left == null && node.right != null ) { // Case 1: Left node null

                if ( node.right.element.compareTo(parent.element) < 0 ) { // If right node is less than parent

                    // Assign parent.left to right node

                    parent.left = node.right;
                }
                else if ( node.right.element.compareTo(parent.element) > 0 ) { // If right node is greater than parent

                    // Assign parent.right to right node

                    parent.right = node.right;
                }
            }
            else if ( node.left != null ) { // Case 2: Right Most Node

                GameTreeNode< Game >[] nodes = rightMostNode(node);

                // Assign rightMost to nodes[0]

                GameTreeNode< Game > rightMost = nodes[ 0 ];

                // Assign parentRightMost to nodes[1]

                GameTreeNode< Game > parentRightMost = nodes[ 1 ];

                // Assign current to rightMost

                current.element = rightMost.element;

                // Assign parentRightMost.right to rightMost.left

                parentRightMost.right = rightMost.left;

                // Remove rightMost

                rightMost = null;
            }
            else { // Else left and right node is null

                if ( parent.left.element.equals(node.element) ) { // If node = parent.left

                    // Remove parent.left

                    parent.left = null;
                }
                else if ( parent.right.element.equals(node.element) ) { // If node = parent.right

                    // Remove parent.right

                    parent.right = null;
                }

                // Remove node

                node = null;
            }

            // Element was removed

            return true;
        }
        else { // Element does not exist in tree so it was not deleted

            return false;
        }
    }

    /**
     * Method getPreOrder
     * @return gameList Arraylist of game in perOrder
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
     */
    private void _preOrder( GameTreeNode< Game > node ) {

        if ( node == null ) { // If node is null then just return

            return;
        }

        // Add the element to gameList

        gameList.add(node.element);

        // Move left

        _preOrder(node.left);

        // Move right

        _preOrder(node.right);
    }

    /**
     * Method: getInOrder
     * @return gameList Arraylist inOrder
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
     */
    private void _inOrder( GameTreeNode< Game > node ) {

        if ( node == null ) { // If node is null then just return

            return;
        }

        // Move left

        _inOrder(node.left);

        // Add the element to gameList

        gameList.add(node.element);

        // Move right

        _inOrder(node.right);
    }

    /**
     * Method: getPostOrder
     * @return gameList Arraylist in postOrder
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

        gameList.add(node.element);

    }
}

