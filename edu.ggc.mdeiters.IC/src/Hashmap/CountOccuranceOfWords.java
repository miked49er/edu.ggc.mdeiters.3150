package Hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Class: CountOccuranceOfWords
 * @author Mike Deiters
 * @version 1.0
 * September 15, 2015
 * ITEC 3150-01
 *
 * Description: Practice Hashmaps
 *
 * Purpose: In Class Activity
 */
public class CountOccuranceOfWords {

    public static void main( String[] args ) {

        // set text in a string

        String text = "Good Morning. Have a good class. Have a good visit. Have fun!";

        // create a Hashmap to hold words as a key and count as value- name it map to work with rest of the code here

        HashMap< String, Integer > map = new HashMap< String, Integer >();

        String[] words = text.split("[\n\t\r.,;:!?(){} ]+");

        for ( int i = 0; i < words.length; i++ ) {

            String key = words[ i ].toLowerCase();
            // Check for word in map and if present, increment count, else add with a count of one

            if ( !map.containsKey(key) ) {

                map.put(key, 1);
            }
            else {

                int value = map.get(key);
                map.put(key, value + 1);
            }

//            System.out.println(words[ i ]);
        }

        // Print each key and the value associated with it

        for ( Map.Entry< String, Integer > entry : map.entrySet() ) {

            if ( entry.getKey().length() < 4 ) {

                System.out.println(entry.getKey() + "\t\t\t" + entry.getValue());
            }
            else {

                System.out.println(entry.getKey() + "\t\t" + entry.getValue());
            }
        }
    }

}
