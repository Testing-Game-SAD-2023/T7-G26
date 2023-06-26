package requirement_t7;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import requirement_t7.controller.TestGameController;
import requirement_t7.controller.TestHomeController;
import requirement_t7.model.TestCompilation;
import requirement_t7.model.TestExecution;
import requirement_t7.model.TestGame;

@Suite
@SelectClasses({
        TestGame.class,
        TestCompilation.class,
        TestExecution.class,
        TestGameController.class,
        TestHomeController.class
})
public class TestSuite {
}