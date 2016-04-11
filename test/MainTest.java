import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.AdditionalAnswers;

import robotCli.Playground;
import robotCli.RobotCli;
import robotCli.robot.Robot;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RobotCli.class, Playground.class, System.class})
public class MainTest {

  public Playground mockedGround;
  public Robot mockedRobot;
  public Console mockedConsole;

  @Before
  public void setup() throws Exception {
    mockedConsole = mock(Console.class);
    mockedGround = mock(Playground.class);
    mockedRobot = mock(Robot.class);
    whenNew(Playground.class).withArguments(anyInt(), anyInt()).thenReturn(mockedGround);
    whenNew(Robot.class).withNoArguments().thenReturn(mockedRobot);

    mockStatic(System.class);
    when(System.console()).thenReturn(mockedConsole);
    when(mockedConsole.readLine(anyString())).thenReturn(RobotCli.EXIT_COMMAND);
	}

  public void subject() {
    RobotCli.main(new String[0]);
  }

  @Test
	public void Main_AllocatesNewPlayground() throws Exception {
    int x = RobotCli.PLAYGROUND_WIDTH;
    int y = RobotCli.PLAYGROUND_HEIGHT;

    subject();

    verifyNew(Playground.class).withArguments(x, y);
	}

  @Test
  public void Main_AllocatesNewRobot() throws Exception {
    subject();

    verifyNew(Robot.class).withNoArguments();
  }

  @Test
  public void Main_SetsPlaygroundInTheRobot() throws Exception {
    subject();
    verify(mockedRobot).setPlayground(mockedGround);
  }

  @Test
  public void Main_ExecutesCommandsGivenViaCommandLine() {
    String command = "New Command";
    ArrayList<String> commandLineArgs = new ArrayList<String>(Arrays.asList(new String[]{command, RobotCli.EXIT_COMMAND}));
    when(mockedConsole.readLine(anyString())).thenAnswer(AdditionalAnswers.returnsElementsOf(commandLineArgs));

    subject();

    verify(mockedRobot, times(1)).execute(command);
  }
}
