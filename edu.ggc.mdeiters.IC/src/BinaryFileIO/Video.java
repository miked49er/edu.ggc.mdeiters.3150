package BinaryFileIO;

/**
 * Class: Video
 * 
 * @author Dr. Johnson
 * @version 1.0 Course : ITEC 3150, Fall, 2015 Written: January 18, 2012
 * 
 * 
 *          This class ? This class describes a subclass of Media called Video
 * 
 *          Purpose: ? Contains the attributes specific to a type of Media
 *          called Video.
 *
 */
public class Video extends Media
{
   private String definition;
   private String format;
   private String director;
   private String rating;
   private String genre;

   /**
    * Method:getDefinition() Getter method for the definition attribute
    * 
    * @return the definition
    */
   public String getDefinition()
   {
      return definition;
   }

   /**
    * Method:getDirector() Getter method for the director attribute
    * 
    * @return the director
    */
   public String getDirector()
   {
      return director;
   }

   /**
    * Method:getRating() Getter method for the rating attribute
    * 
    * @return the rating
    */
   public String getRating()
   {
      return rating;
   }

   /**
    * Method:getGenre() Getter method for the genre attribute
    * 
    * @return the genre
    */
   public String getGenre()
   {
      return genre;
   }

   /*
    * Method:toString() Converts the attributes of the class to a text readable
    * format.
    * 
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return super.toString() + " Video [definition=" + definition
            + ", format=" + format + ", director=" + director + ", rating="
            + rating + ", genre=" + genre + "]";
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
    * Method:Video() Constructor method that accepts values for all the
    * attributes and sets them.
    *
    * @param idNumber
    * @param itemName
    * @param type
    * @param definition
    * @param format
    * @param director
    * @param rating
    * @param genre
    */
   public Video(int idNumber, String itemName, String type, String definition,
         String format, String director, String rating, String genre)
   {
      super(idNumber, itemName, type);
      this.definition = definition;
      this.format = format;
      this.director = director;
      this.rating = rating;
      this.genre = genre;
   }

}
