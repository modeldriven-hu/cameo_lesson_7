package hu.modeldriven.cameo;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.tests.MagicDrawTestRunner;
import com.nomagic.magicdraw.uml.ConvertElementInfo;
import com.nomagic.magicdraw.uml.Refactoring;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.components.mdbasiccomponents.Component;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MagicDrawTestRunner.class)
public class MyTest {

    private static Project project;

    @BeforeClass
    public static void createProject() {
        project = Application.getInstance().getProjectsManager().createProject();
    }

    public static void closeProject() {
        Application.getInstance().getProjectsManager().closeProject();
        project = null;
    }

    @Test
    public void testSomething() throws Exception {
        var classInstance = createClass("A");
        var info = new ConvertElementInfo(Component.class);
        info.setPreserveElementID(true);
        String oldId = classInstance.getID();

        SessionManager.getInstance().createSession(project, "convert to component");
        Element converted = Refactoring.Converting.convert(classInstance, info);
        SessionManager.getInstance().closeSession(project);

        assertTrue(converted instanceof Component);
        assertEquals(oldId, converted.getID());
    }

    private static Element createClass(String name) {
        SessionManager.getInstance().createSession(project, "create class " + name);
        var classInstance = project.getElementsFactory().createClassInstance();
        classInstance.setOwner(project.getPrimaryModel());
        classInstance.setName(name);
        SessionManager.getInstance().closeSession(project);
        return classInstance;
    }

}
