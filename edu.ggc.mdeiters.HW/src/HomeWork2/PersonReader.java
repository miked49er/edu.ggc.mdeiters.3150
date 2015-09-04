package HomeWork2;

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
 * Description: TODO
 *
 * Purpose: TODO
 */
public class PersonReader {

    private File file;
    private HashSet< Person > people;

    public PersonReader( File file ) {
        this.file = file;
        this.people = new HashSet< Person >();
    }

    public HashSet< Person > readPeople() throws ClassNotFoundException, IOException {

        try {

            ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));

            while ( true ) {

                people.add((Person) ( dis.readObject() ));
            }
        }
        catch ( EOFException eof ) {

            System.out.println("Finished Reading");
        }

        return people;
    }

    public void writeNames() throws IOException {

        DataOutputStream dos = new DataOutputStream(new FileOutputStream("firstNames.dat"));

        Iterator< Person > iter = people.iterator();

        while ( iter.hasNext() ) {

            dos.writeUTF(iter.next().firstName);
        }
    }
}
