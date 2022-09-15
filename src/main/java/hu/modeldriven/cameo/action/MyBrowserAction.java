package hu.modeldriven.cameo.action;

import com.nomagic.magicdraw.ui.browser.actions.DefaultBrowserAction;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

import java.awt.event.ActionEvent;

public class MyBrowserAction extends DefaultBrowserAction {

    public MyBrowserAction(String id, String name) {
        super(id, name, null, null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var tree = getTreeOrActiveTree();

        var selectedNode = tree.getSelectedNode();

        if (selectedNode != null) {
            if (selectedNode.getUserObject() instanceof Package) {
                var parentPackage = (Package) selectedNode.getUserObject();

                var generator = new ModelStructureGenerator();
                generator.execute(parentPackage);
            }
        }
    }
}
