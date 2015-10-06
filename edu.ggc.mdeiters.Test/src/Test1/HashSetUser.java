package Test1;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetUser {
    private HashSet< String > mySet = new HashSet< String >();

    public HashSetUser( String[] initialValues ) {
        for ( int i = 0; i < initialValues.length; i++ ) {
            mySet.add(initialValues[ i ]);
        }
    }

    public HashSetUser() {
        // initially an empty hash set
    }

    public static void main( String[] args ) {
        // create instance
        String initialSet[] = { "Romeo", "Juliet", "Adam", "Eve", "Homer", "Marge", "Bart", "Eve" };
        HashSetUser aSet = new HashSetUser(initialSet);

        // YOUR CODE GOES HERE
        // using the instance of HashSetUser named aSet, add code that
        // 1.   prints the contents of the hashset to console

        Iterator< String > iter = aSet.getIterator();

        while ( iter.hasNext() ) {

            System.out.println(iter.next());
        }

        // 2.  Find the name in the hashSet that is alphabetically the lowest and print it to console (Note:  Don't just
        // print "Adam" from the initialSet above.  Do it by checking each element in the HashSetUser hashset

        iter = aSet.getIterator();

        String lowest = iter.next();

        while ( iter.hasNext() ) {

            String temp = iter.next();

            if ( temp.compareToIgnoreCase(lowest) < 0 ) {

                lowest = temp;
            }
        }

        System.out.println("\n\nThe Lowest Name is: " + lowest);

    }

    public Iterator< String > getIterator() {
        return mySet.iterator();
    }

    public void add( String data ) {
        mySet.add(data);
    }

    public void remove( String data ) {
        mySet.remove(data);
    }

}
