package hu.modeldriven.cameo.action;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.CoreHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.*;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.impl.ElementsFactory;

public class ModelStructureGenerator {

    private final Project project;
    private final ElementsFactory factory;
    private final ModelElementsManager manager;

    public ModelStructureGenerator() {
        this.project = Application.getInstance().getProject();
        this.factory = project.getElementsFactory();
        this.manager = ModelElementsManager.getInstance();
    }

    public void execute(Package parentPackage) {
        try {
            SessionManager.getInstance().createSession(project, "Creating model elements from lesson 3");
            createModelElements(parentPackage);
            SessionManager.getInstance().closeSession(project);
        } catch (Exception e) {
            Application.getInstance().getGUILog().showMessage("Exception occured: " + e.getMessage());
            SessionManager.getInstance().cancelSession(project);
        }
    }

    private void createModelElements(Package parentPackage) throws ReadOnlyElementException {
        var firstClass = createClass(parentPackage, "First class");
    }

    private Class createClass(Package parentPackage, String name) throws ReadOnlyElementException {

        var mdClass = factory.createClassInstance();
        mdClass.setName(name);
        manager.addElement(mdClass, parentPackage);

        return mdClass;
    }

}
