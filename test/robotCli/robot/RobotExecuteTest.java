package robotCli.robot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.*;

import robotCli.Playground;
import robotCli.robot.Robot;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Robot.class, CommandParser.class})
public class RobotExecuteTest {

	public Robot robotSpy;
	public CommandParser parserMock = mock(CommandParser.class);
	public String command = "A Commmand";

	@Before
	public void setup() throws Exception {
		whenNew(CommandParser.class).withNoArguments().thenReturn(parserMock);
		robotSpy = spy(new Robot());
	}

	public void subject() {
		robotSpy.execute(command);
	}

	@Test
	public void execute_ChecksValidInstanceStatus() throws Exception {
		doNothing().when(robotSpy, "validateRobotStatus");

		subject();

		verifyPrivate(robotSpy).invoke("validateRobotStatus");
	}

	@Test(expected = IllegalStateException.class)
	public void execute_RaisesExceptionWhenInvalidInstanceStatus() throws Exception {
		doThrow(new IllegalStateException()).when(robotSpy, "validateRobotStatus");

		subject();
	}

	@Test
	public void execute_ParsesTheCommandString() {
		subject();
		verify(parserMock).parse(command);
	}

	@Test
	public void execute_ExecutesTheParsedCommand() {
		HashMap<String, HashMap<String, Object>> parsedCommand = new HashMap<>();
		doReturn(parsedCommand).when(parserMock).parse(anyString());
		subject();
		verify(parserMock).parse(command);
	}

}
