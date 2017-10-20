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
    private String diretory;  //�������ļ���
    private String targetFile;  //Ŀ���ļ�
    private List<String> results = new LinkedList<>(); //ͬ������

    public SearchFileConcurrecy(String diretory, String targetFile) {
        this.diretory = diretory;
        this.targetFile = targetFile;
    }

    /**
     * ��ָ���ļ��У���ȡ�����ļ�
     * ���ļ����б���
     * ������Ŀ¼�������߳̽�������
     * �����ļ���ֱ�ӽ���ƥ��
     */
    public List<String> getResults() throws InterruptedException {
        File files = new File(diretory);
        if (!files.exists() || !files.isDirectory()) {
            System.err.println("Invalid Path��");
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
                    latch.countDown(); //���ļ��� latchҲ�����
                }
                MyFileFilter.getInstance().filter(file, targetFile, results);
            }
            latch.await();
        }//if
        FormatString.format(results, _GetTargetFiles.instanceDll.getAllFilesPath());
        return results;
    }//func getResult
}

