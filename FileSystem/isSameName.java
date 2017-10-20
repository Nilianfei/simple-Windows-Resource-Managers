/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSystem;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 *
 * @author Afei
 */

    public interface isSameName extends Library{
        isSameName INSTANCE =(isSameName)Native.loadLibrary("isSameName", isSameName.class);
        public int isSamefileName(String filename, String filepath);
        public int isSamefolderName(String foldername,String folderpath);
    }

