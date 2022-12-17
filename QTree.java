import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
   QuestionNode root;
	
	Scanner in;
	PrintStream out;
	
    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
      AnswerNode rock = new AnswerNode(Strings.ROCK, null);
      AnswerNode duck = new AnswerNode(Strings.DUCK, null);
		root = new QuestionNode(Strings.IS_IT_ALIVE, duck, rock);
      duck.parent = this.root;
      rock.parent = this.root;
      
	}

    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		//??
      boolean stillPlaying = true;
      while(stillPlaying)
      {
        boolean wasRight = askQuestion();
        if(wasRight)
        {
          out.println(Strings.I_WIN);
        }
        else out.println(Strings.THANKS);
        
        out.println(Strings.PLAY_AGAIN);
        stillPlaying = Asking.forInput(this.in, this.out);
      }
        
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
   
   public boolean askQuestion()
   {
      return root.askQuestion(this.in, this.out);
   }
	
	
}