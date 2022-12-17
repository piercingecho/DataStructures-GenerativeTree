import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QuestionNode implements Node
{
   String question;
   Node yes;
   Node no;
   
   public QuestionNode(String q, Node yes, Node no)
   {
      this.question = q;
      this.yes = yes;
      this.no = no;
   }
   
   public boolean askQuestion(Scanner in, PrintStream out)
   {
      out.println(this.question);
      boolean traverse = Asking.forInput(in, out);
      if(traverse) return yes.askQuestion(in, out);
      else return no.askQuestion(in, out);      
   }
}