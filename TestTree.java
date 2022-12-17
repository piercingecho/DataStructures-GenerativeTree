import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		
		check(Strings.IS_IT_ALIVE);
        say("y");
        //now what? Think of all the input and outputs here...
		  check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("y");
        
        check(Strings.IS_IT_ALIVE);
        say("y");
        check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("n");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Duck Hunt");
        check(Strings.NEW_QUESTION + Strings.DUCK + " and a Duck Hunt");
        say("Is there also a dog");
        check("Answering yes to " + "Is there also a dog" + " means " + "Duck Hunt?");
        say("y");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("y");
        
        check(Strings.IS_IT_ALIVE);
        say("y");
        check("Is there also a dog");
        say("n");
        check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("y");
        
        check(Strings.IS_IT_ALIVE);
        say("y");
        check("Is there also a dog");
        say("n");
        check(Strings.IS_IT_A + Strings.DUCK + "?");
        say("n");
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Rabbit");
        check(Strings.NEW_QUESTION + Strings.DUCK + " and a Rabbit");
        say("Is it a bird");
        check("Answering yes to " + "Is it a bird" + " means " + "Rabbit?");
        say("n");
        check(Strings.THANKS);
        check(Strings.PLAY_AGAIN);
        say("y");

       check(Strings.IS_IT_ALIVE);
        say("y");
        check("Is there also a dog");
        say("n");
        check("Is it a bird");
        say("n");
        check(Strings.IS_IT_A + "Rabbit" + "?");
        say("y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("n");

        
        //close the streams at the end to enrue good behavior.
      comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		
	}
	
	
}