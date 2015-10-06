package Test1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BinaryFileIO
{


    public static void main(String[] args)
    {
       // create instance
        String initialSet[] = {"Romeo", "Juliet", "Adam", "Eve", "Homer", "Marge", "Bart", "Eve"};

        // YOUR CODE GOES HERE
        // Write code to create a binary file in the default directory 
        // named "testNames.dat" using ObjectOutputStream

        //   write the contents of the string array named initialSet to the file you just opened


        // close the file

        try {

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("testNames.dat"));

            for ( String names: initialSet ) {

                output.writeUTF(names);
            }
            output.close();

        } catch ( IOException ioe ) {

            System.out.println(ioe.getMessage());
        }

        System.out.println("Finished writing to the file");
    }

}
