//package HomeWork2;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class: PersonReader
 * @author Mike Deiters
 * @version 1.0
 * September 03, 2015
 * ITEC 3150-01
 *
 * Description: Read in Person objects to a hashset
 *
 * Purpose: Read and Write Binary files
 */
public class PersonReader {

    private File file;
    private HashSet< String > people;

    /**
     * Constructor: PersonReader
     * @param file
     */
    public PersonReader( File file ) {
        this.file = file;
        this.people = new HashSet<>();
    }

    /**
     * Method: readPeople
     * @return people HastSet of firstNames
     * @throws ClassNotFoundException
     * @throws IOException
     * Description: Reads the binary files into a HashSet
     */
    public HashSet< String > readPeople() throws ClassNotFoundException, IOException {

        try {

            // Creates an ObjectInputStream from the FileInputStream

            ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));

            while ( true ) { // Until am EOFException is thrown read another object to people

                Person person = (Person) dis.readObject();
                people.add(person.getFirstName());
            }
        }
        catch ( EOFException eof ) {

            // The file is finished being read in
        }

        return people;
    }

    /**
     * Method: writeNames
     * @throws IOException
     * Description: Writes the first names of the people in the HashSet to a binary file
     */
    public void writeNames() throws IOException {

        // Creates a DataOuptutStream from the FileInputStream

        DataOutputStream dos = new DataOutputStream(new FileOutputStream("firstNames.dat"));

        // Create an iterator to scan through the HashSet

        Iterator< String > iter = people.iterator();

        while ( iter.hasNext() ) { // Until iter has reached the end of the HashSet keep writing out firstNames

            dos.writeUTF(iter.next());
        }
    }
}
