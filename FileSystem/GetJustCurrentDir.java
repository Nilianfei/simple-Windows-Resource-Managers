/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;
import com.sun.jna.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
/**
 *
 * @author Afei
 */
public class GetJustCurrentDir {
    public interface getJustCurrentDir extends Library{
        getJustCurrentDir INSTANCE =(getJustCurrentDir)Native.loadLibrary("getJustCurrentDir", getJustCurrentDir.class);
        public String getJustCurrentDir(String path);
    }

    /**
     * @param args the command line arguments
     */
    public static void paintCurrentDir(DefaultMutableTreeNode father) {
        // TODO code application logic here
        if(father==null||father.isLeaf())return;
        if(father.getFirstChild().toString().equals(""))
            father.removeAllChildren();
        else return;
        String path=father.toString();
        TreeNode parentNode=father.getParent();
        while(parentNode!=null&&!parentNode.toString().equals("¼ÆËã»ú"))
        {
            path=parentNode.toString()+"\\"+path;
            //System.out.println(path);
            parentNode=parentNode.getParent();
        }
        System.setProperty("jna.encoding","GBK" );
        String fn=getJustCurrentDir.INSTANCE.getJustCurrentDir(path);
        //System.out.println(fn);
        String[] fns=fn.split("\\|");
        for(int i=1;i<fns.length;i++)
        {
            DefaultMutableTreeNode son=new DefaultMutableTreeNode(fns[i]);
            String isChild=getJustCurrentDir.INSTANCE.getJustCurrentDir(path+"\\"+fns[i]);
            if(!isChild.equals("0")){
            DefaultMutableTreeNode node=new DefaultMutableTreeNode();
            son.add(node);
            }
            father.add(son);
        }
    }
}
