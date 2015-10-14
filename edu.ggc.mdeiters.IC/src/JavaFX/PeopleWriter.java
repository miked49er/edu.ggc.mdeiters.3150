package JavaFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Class: PersonWriter
 * @author Mike Deiters
 * @version 1.0
 * October 13, 2015
 * ITEC 3150-01
 *
 * Description: Write out Person objects to a file
 *
 * Purpose: Write to a Binary file
 */
public class PeopleWriter {

    private File file;

    /**
     * Constructor: PeopleWriter
     */
    public PeopleWriter() {

        this.file = null;
    }

    /**
     * Constructor: PeopleWriter
     * @param file
     */
    public PeopleWriter( File file ){

        this.file = file;
    }

    /**
     * Method: writeOut
     * @param list
     * @throws IOException
     * Description: Write Person objects to a binary file
     */
    public void writeOut( HashSet< Person > list ) throws IOException {

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(this.file));

        for ( Person person : list ) {

            output.writeObject(person);
        }
        output.close();
    }

}
