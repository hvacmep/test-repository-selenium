package tests;

import app.Application;
import org.junit.Before;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app = new Application();

    @Before
    public void start() {

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
        }

    }