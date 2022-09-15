package hu.modeldriven.cameo;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.tests.MagicDrawTestRunner;
import hu.modeldriven.cameo.action.MyBrowserAction;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;

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

    private void createAndSelectPackage(String name){
        try {

            SessionManager.getInstance().createSession(project, "Creating package");

            var factory = project.getElementsFactory();
            var newPackage = factory.createPackageInstance();
            newPackage.setName(name);

            var model = project.getModels().get(0);
            newPackage.setOwner(model);

            SessionManager.getInstance().closeSession(project);

            var activeTree = Application.getInstance().getMainFrame().getBrowser().getContainmentTree();
            activeTree.openNode(newPackage);

        } catch (Exception e) {
            Application.getInstance().getGUILog().showMessage("Exception occured: " + e.getMessage());
            SessionManager.getInstance().cancelSession(project);
        }
    }

    @Test
    public void testBrowserAction(){

        createAndSelectPackage("myPackage");

        var action = new MyBrowserAction("myId", "myName");
        var uniqueId = (int)System.currentTimeMillis();
        var event = new ActionEvent(this, uniqueId, "");
        action.actionPerformed(event);
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
    }

}
