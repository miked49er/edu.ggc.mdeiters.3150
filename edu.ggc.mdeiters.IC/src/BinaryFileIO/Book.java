package BinaryFileIO;

/**
 * Class: Book
 * 
 * @author Dr. Johnson
 * @version 1.0 Course : ITEC 3150, Fall, 2015 Written: January 18, 2012
 * 
 * 
 *          This class ? This class describes a subclass of Media called Book
 * 
 *          Purpose: ? Contains the attributes specific to a type of Media
 *          called Book.
 *
 */
public class Book extends Media
{
   private String format;
   private String author;

   /**
    * Method:Book()
    * 
    * Constructor method that accepts values for all the attributes and sets
    * them.
    * 
    * @param idNumber
    * @param itemName
    * @param type
    * @param format
    * @param author
    */
   public Book(int idNumber, String itemName, String type, String format,
         String author)
   {
      super(idNumber, itemName, type);
      this.format = format;
      this.author = author;
   }

   /*
    * Method:toString() Converts the attributes of the class to a text readable
    * format.
    * 
    * 
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return super.toString() + " Book [format=" + format + ", author="
            + author + "]";
   }

}
