/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Afei
 */
public class TableRenderPS extends AbstractCellEditor implements TableCellRenderer,ActionListener,TableCellEditor{

    public TableRenderPS(){
        
    }
    public static final DefaultTableCellRenderer DEFAULT_RENDERER =new DefaultTableCellRenderer();
    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer =DEFAULT_RENDERER.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
        if(ProcessSystem.row==row){
            renderer.setBackground(Color.GRAY);
        }
        else {
            renderer.setBackground(Color.WHITE);
        }
        return renderer;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return (Component)value;
    }
    
}
