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

    public interface CLfileoperate extends Library{
        CLfileoperate INSTANCE =(CLfileoperate)Native.loadLibrary("CLfileoperate", CLfileoperate.class);
        public int createFolder(String path);
        public int createFile(String path);
        public int deleteFile(String path);
        public int alterFileName(String path,String filename,String newname);
        public int isExisteDirectory(String path);
        public int copyFile(String spath,String dpath);
        public int openFile(String path);
        public int copyFolder(String s_path,String d_path);
        public int deleteFolder(String path);
    }

