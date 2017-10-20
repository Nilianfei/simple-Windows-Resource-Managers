/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSystem;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Afei
 */
public class MyTableModelPS extends AbstractTableModel{
    static String[] columnNames={"映像名称","PID","线程数"};
    static Object[][] data=null;
    public MyTableModelPS(){};

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
    public boolean paintAllProcess(){
        String process=getProcessInfo.INSTANCE.traverseProcesses();
        if(process.equals("0"))return false;
        String[] SProcess=process.split("\n");
        int processnum=SProcess.length;
        ProcessSystem.jLabelPn.setText(String.valueOf(processnum));
        data=new Object[processnum][3];
        for(int i=0;i<processnum;i++){
            String[] AProcess=SProcess[i].split("\\|");
            data[i][0]=AProcess[0];
            data[i][1]=AProcess[1];
            data[i][2]=AProcess[2];
        }
        return true;
    }
}
