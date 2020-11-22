package Test;

import org.junit.*;

import static Inter_Thread_Communication.Synchronized_keywork.process;

public class InterThreadCommunicationTest {
    @BeforeClass
    public static void preSetup() {
        System.out.println("preSetup...\n");
    }

    @AfterClass
    public static void postTeardown() {
        System.out.println("post-teardown...");
    }

    @Before
    public void setup() {
        System.out.println("Setup...");
    }

    @After
    public void teardown() {
        System.out.println("Teardown...\n");
    }

    @Test
    public void testSynchronization() {
        boolean result = process();
        Assert.assertNotEquals(false, result);
        System.out.println("Assert...");
    }
}
