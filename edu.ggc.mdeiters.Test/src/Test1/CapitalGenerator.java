package Test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CapitalGenerator
{

    public static void main(String[] args)
    {
        try
        {
       // create file to write
        ObjectOutputStream capitalWriter = new ObjectOutputStream(new FileOutputStream("capitals.dat"));
        ArrayList<StateCapital> list = new ArrayList<StateCapital>();
         
        StateCapital temp = new StateCapital("Montgomery", "Alabama");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Raleigh", "North Carolina");
        list.add(temp);
        capitalWriter.writeObject(temp);
        temp = new StateCapital("Atlanta", "Georgia");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Inidanapolis", "Indiana");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Jackson", "Mississippi");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Columbia", "South Carolina");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Harrisburg", "Pennsylvania");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Columbus", "Ohio");
        capitalWriter.writeObject(temp);
        list.add(temp);
        temp = new StateCapital("Tallhasee", "Florida");
        capitalWriter.writeObject(temp);
        list.add(temp);
        
        
        for (StateCapital c : list)
        {
            System.out.println("Hashcode for " + c.getState() + " is " + c.hashCode());
        }
        }
        
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
