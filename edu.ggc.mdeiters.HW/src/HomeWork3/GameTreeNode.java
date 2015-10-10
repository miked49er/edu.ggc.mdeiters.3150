package HomeWork3;

/**
 * Class: GameTreeNode
 * @author Mike Deiters
 * @version 1.0
 * October 06, 2015
 * ITEC 3150-01
 *
 * Description: Nodes of a Binary Search Tree
 *
 * Purpose: Create a Node with a left and right Node
 */
public class GameTreeNode<Game> {

    Game key;
    int balance;
    GameTreeNode<Game> left;
    GameTreeNode<Game> right;
    GameTreeNode<Game> parent;

    public GameTreeNode( Game key ) {

        this.left = null;
        this.right = null;
        this.parent = null;
        this.balance = 0;
        this.key = key;
    }
}
