package de.fh_kiel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CheckNullAspectTest {


    @Autowired
    private CheckNullTestService checkNullTestService;

    @Before
    public void setup() {
        checkNullTestService.setTestNotNullExecuted(false);
        checkNullTestService.setTestNotNullSilentExecuted(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotNull() {
        checkNullTestService.testNotNull(null);
    }

    @Test
    public void testNotNullSilent() {
        checkNullTestService.testNotNullSilent(null);
        assertFalse("method body was executed", checkNullTestService.isTestNotNullSilentExecuted());
    }

    @Test
    public void testEveryThingOk() {
        checkNullTestService.testNotNull(1L);
        assertTrue("method body was not executed", checkNullTestService.isTestNotNullExecuted());
        checkNullTestService.testNotNullSilent(1L);
        assertTrue("method body was not executed", checkNullTestService.isTestNotNullSilentExecuted());
    }
}