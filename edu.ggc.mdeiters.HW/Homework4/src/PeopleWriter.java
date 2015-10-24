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
     * Method: setFile
     * @param file File
     */
    public void setFile( File file ) {

        this.file = file;
    }

    /**
     * Method: writeOut
     * @param list ArrayList of Person
     * @param file String of the file location
     * @throws IOException
     * Description: Write Person objects to a binary file
     */
    public void writeOut( ArrayList< Person > list, String file) throws IOException {

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

        for ( Person person : list ) {

            output.writeObject(person);
        }
        output.close();
    }

}
