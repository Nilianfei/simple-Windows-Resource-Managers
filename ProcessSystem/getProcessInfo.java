/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSystem;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * @author Afei
 */
public interface getProcessInfo extends Library{
        getProcessInfo INSTANCE =(getProcessInfo)Native.loadLibrary("getProcessInfo", getProcessInfo.class);
        public String traverseProcesses();
}
