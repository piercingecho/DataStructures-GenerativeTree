import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Asking
{
   public static boolean forInput(Scanner in, PrintStream out)
   {
      String line = in.nextLine();
      while(!line.equals("Y") && !line.equals("N") && !line.equals("n") && !line.equals("y"))
      {
         out.println("Please put only 'y' or 'n'.");
         line = in.nextLine();
      }
      
      if(line.equals("Y") || line.equals("y"))
      {
         return true;
      }
      else
      {
         return false;
      }

   }
}