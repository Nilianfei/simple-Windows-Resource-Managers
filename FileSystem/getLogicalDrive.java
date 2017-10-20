/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import com.sun.jna.*;

/**
 *
 * @author Afei
 */

    public interface getLogicalDrive extends Library{
        getLogicalDrive INSTANCE =(getLogicalDrive)Native.loadLibrary("getLogicalDrive", getLogicalDrive.class);
        public int getLogicalDrive();
    }

