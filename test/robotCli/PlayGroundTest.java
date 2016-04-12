package robotCli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Playground.class)
public class PlayGroundTest {

	@Test
	public void Constructor_SetsInstanceVariables() {
		int x = 1, y = 3;

		Playground pg = new Playground(x, y);

		assertEquals(x,	pg.getWidth());
		assertEquals(y,	pg.getHeight());
	}
}
