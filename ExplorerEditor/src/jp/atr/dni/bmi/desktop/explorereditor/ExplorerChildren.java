/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.explorereditor;

import java.io.File;
import jp.atr.dni.bmi.desktop.model.Constants;
import jp.atr.dni.bmi.desktop.model.NodeData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.NSReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.NeuroshareFile;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author kharada
 * @version 2011/01/24
 */
public class ExplorerChildren extends Children.Keys {

    public ExplorerChildren(NodeData nodeData) {

        String nodeType = nodeData.getNodeType();
        String targetPath = nodeData.getTargetPath();

        if (nodeType.equals("Directory")) {
            File target = new File(targetPath);
            File[] listFile = target.listFiles();
            int length = target.listFiles().length;
            NodeData[] objs = new NodeData[length];
            for (int c = 0; c < length; c++) {
                objs[c] = new NodeData(listFile[c].getAbsolutePath());
            }
            setKeys(objs);
        } else if (nodeType.equals("File/nsn")) {

            NeuroshareFile nsf = new NSReader().readNSFileOnlyInfo(targetPath);
            int entityCount = (int) nsf.getFileInfo().getEntityCount();
            NodeData[] objs = new NodeData[entityCount];
            for (int ii = 0; ii < entityCount; ii++) {
                Entity entity = nsf.getEntities().get(ii);
                switch (entity.getTag().getElemType()) {
                    case ENTITY_EVENT:
                        objs[ii] = new NodeData("Neuroshare/eventinfo", entity.getEntityInfo().getEntityLabel(), targetPath);
                        break;
                    case ENTITY_ANALOG:
                        objs[ii] = new NodeData("Neuroshare/analoginfo", entity.getEntityInfo().getEntityLabel(), targetPath);
                        break;
                    case ENTITY_SEGMENT:
                        objs[ii] = new NodeData("Neuroshare/segmentinfo", entity.getEntityInfo().getEntityLabel(), targetPath);
                        break;
                    case ENTITY_NEURAL:
                        objs[ii] = new NodeData("Neuroshare/neuralinfo", entity.getEntityInfo().getEntityLabel(), targetPath);
                        break;
                    default:
                        break;
                }
                objs[ii].setEntity(entity);
            }
            setKeys(objs);
        }
    }

    public ExplorerChildren() {
//        GeneralFileInfo[] objs = new GeneralFileInfo[1];
//        objs[0] = new GeneralFileInfo(new File(Constants.DATA_HOME).getAbsolutePath());
        NodeData[] objs = new NodeData[1];
        objs[0] = new NodeData(new File(Constants.DATA_HOME).getAbsolutePath());
        setKeys(objs);
    }

    @Override
    protected Node[] createNodes(Object t) {
        NodeData nd = (NodeData) t;
        return new Node[]{new ExplorerNode(nd)};
    }
}
