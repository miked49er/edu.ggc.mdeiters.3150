package HomeWork2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class: TODO
 * @author Mike Deiters
 * @version 1.0
 * September 03, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class personTester {

    public static void main( String[] args ) {

        URL path = PersonReader.class.getResource("people.dat");
        File file = new File(path.getFile());

        PersonReader pr = new PersonReader(file);

        try {

            HashSet< Person > people = pr.readPeople();

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

    public static void printNames( HashSet< Person > people ) {

        Iterator< Person > iter = people.iterator();

        while ( iter.hasNext() ) {

            System.out.println(iter.next().getFirstName());
        }
    }
}
