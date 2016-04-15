package robotCli.robot;

import robotCli.Playground;

public class Robot {

	private Playground ground;
	private boolean placed;
	private CommandParser parser;

	public Robot() {
		this.parser = new CommandParser();
	}

  public void execute(String command) {
    // TODO Auto-generated method stub
  	this.validateRobotStatus();
  	this.parser.parse(command);
  }

	public boolean isPlaced() {
		return this.placed;
	}

	public void setPlayground(Playground palyground) {
  	this.ground = palyground;
  }

	private void validateRobotStatus() {
		// TODO Auto-generated method stub
		System.out.println("Validation");
	}

}
