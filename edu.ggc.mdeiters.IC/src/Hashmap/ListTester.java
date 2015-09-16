package Hashmap;

/**
 A program that tests the LinkedList class
 */
public class ListTester {
    public static void main( String[] args ) {
        LinkedList staff = new LinkedList();
        staff.addFirst("Diana");
        staff.addFirst("Harry");
        staff.addFirst("Romeo");
        staff.addFirst("Tom");

        // | in the comments indicates the iterator position

        ListIterator iterator = staff.listIterator(); // |DHRT
        iterator.next(); // D|HRT
        iterator.next(); // DH|RT

        // Add more elements after second element

        iterator.add("Juliet"); // DHJ|RT
        iterator.add("Nina"); // DHJN|RT

        iterator.next(); // DHJNR|T

        // Remove last traversed element

        iterator.remove(); // DHJN|T

        LinkedList staff2 = new LinkedList();
        staff2.addFirst("Homer");
        staff2.addFirst("Marge");
        staff2.addFirst("Lisa");
        staff2.addFirst("Bart");

        staff.transferFrom(staff2);

        // Print all elements
        // reset iterator
        System.out.println("The final staff list contains: ");
        iterator = staff.listIterator();
        while ( iterator.hasNext() ) {
            String name = (String) iterator.next();
            System.out.print(name + " ");
        }

        System.out.println();

        // Print all elements
        // reset iterator
        System.out.println("The final staff2 list contains: ");
        iterator = staff2.listIterator();
        while ( iterator.hasNext() ) {
            String name = (String) iterator.next();
            System.out.print(name + " ");
        }

        System.out.println();
        System.out.println();

    }
}
