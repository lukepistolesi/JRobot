package robotCli;

import java.io.Console;
import java.util.Scanner;

import robotCli.robot.Robot;

public class RobotCli {

	public final static int PLAYGROUND_WIDTH = 5;
  public final static int PLAYGROUND_HEIGHT = 5;
  public final static String EXIT_COMMAND = "Exit";

  public static void main(String[] args) {

    Playground ground = new Playground(PLAYGROUND_WIDTH, PLAYGROUND_HEIGHT);
	  Robot robot = new Robot();

	  robot.setPlayground(ground);

	  Console console = System.console();

	  String command = console.readLine("Next Command: ");
	  while(command != EXIT_COMMAND) {
	    System.out.println("Executing command: " + command);
	    robot.execute(command);
	    command = console.readLine("Next Command: ");
	  }
	}

}
