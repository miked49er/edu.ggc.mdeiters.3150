package JavaFX;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class: PersonReader
 * @author Mike Deiters
 * @version 1.1
 * October 13, 2015
 * ITEC 3150-01
 *
 * Description: Read in Person objects to a hashset
 *
 * Purpose: Read and Write Binary files
 */
public class PersonReader {

    private File file;
    private HashSet< Person > people;

    /**
     * Constructor: PersonReader
     */
    public PersonReader() {

        this.file = null;
        this.people = new HashSet<>();
    }

    /**
     * Constructor: PersonReader
     * @param file
     */
    public PersonReader( File file ) {
        this.file = file;
        this.people = new HashSet<>();
    }

    /**
     * Method: setFile
     * @param file File
     */
    public void setFile( File file ) {

        this.file = file;
    }

    /**
     * Method: readPeople
     * @return people HastSet of firstNames
     * @throws ClassNotFoundException
     * @throws IOException
     * Description: Reads the binary files into a HashSet
     */
    public HashSet< Person > readPeople(String file) throws ClassNotFoundException, IOException {

        try {

            // Creates an ObjectInputStream from the FileInputStream

            ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));

            while ( true ) { // Until am EOFException is thrown read another object to people

                Person person = (Person) dis.readObject();
                people.add(person);
            }
        }
        catch ( EOFException eof ) {

            // The file is finished being read in
        }

        return people;
    }
}
