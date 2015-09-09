//package HomeWork2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class: personTester
 * @author Mike Deiters
 * @version 1.0
 * September 03, 2015
 * ITEC 3150-01
 *
 * Description: Find a Binary file to read and output the first names
 *
 * Purpose: Utilize the PersonReader class to read in and process it
 */
public class personTester {

    /**
     * Method: main
     * @param args
     * Description: Pass people.dat to PersonReader and call printNames
     */
    public static void main( String[] args ) {

        // Creates a path to people.dat

        URL path = PersonReader.class.getResource("people.dat");

        // Using the path create a new File

        File file = new File(path.getPath());

        PersonReader pr = new PersonReader(file);

        try {

            // Attempts to read the binary file into a hash set

            HashSet< String > people = pr.readPeople();

            printNames(people);
            pr.writeNames();
        }
        catch ( ClassNotFoundException cnf ) {

            System.out.println("Class Not Found");
            System.out.println(cnf.getMessage());
        }
        catch ( IOException ioe ) {

            System.out.println("IO Exception");
        }
    }

    /**
     * Method: printNames
     * @param people
     * Description: Prints out the first names of the people to the console
     */
    public static void printNames( HashSet< String > people ) {

        // Create an Iterator to scan through the HashSet

        Iterator< String > iter = people.iterator();

        while ( iter.hasNext() ) { // Until iter has reached the end of the HashSet keep printing firstNames

            System.out.println(iter.next());
        }
    }
}
