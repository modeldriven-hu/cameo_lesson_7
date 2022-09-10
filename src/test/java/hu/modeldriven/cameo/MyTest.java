package hu.modeldriven.cameo;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.tests.MagicDrawTestRunner;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

//@RunWith(MagicDrawTestRunner.class)
public class MyTest {

    private static Project project;

    @BeforeClass
    public static void createProject()  {
        try {
            var main = new MagicDrawTestRunner(MyTest.class);
            project = Application.getInstance().getProjectsManager().createProject();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void closeProject() {
        Application.getInstance().getProjectsManager().closeProject();
        project = null;
    }

    @Test
    public void testSomething() throws Exception {
        var frame = GuiActionRunner.execute(() ->new SimpleCopyApplication());
        var window = new FrameFixture(frame);
        window.show();
        window.moveTo(new Point(200, 200));
        window.button("myButton").click();
        var label = window.label("myLabel").text();

        Assert.assertEquals("Random gibberish", label);

        System.out.println("And we wait");
    }

}
