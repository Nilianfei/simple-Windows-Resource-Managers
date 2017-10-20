package FileSystem.utils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by windy on 2017/8/11.
 * 每次主进程搜寻文件时，只有一个文件名对应的唯一文件过滤器
 * 创建单例模式，避免MyFileFilter实例被重复创建销毁
 */
public class MyFileFilter {
    private  volatile static MyFileFilter instance;
    private Pattern pattern;

    private MyFileFilter() {}

    public static MyFileFilter getInstance(){
        if (instance == null){
            synchronized (MyFileFilter.class) {
                if (instance == null)
                    instance = new MyFileFilter();
            }
        }
        return instance;
    }

    public void filter(File srcFile, String targetFile, List<String> results){
        this.pattern = Pattern.compile(".*(" + targetFile + ").*", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(srcFile.getName()).find()) {
            //System.out.println("MyFileFilter - \n" + srcFile.getAbsolutePath());
           results.add(srcFile.getAbsolutePath());
        }
    }
}
