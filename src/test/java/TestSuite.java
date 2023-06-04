import model.TestCompilation;
import model.TestGame;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TestGame.class,
        TestCompilation.class
})
public class TestSuite {
}
