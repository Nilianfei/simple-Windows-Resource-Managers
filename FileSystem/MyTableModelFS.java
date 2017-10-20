/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.List;

/**
 *
 * @author Afei
 */
public class MyTableModelFS extends AbstractTableModel{

    static String[] columnNames={" ","名称","类型"};
    static Object[][] data=null;
    public MyTableModelFS(){};
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    @Override
    public int getRowCount() {
        if(data==null)return 0;
        return data.length;
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(data==null)return null;
        return data[rowIndex][columnIndex];
    }
    @Override   
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
	data[rowIndex][columnIndex] = aValue;
	fireTableCellUpdated(rowIndex, columnIndex);
    }
    public static void startTable(){
        DefaultMutableTreeNode root=(DefaultMutableTreeNode)FileSystem.jTree.getModel().getRoot();
        int childcount=root.getChildCount();
        data=new Object[childcount][3];
        for(int i=0;i<childcount;i++)
        {
            Icon icon=getFileIcon(root.getChildAt(i).toString()+"\\");
            JLabel label=new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBackground(Color.WHITE);
            data[i][0]=label;
            data[i][1]=root.getChildAt(i).toString();
            data[i][2]="存储器";
        }
    }
    public  boolean searchTable(String fn,String fp){
        List<String> paths=new LinkedList();
        try {
            paths = new SearchFileConcurrecy(fp+"\\", fn).getResults();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyTableModelFS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(gotoWyw(paths))return true;
        else return false;
    }
    public static boolean rencentfileTable(String LrufileName){
        List<String> paths=new LinkedList();
        try{
            String encoding="GBK";
            File file=new File(LrufileName);
            if(file.isFile()&&file.exists()){
                InputStreamReader read=new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader=new BufferedReader(read);
                String lineTxt=null;
                while((lineTxt=bufferedReader.readLine())!=null){
                    paths.add(lineTxt);
                }
                read.close();
            }
            else{
                System.out.println("can not find the file");
                }
        }catch(Exception e){
            System.out.println("rending text error");
            e.printStackTrace();
        }
        if(gotoWyw(paths))return true;
        else return false;
    }
    public static boolean gotoWyw(List<String> paths){
        if(paths.isEmpty()){
            data=null;
            return true;
        }
        int listsize=paths.size();
        data=new Object[listsize][3];
        for(int i=0;i<listsize;i++){
            data[i][2]=paths.get(i);
            String filepath=paths.get(i);
            Icon icon=getFileIcon(filepath);
            JLabel label=new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBackground(Color.WHITE);
            data[i][0]=label;
            String[] filenames=null;
            if(filepath.contains("\\")){
                filepath=filepath.replace('\\', '|');
                filenames=filepath.split("\\|");
            }
            else {
                filenames=new String[1];
                filenames[0]=filepath;
            }
            data[i][1]=filenames[filenames.length-1];
        }
        return true;
    }
    public boolean gotoCurrentDir(String path){
        if(path.contains("|")){
            String[] searchInfo=path.split("\\|");
            if(searchTable(searchInfo[0],searchInfo[1]))return true;
            else return false;
        }
        if(path.equals("最近访问的位置")){
            return rencentfileTable("rencentLru.txt");
        }
        if(path.equals("")){
            startTable();
            return true;
        }
        System.setProperty("jna.encoding","GBK" );
        String fn=getJustCurrentFiles.INSTANCE.getJustCurrentFiles(path);
        String dn=GetJustCurrentDir.getJustCurrentDir.INSTANCE.getJustCurrentDir(path);
        if(dn.equals("0")&&fn.equals("0")){
            data=null;
            return true;
        }
        int size=0,dnslength=0;
        String[] dns=null;
        String[] fns=null;
        if(!dn.equals("0")){
            dn=dn.substring(1, dn.length());
            dns=dn.split("\\|");
            dnslength=dns.length;
            size=size+dns.length;
        }
        if(!fn.equals("0")){
            fn=fn.substring(1,fn.length());
            fns=fn.split("\\|");
            size=size+fns.length;
        }
        data=new Object[size][3];
        if(!dn.equals("0")){
            for(int i=0;i<dnslength;i++){
                String filePath=path+"\\"+dns[i];
                Icon icon=getFileIcon(filePath);
                JLabel label=new JLabel();
                label.setOpaque(true);
                label.setIcon(icon);
                label.setBackground(Color.WHITE);
                data[i][0]=label;
                data[i][1]=dns[i];
                data[i][2]="文件夹";
            }
        }
        if(!fn.equals("0")){
            for(int i=dnslength;i<size;i++)
            {
                String filePath=path+"\\"+fns[i-dnslength];
                Icon icon=getFileIcon(filePath);
                JLabel label=new JLabel();
                label.setOpaque(true);
                label.setIcon(icon);
                label.setBackground(Color.WHITE);
                data[i][0]=label;
                data[i][1]=fns[i-dnslength];
                String ft=getFileType.INSTANCE.getFileType(fns[i-dnslength]);
                data[i][2]=ft;
            }
        }
        return true;
    }
    public String paintCurrentFiles(DefaultMutableTreeNode father) {
        if(father==null)return "";
        if(father.toString().equals("计算机")){startTable();return "";}
        if(!father.isLeaf()&&father.getFirstChild().toString().equals(""))
            father.removeAllChildren();
        String path=father.toString();
        TreeNode parentNode=father.getParent();
        while(parentNode!=null&&!parentNode.toString().equals("计算机"))
        {
            path=parentNode.toString()+"\\"+path;
            parentNode=parentNode.getParent();
        }
        if(gotoCurrentDir(path))
        return path;
        else return "";
    }  
    private static Icon getFileIcon(String filePath) {
        File f=new File(filePath);
        if(f.exists())
        {
            FileSystemView fsv=FileSystemView.getFileSystemView();
            Icon icon=fsv.getSystemIcon(f);
            return icon;
        }
        return null;
    }
}
