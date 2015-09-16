package BinaryFileIO;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cjohns25
 *
 */
public class CreateBinaryFile
{

    /**
     * 
     * Method:main()
     * 
     * This method is the starting point of the program. It contains the initial
     * reading of items from the text file media.txt and a menu for allowing
     * user to choose various tasks.
     * 
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // open file for binary file output
        DataOutputStream output = null;
        try
        { // Create an output stream for file temp.dat
            output = new DataOutputStream(new FileOutputStream(
                    "BinaryMedia.dat"));
        } catch (Exception ex)
        {
            System.out.println("Unable to open file for writing- try again");
            System.exit(0);
        }

        // write the six items to the file
        try
        {
            /*
             * first the video, video Star Wars 1 high definition DVD George
             * Lucas science fiction PG
             */
            output.writeUTF("video");
            output.writeUTF("Star Wars");
            output.writeInt(1);
            output.writeUTF("High Definition");
            output.writeUTF("DVD");
            output.writeUTF("George Lucas");
            output.writeUTF("science fiction");
            output.writeUTF("PG");

            /*
             * second video video Empire Strikes Back 4 high definition DVD
             * George Lucas science fiction PG
             */

            output.writeUTF("video");
            output.writeUTF("Empire Strikes Back");
            output.writeInt(4);
            output.writeUTF("High Definition");
            output.writeUTF("DVD");
            output.writeUTF("George Lucas");
            output.writeUTF("science fiction");
            output.writeUTF("PG");

            /*
             * now the books book Object-Oriented Programming 2 Dr. Java Expert
             * digital
             * 
             * book Object-Oriented Requirements Analysis 5 Dr. Java Expert
             * print
             */
            output.writeUTF("book");
            output.writeUTF("Object-Oriented Programming");
            output.writeInt(2);
            output.writeUTF("Dr. Java Expert");
            output.writeUTF("digital");

            output.writeUTF("book");
            output.writeUTF("Object-Oriented Requirements Analysis");
            output.writeInt(5);
            output.writeUTF("Dr. Java Expert");
            output.writeUTF("print");

            /*
             * Now the music music Star Wars Soundtrack 3 John Williams CD
             * 
             * music Empire Soundtrack 6 John Williams digital
             */
            output.writeUTF("music");
            output.writeUTF("Star Wars Soundtrack");
            output.writeInt(3);
            output.writeUTF("John Williams");
            output.writeUTF("CD");

            output.writeUTF("music");
            output.writeUTF("Empire Soundtrack");
            output.writeInt(5);
            output.writeUTF("John Williams");
            output.writeUTF("digital");

        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.out.println("Problem writing to file");
        }

        try
        {
            // close the file
            output.close();
        } catch (Exception ex)
        {
            System.out.println("unable to properly close file");
        }

    }
}
