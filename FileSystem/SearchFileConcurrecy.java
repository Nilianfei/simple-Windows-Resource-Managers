package FileSystem;

import FileSystem.utils.FormatString;
import FileSystem.utils.MyFileFilter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by windy on 2017/8/10.
 * @usege new SearchFileConcurrecy(dir, file).getResult()
 */
public class SearchFileConcurrecy {
    private String diretory;  //待搜索文件夹
    private String targetFile;  //目标文件
    private List<String> results = new LinkedList<>(); //同步容器

    public SearchFileConcurrecy(String diretory, String targetFile) {
        this.diretory = diretory;
        this.targetFile = targetFile;
    }

    /**
     * 打开指定文件夹，获取所有文件
     * 对文件进行变量
     * 若是子目录，开辟线程进行搜索
     * 若是文件，直接进行匹配
     */
    public List<String> getResults() throws InterruptedException {
        File files = new File(diretory);
        if (!files.exists() || !files.isDirectory()) {
            System.err.println("Invalid Path！");
            return null;
        }

        File[] fileList = files.listFiles();
        if (fileList != null || fileList.length > 0) {
            CountDownLatch latch = new CountDownLatch(fileList.length);
            for (File file : fileList) {
                if (file.isDirectory()) {
                   // lambda
                    new Thread(() -> {
                        _GetTargetFiles.instanceDll.getFilesPath(file.getAbsolutePath(), targetFile);
                        latch.countDown();
                    }).start();
                } else {
                    latch.countDown(); //非文件夹 latch也需计数
                }
                MyFileFilter.getInstance().filter(file, targetFile, results);
            }
            latch.await();
        }//if
        FormatString.format(results, _GetTargetFiles.instanceDll.getAllFilesPath());
        return results;
    }//func getResult
}

