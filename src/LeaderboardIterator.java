import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;


/**
 * This iterator allows for in-order traversal of the Leaderboard's BST.
 * It iterates through Player objects in increasing order of their scores.
 */
public class LeaderboardIterator implements Iterator<Player> {

    /** Stack to help with in-order traversal */
    private Stack<BSTNode<Player>> stack;

    /**
     * Initializes the iterator by pushing all nodes to the stack that will allow in-order traversal.
     * @param root the root node of the BST to traverse
     */
    public LeaderboardIterator(BSTNode<Player> root) {
        stack = new Stack<>();
        pushLeftNodes(root);
    }

    /**
     * Pushes all left children of the current node to the stack for in-order traversal.
     * @param node the current node to push left children from
     */
    private void pushLeftNodes(BSTNode<Player> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }

    /**
     * Returns true if there are more elements to iterate over.
     * @return true if there are more elements in the BST
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next Player in the in-order traversal.
     * @return the next Player in the iteration
     */
    @Override
    public Player next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        BSTNode<Player> currentNode = stack.pop();
        Player currentPlayer = currentNode.getData();

        // Push left nodes of the right subtree if they exist
        if (currentNode.getRight() != null) {
            pushLeftNodes(currentNode.getRight());
        }

        return currentPlayer;
    }
}

