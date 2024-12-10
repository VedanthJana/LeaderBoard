import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class represents a binary search tree (BST) of Player objects with various scores.
 * We sort the Players by a combination of their scores and names (see the Player.compareTo() method).
 * 
 * The class provides methods to insert, search, and remove Player objects from the tree, as well as a couple of methods
 * for viewing its contents in printed form.
 * 
 * This class also implements Iterable<Player> so that it can be used in an enhanced for-loop.
 */
public class Leaderboard implements Iterable<Player> {

    /** The root node of the binary tree */
    private BSTNode<Player> root;
    
    /** The current number of nodes that have been added to the tree */
    private int size;

    /**
     * Initializes an empty leaderboard
     */
    public Leaderboard() {
        this.size = 0;
        this.root = null;
    }

    /**
     * An accessor method for the size variable. This method runs in O(1) time.
     * @return the number of nodes expected to be present in this BST
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the root node of the binary tree, for testing purposes.
     * @return a reference to the root node of the binary tree
     */
    protected BSTNode<Player> getRoot() {
        return this.root;
    }

    //////////////////////////////////////////// COUNT ////////////////////////////////////////////

    /**
     * Returns the number of players currently present in the BST.
     * @return the number of players in the leaderboard
     */
    public int count() {
        return countHelper(root);
    }

    /**
     * Returns the size of the subtree rooted at the given node.
     * @param node the root of this subtree
     * @return the number of players in this subtree
     */
    protected int countHelper(BSTNode<Player> node) {
        if (node == null) return 0;
        return 1 + countHelper(node.getLeft()) + countHelper(node.getRight());
    }

    ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////

    /**
     * Returns a Player reference with the given name. 
     * You may assume that there is AT MOST ONE Player with the name in the tree.
     * @param name the name of the player to find
     * @return a reference to the Player with the given name, or null if no Player with this name is present in the tree
     */
    public Player lookup(String name) {
        if (root == null) return null;
        return lookupHelper(root, name);
    }

    /**
     * Exhaustively searches the Leaderboard tree for a Player with the given name.
     * @param node the root of this subtree
     * @param name the name of the player to find
     * @return a reference to the Player with the given name, or null if no Player with this name is present in the subtree
     */
    protected Player lookupHelper(BSTNode<Player> node, String name) {
        if (node == null) return null;
        
        Player current = node.getData();
        int comparison = current.getName().compareTo(name);
        
        if (comparison == 0) return current; // Found the player
        else if (comparison > 0) return lookupHelper(node.getLeft(), name); // Search left
        else return lookupHelper(node.getRight(), name); // Search right
    }

    //////////////////////////////////////////// ADD ////////////////////////////////////////////

    /**
     * Adds a new Player to this Leaderboard. 
     * If the root is null, creates a new node with the given Player and sets it as the root.
     * Otherwise, calls the addPlayerHelper() method to recursively add the Player to the tree.
     * @param player the player to add to the tree
     * @return true if the Player was successfully added, false otherwise
     */
    public boolean addPlayer(Player player) {
        if (root == null) {
            root = new BSTNode<>(player);
            size++;
            return true;
        }
        return addPlayerHelper(root, player);
    }

    /**
     * Adds a new player to the leaderboard rooted at the given node.
     * If the player is already in the tree, they will NOT be added again.
     * @param node the root node of the tree to add the player to
     * @param player the player to add to the tree
     * @return true if the player was successfully added, false otherwise
     */
    protected boolean addPlayerHelper(BSTNode<Player> node, Player player) {
        int comparison = node.getData().compareTo(player);

        if (comparison == 0) {
            return false; // Player is already in the tree
        }
        if (comparison > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<>(player));
                size++;
                return true;
            } else {
                return addPlayerHelper(node.getLeft(), player);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<>(player));
                size++;
                return true;
            } else {
                return addPlayerHelper(node.getRight(), player);
            }
        }
    }

    //////////////////////////////////////////// REMOVE ////////////////////////////////////////////

    /**
     * Removes the given Player from this Leaderboard.
     * @param player the player to remove from the tree
     * @return true if the player was successfully removed, false otherwise
     */
    public boolean removePlayer(Player player) {
        // remove from an empty tree
        if (root == null) return false;
        
        try {
            root = removePlayerHelper(root, player);
            size--;
            return true;
        } catch (NoSuchElementException e) {
            // the player was not found in this tree
            return false;
        }
    }

    /**
     * Removes a player from the BST rooted at the given node and returns the new root of the subtree.
     * @param node the current root node of the subtree
     * @param player the player to remove from the tree
     * @return the root node of this subtree after the player has been removed
     * @throws NoSuchElementException if the player is not found in the tree
     */
    protected BSTNode<Player> removePlayerHelper(BSTNode<Player> node, Player player) {
        if (node == null) {
            throw new NoSuchElementException("Player not found");
        }

        int comparison = node.getData().compareTo(player);

        // Player found
        if (comparison == 0) {
            // Case 1: Node has no children (leaf node)
            if (node.getLeft() == null && node.getRight() == null) {
                return null; // Simply remove the node
            }
            
            // Case 2: Node has only one child (left or right)
            if (node.getLeft() == null) {
                return node.getRight(); // Return the right child
            }
            if (node.getRight() == null) {
                return node.getLeft(); // Return the left child
            }

            // Case 3: Node has two children, replace it with the in-order successor
            // Find the in-order successor (smallest node in the right subtree)
            Player successor = getMinScoreHelper(node.getRight());

            // Create a new node with the successor's data
            node = new BSTNode<>(successor, node.getLeft(), node.getRight());

            // Remove the successor node from its original position in the right subtree
            node.setRight(removePlayerHelper(node.getRight(), successor));

            return node;
        } else if (comparison > 0) {
            // Player is smaller than current node's data, go left
            node.setLeft(removePlayerHelper(node.getLeft(), player));
        } else {
            // Player is larger than current node's data, go right
            node.setRight(removePlayerHelper(node.getRight(), player));
        }

        return node;
    }

    //////////////////////////////////////////// GET MIN ////////////////////////////////////////////

    /**
     * Finds the player with the lowest score in the leaderboard.
     * @return the Player with the lowest score, or null if the tree is empty
     */
    public Player getMinScore() {
        if (root == null) return null;
        return getMinScoreHelper(root);
    }

    /**
     * Returns the Player with the smallest score in the current subtree.
     * @param node the root node of the tree to search; must not be null
     * @return the Player with the smallest score in the current subtree
     */
    protected Player getMinScoreHelper(BSTNode<Player> node) {
        return node.getLeft() == null ? node.getData() : getMinScoreHelper(node.getLeft());
    }

    //////////////////////////////////////////// GET MAX ////////////////////////////////////////////

    /**
     * Finds the player with the highest score in the leaderboard.
     * @return the Player with the highest score, or null if the tree is empty
     */
    public Player getMaxScore() {
        if (root == null) return null;
        return getMaxScoreHelper(root);
    }

    /**
     * Returns the Player with the largest score in the current subtree.
     * @param node the root node of the tree to search; must not be null
     * @return the Player with the largest score in the current subtree
     */
    protected Player getMaxScoreHelper(BSTNode<Player> node) {
        return node.getRight() == null ? node.getData() : getMaxScoreHelper(node.getRight());
    }

    //////////////////////////////////////////// NEXT ////////////////////////////////////////////

    /**
     * Finds the "smallest" Player in the leaderboard whose object is still "larger" than the provided Player.
     * @param player the player whose successor value we are searching for
     * @return the next largest player in the leaderboard, or null if no such player exists
     */
    public Player next(Player player) {
        if (root == null) return null;
        return nextHelper(root, player);
    }

    /**
     * Returns the Player with the next largest score in the subtree rooted at the current node.
     * @param node the root of the relevant subtree
     * @param player the player whose successor value we are searching for
     * @return the next largest player in the leaderboard, or null if no player in this subtree is larger than the given player
     */
    protected Player nextHelper(BSTNode<Player> node, Player player) {
        if (node == null) return null;
        
        int comparison = player.compareTo(node.getData());
        if (comparison < 0) {
            Player leftResult = nextHelper(node.getLeft(), player);
            return (leftResult == null) ? node.getData() : leftResult;
        }
        
        return nextHelper(node.getRight(), player);
    }

    //////////////////////////////////////////// STRINGS ////////////////////////////////////////////

    /**
     * Creates a list of the players in this leaderboard from lowest to highest score.
     * @return a list of all players in increasing order of score
     */
    @Override
    public String toString() {
        StringBuilder leaders = new StringBuilder();
        for (Player p : this) {
            leaders.append(p.toString()).append("\n");
        }
        return leaders.toString().trim();
    }

    /**
     * Creates a tree-formatted version of the current state of the leaderboard
     * @return a tree-formatted string representation of this leaderboard
     */
    public String prettyPrint() {
        return prettyPrintHelper("", root, false);
    }

    /**
     * Recursive helper method for the prettyPrint() method
     */
    private String prettyPrintHelper(String prefix, BSTNode<Player> node, boolean isLeft) {
        if (node == null) {
            return "";
        }
        // Build the string for the current node
        String str = prefix + (isLeft ? "├──" : "└──") + node.getData().toString() + "\n";
        
        // Recursively build the string for the left and right subtrees
        str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getLeft(), true);
        str += prettyPrintHelper(prefix + (isLeft ? "│   " : "    "), node.getRight(), false);
        
        return str;
    }


    //////////////////////////////////////////// ITERATOR ////////////////////////////////////////////

    /**
     * Implements Iterable to enable enhanced for-loop iteration over the leaderboard.
     * @return an iterator for the leaderboard
     */
    @Override
    public Iterator<Player> iterator() {
        return new LeaderboardIterator(root);
    }

    private static class LeaderboardIterator implements Iterator<Player> {
        private Stack<BSTNode<Player>> stack = new Stack<>();

        public LeaderboardIterator(BSTNode<Player> root) {
            pushLeft(root);
        }

        private void pushLeft(BSTNode<Player> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Player next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode<Player> node = stack.pop();
            pushLeft(node.getRight());
            return node.getData();
        }
    }
}

