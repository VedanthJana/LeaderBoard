
// TODO: file header

public class LeaderboardTester {
  
  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1) System.out.print("diffScore FAIL ");
    if (!test2) System.out.print("diffName FAIL ");
    if (!test3) System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }
  
  private static boolean testCompareToDiffScore() {
	    // Create two Player objects with different scores
	    Player player1 = new Player("Alice", 50);  // Score 50
	    Player player2 = new Player("Bob", 70);    // Score 70
	    
	    // Compare player1 and player2 using compareTo
	    int result = player1.compareTo(player2);

	    // The expected result is that player1 (score 50) should be less than player2 (score 70)
	    // since we compare in descending order, player1 should be considered 'less'
	    // (result should be positive if player1 is less than player2).
	    if (result < 0) {
	        System.out.println("Test passed: Player1 is less than Player2 based on score.");
	        return true;
	    } else {
	        System.out.println("Test failed: Player1 is not less than Player2 based on score.");
	        return false;
	    }

	}



  
  private static boolean testCompareToSameScoreDiffName() {
	    // Create two Player objects with the same score but different names
	    Player player1 = new Player("Alice", 100);  // Score 100, Name "Alice"
	    Player player2 = new Player("Bob", 100);    // Score 100, Name "Bob"
	    
	    // Compare player1 and player2 using compareTo
	    int result = player1.compareTo(player2);

	    // The expected result is that player1 ("Alice") should be less than player2 ("Bob")
	    // because "Alice" comes before "Bob" alphabetically.
	    if (result < 0) {
	        System.out.println("Test passed: Player1 ('Alice') is less than Player2 ('Bob') based on name.");
	        return true;
	    } else if (result > 0) {
	        System.out.println("Test passed: Player1 ('Alice') is greater than Player2 ('Bob') based on name.");
	        return true;
	    } else {
	        System.out.println("Test failed: Player1 ('Alice') and Player2 ('Bob') are considered equal.");
	        return false;
	    }
	}

  
  private static boolean testCompareToEqual() {
	    // Create two Player objects with the same score and same name
	    Player player1 = new Player("Alice", 100);  // Score 100, Name "Alice"
	    Player player2 = new Player("Alice", 100);  // Score 100, Name "Alice"
	    
	    // Compare player1 and player2 using compareTo
	    int result = player1.compareTo(player2);

	    // The expected result is 0, since both players have the same score and the same name
	    if (result == 0) {
	        System.out.println("Test passed: Player1 ('Alice') is equal to Player2 ('Alice').");
	        return true;
	    } else {	
	        System.out.println("Test failed: Player1 ('Alice') and Player2 ('Alice') are not considered equal.");
	        return false;
	    }
	}

  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1) System.out.print("lookupRoot FAIL ");
    if (!test2) System.out.print("lookupLeft FAIL ");
    if (!test3) System.out.print("lookupRight FAIL ");
    if (!test4) System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }
  
  // HINT: for these testers, add one Player at the root and then build the rest of the tree manually
  // using the reference returned by getRoot(), so that you are not relying on the correctness of
  // the addPlayer() method.
  
  private static boolean testLookupRoot() {
    // TODO: look up the player stored in the root note
    return false;
  }
  
  private static boolean testLookupLeft() {
    // TODO: look up a player stored in the left subtree
    return false;
  }
  
  private static boolean testLookupRight() {
    // TODO: look up a player stored in the right subtree
    return false;
  }
  
  private static boolean testLookupNotPresent() {
    // TODO: look up the name of a player who is not present in the tree (NOT on an empty tree!)
    return false;
  }
  
  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  
  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1) System.out.print("addEmpty FAIL ");
    if (!test2) System.out.print("addPlayer FAIL ");
    if (!test3) System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }
  
  private static boolean testAddPlayerEmpty() {
    // TODO: verify that addPlayer() correctly adds a player to an empty BST
    return false;
  }
  
  private static boolean testAddPlayer() {
    // TODO: verify that addPlayer() correctly adds a player to a non-empty BST
    // Each time you add a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have increased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    return false;
  }
  
  private static boolean testAddPlayerDuplicate() {
    // TODO: verify that adding a duplicate player returns false, does not modify the BST, and
    // does not cause an exception
    return false;
  }
  
  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////
  
  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1) System.out.print("remove FAIL ");
    if (!test2) System.out.print("removeOneChild FAIL ");
    if (!test3) System.out.print("removeTwoChildren FAIL ");
    if (!test4) System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }
  
  private static boolean testRemoveLeaf() {
    // TODO: verify that removePlayer() correctly removes a leaf node from the tree
    return false;
  }
  
  private static boolean testRemoveOneChild() {
    // TODO: verify that removePlayer() correctly removes a player with ONE child
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    return false;
  }
  
  private static boolean testRemoveTwoChildren() {
    // TODO: verify that removePlayer() correctly removes a player with TWO children
    // Each time you remove a player, make sure that:
    // (1) the method returns true
    // (2) the size and count have decreased correctly
    // (3) the output of prettyPrint() is the tree that you expect (you may do this one visually)
    return false;
  }
  
  private static boolean testRemoveNotInTree() {
    // TODO: verify that removing a player not in the tree returns false, does not modify the BST, 
    // and does not cause an exception
    return false;
  }
 
  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////
  
  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1) System.out.print("afterRoot FAIL ");
    if (!test2) System.out.print("afterLeft FAIL ");
    if (!test3) System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }
  
  private static boolean testGetNextAfterRoot() {
    // TODO: verify that next() returns the correct Player when passed the Player in the root node
    return false;
  }
  
  private static boolean testGetNextAfterLeftSubtree() {
    // TODO: verify that next() returns the correct Player when the correct value is in the left
    // subtree of the leaderboard
    return false;
  }
  
  private static boolean testGetNextAfterRightSubtree() {
    // TODO: verify that next() returns the correct Player when the correct value is in the right
    // subtree of the leaderboard
    return false;
  }
  
  //////////////////////////////////////////// MAIN ////////////////////////////////////////////
  
  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo()?"PASS":"");
    
    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup()?"PASS":"");
    
    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd()?"PASS":"");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove()?"PASS":"");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext()?"PASS":"");
  }
  
}

