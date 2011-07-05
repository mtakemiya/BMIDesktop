/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.explorereditor;

import java.io.File;
import java.util.ArrayList;
import jp.atr.dni.bmi.desktop.model.Constants;
import jp.atr.dni.bmi.desktop.model.FileType;
import jp.atr.dni.bmi.desktop.model.GeneralFileInfo;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 * 
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class ExplorerChildren extends Children.Keys {

   /**
    *
    * @param obj
    */
   public ExplorerChildren(GeneralFileInfo obj) {

      FileType nodeType = obj.getFileType();
      String targetPath = obj.getFilePath();

      if (nodeType == FileType.DIRECTORY) {

         // Get Child Nodes.
         File target = new File(targetPath);
         File[] listFile = target.listFiles();
         if (listFile == null) {
            // Cannot get children info.
            // exam. hidden directory.
            return;
         }
         int length = target.listFiles().length;

         ArrayList<GeneralFileInfo> childNodes = new ArrayList<GeneralFileInfo>();
         for (int ii = 0; ii < length; ii++) {
            File currFile = listFile[ii];
            String childNodePath = currFile.getAbsolutePath();
            System.out.println("curr type: " + FileType.fromFileName(currFile.getName()));
            if (currFile.isDirectory() && childNodePath.indexOf('.') != -1) {
               // Do not display "hidden" directory.
               continue;
            } else if (currFile.isFile() && listFile[ii].getName().startsWith(".")) {
               // Do not display "hidden" file.
               continue;
            } else if (!currFile.isDirectory() && !FileType.isDataFile(FileType.fromFileName(currFile.getName()))) {
               //Skip non data files
               continue;
            }
            childNodes.add(new GeneralFileInfo(childNodePath));
         }
         setKeys(childNodes.toArray());
      }
   }

   /**
    *
    */
   public ExplorerChildren() {
      GeneralFileInfo[] objs = new GeneralFileInfo[1];
      objs[0] = new GeneralFileInfo(new File(Constants.DATA_HOME).getAbsolutePath());
      objs[0].setFileName("Root");
      setKeys(objs);
   }

   /**
    *
    * @param t
    * @return
    */
   @Override
   protected Node[] createNodes(Object t) {
      GeneralFileInfo obj = (GeneralFileInfo) t;
      return new Node[]{new ExplorerNode(obj)};
   }

   /**
    *
    * @param t
    * @return
    */
   public boolean remove(Object t) {
      GeneralFileInfo obj = (GeneralFileInfo) t;
      Node a = findChild(obj.getFileName());
      return remove(a);
   }
}
