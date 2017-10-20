package FileSystem.plusfun;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by windy on 2017/8/22.
 */
public class MyLRU {
    private final int MAX_SIZE = 20;
    private LinkedList<String> mPathList;
    private String fileName;

    public MyLRU(String fileName) throws IOException{
        this.mPathList = new LinkedList<>();
        this.fileName = fileName;

        File file = new File(fileName);
        if (file.exists()){
            readExistPaths(file);
        }
        else {
            file.createNewFile();
        }
    }

    public void add(String path){
        if(mPathList.contains(path)){
            mPathList.remove(path);
        }
        else if (mPathList.size() >= MAX_SIZE){
            mPathList.removeLast();
        }
        mPathList.addFirst(path);
    }
    public boolean writePath() throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)));
        for (String path : mPathList){
            bufferedWriter.write(path);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }

    private void readExistPaths(File file) throws IOException{
        String path;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        while ((path = bufferedReader.readLine()) != null){
              add(path);
        }
        bufferedReader.close();
    }
}
