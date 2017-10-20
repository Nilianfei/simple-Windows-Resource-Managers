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
    public interface getJustCurrentFiles extends Library{
        getJustCurrentFiles INSTANCE =(getJustCurrentFiles)Native.loadLibrary("getJustCurrentFiles", getJustCurrentFiles.class);
        public String getJustCurrentFiles(String path);
    }


