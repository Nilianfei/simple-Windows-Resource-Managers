/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSystem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Afei
 */
public class watchProcessInfoThread extends Thread{
    public void run(){
        while(true){
            if(ProcessSystem.mtm.paintAllProcess())
                ProcessSystem.mtm.fireTableDataChanged();
            try {
                sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(watchProcessInfoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
