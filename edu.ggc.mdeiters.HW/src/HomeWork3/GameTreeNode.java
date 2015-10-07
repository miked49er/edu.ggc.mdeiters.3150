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

    Game element;
    GameTreeNode<Game> left;
    GameTreeNode<Game> right;

    public GameTreeNode( Game element ) {

        this.element = element;
    }
}
