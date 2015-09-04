package HomeWork2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author cjohns25
 *
 */
public class PeopleWriter
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // open text file peopleFile.txt for reading
        Scanner textFileInput = null;
        try
        {
            textFileInput = new Scanner(new File("peopleFile.txt"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Problem reading file - quitting");
            System.exit(1);
        }
        
        ArrayList<Person>  myPeople = new ArrayList<Person>();
        
        try
        {
            while (textFileInput.hasNext())
            {
                Person p = new Person();
                String temp = null;
                
                // read in one person
                // first name
                temp = textFileInput.nextLine();
                p.setFirstName(temp);
                // last name
                temp = textFileInput.nextLine();
                p.setLastName(temp);
                //city
                temp = textFileInput.nextLine();
                p.setCity(temp);
                // id number
                temp = textFileInput.nextLine();
                int id = Integer.parseInt(temp);
                p.setIdNum(id);
                
                myPeople.add(p);
                
                
                }
            textFileInput.close();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.exit (1);
        }
        
        // now write people.dat binary file using ObjectOutputStream
        
        try
        {
            ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("people.dat"));
            
            // now write the Person classes from the ArrayList
            for(Person p : myPeople)
            {
                outFile.writeObject(p);
            }
            outFile.close();
            
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
