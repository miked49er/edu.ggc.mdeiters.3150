package BinaryFileIO;

/**
 * Class: Music
 * 
 * @author Dr. Johnson
 * @version 1.0 Course : ITEC 3150, Fall, 2015 Written: January 18, 2012
 * 
 * 
 *          This class ? This class describes a subclass of Media called Music
 * 
 *          Purpose: ? Contains the attributes specific to a type of Media
 *          called Music.
 *
 */
public class Music extends Media
{
   private String format;
   private String artist;

   /**
    * Method:Music()
    * 
    * Constructor method that accepts values for all the attributes and sets
    * them.
    * 
    * @param idNumber
    * @param itemName
    * @param type
    * @param format
    * @param artist
    */
   public Music(int idNumber, String itemName, String type, String format,
         String artist)
   {
      super(idNumber, itemName, type);
      this.format = format;
      this.artist = artist;
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
      return super.toString() + " Music [format=" + format + ", artist="
            + artist + "]";
   }

   /**
    * Method:getFormat() Getter method for the format attribute
    * 
    * @return the format
    */
   public String getFormat()
   {
      return format;
   }

   /**
    * Method:getArtist() Getter method for the artist attribute
    * 
    * @return the artist
    */
   public String getArtist()
   {
      return artist;
   }

}
