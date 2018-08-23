package test.rest.demo.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Before
    public void setUp() throws Exception {
        logger.info("@Before");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("@After");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        logger.info("@BeforeClass");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        logger.info("@AfterClass");
    }

    @Test
    public void test1() throws Exception {
        logger.info("test1()");
    }

    @Test
    public void test2() throws Exception {
        logger.info("test2()");
    }

    @Test(expected = RuntimeException.class)
    public void testException() throws Exception {
        logger.info("testException()");
        throw new RuntimeException();
    }

    @Test(timeout = 1000L)
    public void testTimeout() throws Exception {
        logger.info("testTimeout()");
    }

    @Test
    @Ignore
    public void testIgnored() throws Exception {
        logger.info("testIgnored()");
    }
}
