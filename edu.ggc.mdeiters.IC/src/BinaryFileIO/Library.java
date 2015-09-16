package BinaryFileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: Library
 *
 * @author Dr. Johnson
 * @version 1.0 Course : ITEC 3150, Fall, 2015 Written: January 18, 2012
 *
 *
 *          This class ? This class describes library used to contain the media
 *          item library. It also contains the main method which starts the
 *          program.
 *
 *          Purpose: ? Methods and attributes needed to create a library of
 *          Media class items. Print them, read them from a file,search for a
 *          particular id and add a new item.
 *
 */
public class Library {

	// actual library data
	private ArrayList< Media > libraryItems = new ArrayList< Media >();

	/**
	 *
	 * Method:main()
	 *
	 * This method is the starting point of the program. It contains the initial
	 * reading of items from the text file media.txt and a menu for allowing user
	 * to choose various tasks.
	 *
	 *
	 * @param args
	 */
	public static void main( String[] args ) {
		// create library by reading in from text file named Media.txt
		Library myLibrary = new Library();

		// open a Scanner to read data from File
		DataInputStream mediaReader = null;

		try {
			// open binary file

			mediaReader = new DataInputStream(new FileInputStream("BinaryMedia.dat"));

			// read one person at a time
			while ( true ) {

				String category = mediaReader.readUTF();
				String name = mediaReader.readUTF();
				int id = mediaReader.readInt();

				if ( category.equalsIgnoreCase("music") ) {
					String artist = mediaReader.readUTF();
					String format = mediaReader.readUTF();
					Music tp = new Music(id, name, category, format, artist);
					myLibrary.addItem(tp);

				}
				else if ( category.equalsIgnoreCase("video") ) {
					String def = mediaReader.readUTF();
					String format = mediaReader.readUTF();
					String director = mediaReader.readUTF();
					String genre = mediaReader.readUTF();

					String rating = mediaReader.readUTF();
					Video tp = new Video(id, name, category, def, format, director,
							rating, genre);
					myLibrary.addItem(tp);

				}
				else if ( category.equalsIgnoreCase("book") ) {
					String author = mediaReader.readUTF();
					String format = mediaReader.readUTF();
					Book tp = new Book(id, name, category, format, author);
					myLibrary.addItem(tp);
				}
				else {
					System.out.println("Unknown media type " + category);
				}

			}
		}
		catch ( EOFException eofe ) {

		}
		catch ( IOException ioe ) {

		}

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Welcome to the library");
		boolean done = false;
		while ( !done ) {
			System.out.println("Would you like to :");
			System.out.println("  1. View contents of library");
			System.out.println("  2. Search for an item");
			System.out.println("  3. Exit");
			int userInput = keyboard.nextInt();
			if ( userInput == 1 ) {
				myLibrary.printLibraryItems();
			}
			else if ( userInput == 2 ) {
				System.out.println("Please enter item number");
				int id = keyboard.nextInt();
				Media item = myLibrary.searchById(id);
				if ( item != null ) {
					System.out.println(item);
				}
				else {
					System.out.println("Sorry- item not found");
				}
			}
			else {
				done = true;
			}

		}

	}

	/**
	 * Method:printLibraryItems()
	 *
	 * This method prints the library items contained in the libraryItems Array
	 * list. It relies on the toString method of the various Media types to print
	 * the items in a user friendly format.
	 *
	 *
	 */
	public void printLibraryItems() {
		for ( int i = 0; i < libraryItems.size(); i++ ) {
			Media temp = libraryItems.get(i);
			System.out.println(temp);
		}

	}

	/**
	 * Method:searchById()
	 *
	 * This method looks at each item in the libraryItems array list and if its
	 * idNumber attribute matches the input parameter id, that item is returned
	 * to the caller. It returns null if item is not found.
	 *
	 * @param id
	 *
	 * @return Media
	 *
	 */
	public Media searchById( int id ) {
		Media item = null;
		for ( Media temp : libraryItems ) {
			if ( temp.getIdNumber() == id ) {
				item = temp;
			}

		}
		return item;
	}

	/**
	 * Method:addItem()
	 *
	 * This method adds the parameter m to the libraryItems array list
	 *
	 * @param m
	 *
	 */
	public void addItem( Media m ) {
		libraryItems.add(m);
	}

}
