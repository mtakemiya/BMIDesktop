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
 */
public class ExplorerChildren extends Children.Keys {

    public ExplorerChildren(String parentPath) {
        File target = new File(parentPath);

        if (target.isDirectory()) {
            File[] listFile = target.listFiles();
            int length = target.listFiles().length;
            GeneralFileInfo[] objs = new GeneralFileInfo[length];

            for (int c = 0; c < length; c++) {
                objs[c] = new GeneralFileInfo(listFile[c].getAbsolutePath(),"");
            }

            setKeys(objs);
        }
    }

    public ExplorerChildren() {
        GeneralFileInfo[] objs = new GeneralFileInfo[1];
        objs[0] = new GeneralFileInfo(new File(Constants.DATA_HOME).getAbsolutePath(),"");
        setKeys(objs);
    }

    @Override
    protected Node[] createNodes(Object t) {
        GeneralFileInfo obj = (GeneralFileInfo) t;
//        AbstractNode result = new AbstractNode(new ExplorerChildren(obj.getFilePath()), Lookups.singleton(obj));
//        result.setDisplayName(obj.toString());
//        result.setDisplayName(obj.getFileName());
        return new Node[]{ new ExplorerNode(obj)};
    }
//    @Override
//    protected void addNotify() {
//        GeneralFileInfo[] objs = new GeneralFileInfo[5];
//        for (int i = 0; i < objs.length; i++){
//            objs[i] = new GeneralFileInfo("C:\\Users\\kharada\\99_temp\\20110105\\test001.txt", Integer.toString(i));
//        }
//        setKeys(objs);
//    }
}
