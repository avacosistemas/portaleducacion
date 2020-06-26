package ar.com.avaco;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public class AppContextAuthenticationClienteTest {

	@Test
	public void appContextTest() {
		assertTrue(true);
	}

}