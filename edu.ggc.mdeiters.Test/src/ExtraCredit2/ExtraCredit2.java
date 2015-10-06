package ExtraCredit2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class: TODO
 * @author Mike Deiters
 * @version 1.0
 * October 05, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class ExtraCredit2 {

    /*
    Create a Java class with a main method.  In the main method, create a HashMap where the key is a String and the value is a Double.

Add 5 elements to the HashMap.  The string should be a person's name (make them up) and the Double value should be their age (again, make them up and make them all different).

Print each key, value pair from the HashMap (iterate through the maps keys) individually.
     */
    public static void main(String[] args) {

        Map<String, Double> map = new HashMap<>();

        map.put("Sam", 20.0);
        map.put("Tim", 3.0);
        map.put("Susy", 54.0);
        map.put("Abi", 21.0);
        map.put("Bob", 32.5);

        for ( Map.Entry<String, Double> entry : map.entrySet()) {

            System.out.println("Name: " + entry.getKey() + " \tAge: " + entry.getValue());
        }
    }
}
