/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.explorereditor;

import java.io.File;
import jp.atr.dni.bmi.desktop.model.Constants;
import jp.atr.dni.bmi.desktop.model.GeneralFileInfo;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author kharada
 * @version 2011/01/24
 */
public class ExplorerChildren extends Children.Keys {

    public ExplorerChildren(GeneralFileInfo obj) {

        String nodeType = obj.getFileType();
        String targetPath = obj.getFilePath();

        if (nodeType.equals("Directory")) {
            File target = new File(targetPath);
            File[] listFile = target.listFiles();
            int length = target.listFiles().length;
            GeneralFileInfo[] objs = new GeneralFileInfo[length];
            for (int c = 0; c < length; c++) {
                objs[c] = new GeneralFileInfo(listFile[c].getAbsolutePath());
            }
            setKeys(objs);
        }
    }

    public ExplorerChildren() {
        GeneralFileInfo[] objs = new GeneralFileInfo[1];
        objs[0] = new GeneralFileInfo(new File(Constants.DATA_HOME).getAbsolutePath());
        setKeys(objs);
    }

    @Override
    protected Node[] createNodes(Object t) {
        GeneralFileInfo obj = (GeneralFileInfo) t;
        return new Node[]{new ExplorerNode(obj)};
    }
}
