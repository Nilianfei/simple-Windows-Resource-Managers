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

    public interface getFileType extends Library{
        getFileType INSTANCE =(getFileType)Native.loadLibrary("getFileType", getFileType.class);
        public String getFileType(String filename);
    }

