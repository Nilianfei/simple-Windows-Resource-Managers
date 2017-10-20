package FileSystem;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Created by windy on 2017/8/8.
 */
public interface _GetTargetFiles extends Library{
    _GetTargetFiles instanceDll = (_GetTargetFiles) Native.loadLibrary("getTargetFilesDll", _GetTargetFiles.class);
    int getFilesPath(String curDir, String tarDir);
    String getAllFilesPath();
}
