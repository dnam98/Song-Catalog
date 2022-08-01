//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Song Tree Tester
// Course: CS 300 Summer 2021
//
// Author: Dongwon Nam
// Email: dnam9@wisc.edu
// Lecturer: Chris Magnano
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

// Add javadoc style class header here
// File Header comes here

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * SongTree.
 *
 */

public class SongTreeTester {

  /**
   * Checks the correctness of the implementation of both addSong() and toString() methods
   * implemented in the SongTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty SongTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one song and then check that the addSong()
   * method call returns true, the tree is not empty, its size is 1, and the .toString() called on
   * the tree returns the expected output. (3) Try adding another song which is smaller that the
   * song at the root, (4) Try adding a third song which is greater than the one at the root, (5)
   * Try adding at least two further songs such that one must be added at the left subtree, and the
   * other at the right subtree. For all the above scenarios, and more, double check each time that
   * size() method returns the expected value, the add method call returns true, and that the
   * .toString() method returns the expected string representation of the contents of the binary
   * search tree in an increasing order from the smallest to the greatest song with respect to year,
   * rating, and then name. (6) Try adding a song already stored in the tree. Make sure that the
   * addSong() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddSongToStringSize() {
    // Create a new SongTree
    SongTree songTree = new SongTree();

    // (1) Check it is empty, size is 0, and the empty string is ""
    if (!songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 0) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 0 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString().equals("")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (1)");
      System.out.println("Expected:\nActual:\n" + songTree.toString());
      return false;
    }

    // (2) Add one song and check isEmpty, size, and the string
    songTree.addSong(new Song(2018, 6.5, "The Sequal to Despacito"));

    if (songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 1) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 1 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString()
        .equals("[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (2)");
      System.out
          .println("Expected:\n" + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
              + "Actual:\n" + songTree.toString());
      return false;
    }

    // (3) Add another song that is smaller and check
    songTree.addSong(new Song(1988, 9.5, "Fast Car"));

    if (songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 2) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 2 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString().equals("[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
        + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (3)");
      System.out.println("Expected:\n[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
          + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n\nActual:\n"
          + songTree.toString());
      return false;
    }

    // (4) Add another song that is greater and check
    songTree.addSong(new Song(2019, 8.5, "Tucson Fog"));

    if (songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 3) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 3 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString()
        .equals("[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
            + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
            + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (4)");
      System.out.println("Expected:\n[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
          + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n" + "Actual:\n" + songTree.toString());
      return false;
    }

    // (5) Add song that is equal in year but small in rating.
    songTree.addSong(new Song(2018, 3.2, "LowerRatingAt2018"));

    if (songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 4) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 4 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString()
        .equals("[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
            + "[(Year: 2018) (Rate: 3.5) (Name: LowerRatingAt2018)]\n"
            + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
            + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (5)");
      System.out.println("Expected:\n[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
          + "[(Year: 2018) (Rate: 3.5) (Name: LowerRatingAt2018)]\n"
          + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n" + "Actual:\n" + songTree.toString());
      return false;
    }

    // (6) Add song that is equal in year but greater in rating.
    songTree.addSong(new Song(2018, 9.9, "GreaterRatingAt2018"));

    if (songTree.isEmpty()) {
      System.out.println("testAddSongToStringSize error: Error in isEmpty method");
      return false;
    }

    if (songTree.size() != 5) {
      System.out.println("testAddSongToStringSize error: Error in size method");
      System.out.println("Expected: 5 Actual: " + songTree.size());
      return false;
    }

    if (!songTree.toString()
        .equals("[(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
            + "[(Year: 2018) (Rate: 3.5) (Name: LowerRatingAt2018)]\n"
            + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
            + "[(Year: 2018) (Rate: 9.9) (Name: GreaterRatingAt2018)]\n"
            + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n")) {
      System.out.println("testAddSongToStringSize error: Error in toString method (6)");
      System.out.println("Expected:\n [(Year: 1988) (Rate: 9.5) (Name: Fast Car)]\n"
          + "[(Year: 2018) (Rate: 3.5) (Name: LowerRatingAt2018)]\n"
          + "[(Year: 2018) (Rate: 6.5) (Name: The Sequal to Despacito)]\n"
          + "[(Year: 2018) (Rate: 9.9) (Name: GreaterRatingAt2018)]\n"
          + "[(Year: 2019) (Rate: 8.5) (Name: Tucson Fog)]\n" + "Actual: " + songTree.toString());
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the SongTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new SongTree. Then, check that
   * calling the contains() method on an empty SongTree returns false. (2) Consider a SongTree of
   * height 3 which contains at least 5 songs. Then, try to call contains() method to search for the
   * song having a match at the root of the tree. (3) Then, search for a song at the right and left
   * subtrees at different levels considering successful and unsuccessful search operations. Make
   * sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    return false;
  }

  /**
   * Checks for the correctness of SongTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty song tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * SongTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*) (*) /
   * (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    return false;
  }

  /**
   * Checks for the correctness of SongTree.getBestSong() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestSong() {
    return false;
  }

  /**
   * Checks for the correctness of SongTree.lookup() method. This test must consider at least 3 test
   * scenarios. (1) Ensures that the SongTree.lookup() method throws a NoSuchElementException when
   * called on an empty tree. (2) Ensures that the SongTree.lookup() method returns an array list
   * which contains all the songs satisfying the search criteria of year and rating, when called on
   * a non empty song tree with one match, and two matches and more. Vary your search criteria such
   * that the lookup() method must check in left and right subtrees. (3) Ensures that the
   * SongTree.lookup() method throws a NoSuchElementException when called on a non-empty song tree
   * with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    return false;
  }

  public static void demo() {
    SongTree bst = new SongTree();
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    bst.addSong(new Song(2018, 6.5, "The Sequal to Despacito"));
    bst.addSong(new Song(1988, 9.5, "Fast Car"));
    System.out.println("==============================================================");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    bst.addSong(new Song(2018, 8.5, "Tucson Fog"));
    bst.addSong(new Song(2018, 6.0, "Get Warmer"));
    bst.addSong(new Song(2017, 5.5, "Slow Truck"));
    bst.addSong(new Song(2018, 7.5, "Take 64"));
    bst.addSong(new Song(2018, 6.0, "Peter Lorre"));
    bst.addSong(new Song(2015, 8.5, "Mr. DimFront"));
    System.out.println("==============================================================");
    System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
    System.out.println(bst);
    try {
      System.out.println(
          "This catalog contains (2018, 7.5, Take 64): " + bst.contains(2018, 7.5, "Take 64"));
      System.out.println(
          "This catalog contains (2016, 8.4, Flowers): " + bst.contains(2016, 7.5, "Flowers"));
      System.out.println();
      System.out.println("Best song: " + bst.getBestSong());
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2018 rated 6.5 and higher");
      System.out.println(bst.lookup(2018, 6.5));
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2018 with rated 8.0 and higher");
      System.out.println(bst.lookup(2018, 8.0));
      System.out.println();
      System.out.println("Lookup query: search for the songs of 2015 with rated 9.0 and higher");
      System.out.println(bst.lookup(2015, 9.0));
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    testAddSongToStringSize();
    // demo();
  }

}
