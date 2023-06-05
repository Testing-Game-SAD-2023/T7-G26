import controller.TestGameController;
import controller.TestHomeController;
import model.TestCompilation;
import model.TestExecution;
import model.TestGame;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

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
