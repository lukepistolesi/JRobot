package robotCli.robot;

import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Robot.class, CommandParser.class})
public class RobotConstructorTest {

	public CommandParser parserMock = mock(CommandParser.class);

	@Before
	public void setup() throws Exception {
		whenNew(CommandParser.class).withNoArguments().thenReturn(parserMock);
	}

	@Test
	public void constructor_AllocatesNewParser() {
		Robot robot = new Robot();
		assertEquals(parserMock, Whitebox.getInternalState(robot, "parser"));
	}

	@Test
	public void constructor_SetsPlacedToFalse() {
		Robot robot = new Robot();
		assertFalse(robot.isPlaced());
	}
}
