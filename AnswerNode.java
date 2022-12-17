import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AnswerNode implements Node
{  
   String answer;
   QuestionNode parent;
   
   public AnswerNode(String a, QuestionNode parent)
   {
      this.answer = a;
      this.parent = parent;
   }
   
   
   public boolean askQuestion(Scanner in, PrintStream out)
   {
      out.println(Strings.IS_IT_A + this.answer + "?");
      boolean answer = Asking.forInput(in, out);
      if(answer) return true;
      else
      {
         //make a new node etc here
         altertree(in, out);
         return false;
      }
   }
   
   public void altertree(Scanner in, PrintStream out)
   {
      out.println(Strings.WHAT_IS_THE_ANSWER);
      String a = in.nextLine();
      out.println(Strings.NEW_QUESTION + this.answer + " and a " + a);
      String q = in.nextLine();
      QuestionNode newQ = new QuestionNode(q, null, null);
      AnswerNode newAns = new AnswerNode(a, newQ);
      
      QuestionNode parent = this.parent; //the question right before this answer
      if(parent.yes == this) //if the parent points here
      {
         parent.yes = newQ;
      }
      else
      {
         parent.no = newQ;
      }
      this.parent = newQ;
      
      //at this point we have singularly linked answers to the question node, and the
      //question node is in the correct place in the tree. 
      
      out.println("Answering yes to " + newQ.question + " means " + newAns.answer + "?");
      boolean yes = Asking.forInput(in, out);
      if(yes)
      {
         newQ.yes = newAns;
         newQ.no = this;
      }
      else
      {
         newQ.yes = this;
         newQ.no = newAns;
      }
   }
}