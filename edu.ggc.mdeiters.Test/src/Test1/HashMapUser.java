package Test1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class HashMapUser {

    public static void main( String[] args ) {
        // open file named "capitals.dat"-  this contains a collection of StateCapitol objects.
        // the file was created using ObjectOutputStream method writeObject (very similar to Person class
        // in homework 2!!!)

        // Create a hashmap instance that will map the name of the state (key) to its capitol (value)
        // populate it with the information read in from capitols.dat

        // then print the information from the map (NOT AS YOU READ IT FROM THE FILE- BUT AFTER IT IS IN THE 
        // MAP

        ObjectInputStream input = null;
        HashMap< String, String > map = null;

        try {

            input = new ObjectInputStream(new FileInputStream("capitals.dat"));

            map = new HashMap<>();

            while ( true ) {

                StateCapital tempState = (StateCapital) input.readObject();
                String state = tempState.getState();
                String capitol = tempState.getCapital();

                map.put(state, capitol);
            }
        }
        catch ( EOFException eof ) {

            // Finished reading in the objects;
        }
        catch ( ClassNotFoundException cnf ) {

            System.out.println(cnf.getMessage());
        }
        catch ( IOException ioe ) {

            System.out.println(ioe.getMessage());
        }

        for ( Map.Entry< String, String > entry : map.entrySet() ) {

            if ( entry.getKey().length() < 6 ) {

                System.out.println("State: " + entry.getKey() + "\t\t\t\tCapitol: " + entry.getValue());
            }
            else if ( entry.getKey().length() < 8 ) {

                System.out.println("State: " + entry.getKey() + "\t\t\tCapitol: " + entry.getValue());
            }
            else if ( entry.getKey().length() <= 12 ) {

                System.out.println("State: " + entry.getKey() + "\t\tCapitol: " + entry.getValue());
            }
            else {

                System.out.println("State: " + entry.getKey() + "\tCapitol: " + entry.getValue());
            }

        }
    }

}
