//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Song Tree
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class is the binary tree of the song list
 * 
 * @author Dongwon
 *
 */
public class SongTree {
  private BSTNode<Song> root; // root of this song BST
  private int size; // size of this song tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this SongTree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (root == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of songs stored in this BST.
   * 
   * @return the size of this SongTree
   */
  public int size() {
    return this.size;
  }


  /**
   * Adds a new song to this SongTree
   * 
   * @param newSong a new song to add to this BST.
   * @return true if the newSong was successfully added to this BST, and returns false if there is a
   *         match with this song already stored in this BST.
   */
  public boolean addSong(Song newSong) {
    // Add newSong to an empty SongTree
    if (isEmpty()) {
      root = new BSTNode<Song>(newSong);
      size++;
      return true;
    }

    // Add newSong to an non-empty SongTree
    else {
      BSTNode<Song> current = root;
      if (addSongHelper(newSong,current)) {
        size++;
        return true;
      }
    }
    
    return false;
  }

  /**
   * Recursive helper method to add a new song to a SongTree rooted at current.
   * 
   * @param current The "root" of the subtree we are inserting new song into.
   * @param newSong The song to be added to a BST rooted at current.
   * @return true if the newSong was successfully added to this SongTree, false otherwise
   */
  protected static boolean addSongHelper(Song newSong, BSTNode<Song> current) {
    BSTNode<Song> addedSong = new BSTNode<Song>(newSong);

    // Base case: Matching song already exists
    if (current.getData().compareTo(newSong) == 0) {
      return false;
    }

    // When newSong is smaller than current (Add to left child)
    if (current.getData().compareTo(newSong) > 0) {
      // Base case: Empty child node -> Successfully Added
      if (current.getLeft() == null) {
        current.setLeft(addedSong);
        return true;
      }
      else {
      // Recursive case: Go to the next node
      current = current.getLeft();
      return addSongHelper(newSong, current);
      }
    }

    // When newSong is greater than current (Add to right child)
    else {
      // Base case: Empty child node
      if (current.getRight() == null) {
        current.setRight(addedSong);
        return true;
      }

      // Recursive case: Go to the next node
      current = current.getRight();
      return addSongHelper(newSong, current);
    }
  }

  /**
   * Returns a String representation of all the songs stored within this BST in the increasing
   * order, separatingd by a newline "\n". For instance
   * 
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" + "[(Year: 2015) (Rate: 6.5) (Name:
   * Cinderella)]" + "\n"
   * 
   * @return a String representation of all the songs stored within this BST sorted in an increasing
   *         order with respect to the result of Song.compareTo() method (year, rating, name).
   *         Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a SongTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current song within this BST (root of a subtree)
   * @return a String representation of all the songs stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Song.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Song> current) {
    String output = "";

    if (current != null) {

      if (current.getLeft() != null) {
        output += current.getLeft().getData().toString() + "\n";
      }

      output += current.getData().toString() + "\n";

      if (current.getRight() != null) {
        output += current.getRight().getData().toString() + "\n";
      }

    }

    return output;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a SongTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Song> current) {
    int leftHeight = 0;
    int rightHeight = 0;

    // Root does not exist
    if (current == null) {
      return 0;
    }

    // Root exists
    if (current.getLeft() != null) {
      current = current.getLeft();
      leftHeight = heightHelper(current);
    }

    if (current.getRight() != null) {
      current = current.getRight();
      rightHeight = heightHelper(current);
    }

    return 1 + Math.max(leftHeight, rightHeight);

  }

  /**
   * Checks whether this SongTree contains a song given its name, production year, and rating.
   * 
   * @param year   year of production of the song to search
   * @param rating rating of the song to search
   * @param name   name of the song to search
   * @return true if there is a match with this song in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    Song target = new Song(year, rating, name);
    return containsHelper(target, root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given song in the subtree
   * rooted at current
   * 
   * @param target  a reference to a song we are searching for a match in the BST rooted at current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Song target, BSTNode<Song> current) {
    // item match
    if (target.equals(current.getData())) {
      return true;
    }

    // not matching
    else {
      if (current.getLeft() != null) {
        return containsHelper(target, current.getLeft());
      }
      if (current.getRight() != null) {
        return containsHelper(target, current.getRight());
      }

      return false;
    }
  }


  /**
   * Gets the best (maximum) song in this BST
   * 
   * @return the best (largest) song (the most recent, highest rated, and having the largest name)
   *         in this SongTree, and null if this tree is empty.
   */
  public Song getBestSong() {
    return getBestSongHelper(root);
  }

  private Song getBestSongHelper(BSTNode<Song> current) {
    if (current.getRight() == null) {
      return current.getData();
    }

    else {
      current = current.getRight();
      return getBestSongHelper(current);
    }
  }


  /**
   * Search for songs given the year and minimum rating as lookup key.
   * 
   * @param year   production year of a song
   * @param rating rating value of a song
   * @return a list of songs whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws a NoSuchElementException with a descriptive error message if there is no Song found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Song> lookup(int year, double rating) {

    // call lookupHelper given the year, rating, the root of this SongTree and an empty arrayList)
    ArrayList<Song> songList = new ArrayList<Song>();
    lookupHelper(year, rating, root, songList);

    // If no match found by the lookupHelper throw a NoSuchElementException with a descriptive error
    // message
    if (songList.isEmpty()) {
      throw new NoSuchElementException("No matching song was found");
    }

    // else return the list of songs
    return songList;
  }

  /**
   * Recursive helper method to lookup the list of songs given their year of production and a
   * minimum value of rating
   * 
   * @param year     the year we would like to search for a song
   * @param rating   the minimum rating we would like to search for a song
   * @param songList an arraylist which stores the list of songs matching our search criteria (exact
   *                 year of production and having at least the provided rating) within the subtree
   *                 rooted at current
   * @param current  "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Song> current,
      ArrayList<Song> songList) {
    // If the BST rooted at current is null, do nothing and return
    if (current == null) {
      return;
    }

    // Add the song in pre-order if satisfies the condition
    else {
      if (current.getData().getYear() == year) {
        if (current.getData().getRating() >= rating) {
          songList.add(current.getData());
        }
      }
      if (current.getLeft() != null) {
        lookupHelper(year, rating, current.getLeft(), songList);
      }
      if (current.getRight() != null) {
        lookupHelper(year, rating, current.getRight(), songList);
      }
    }

  }
}
