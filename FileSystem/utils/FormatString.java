package FileSystem.utils;

import java.util.List;


/**
 * Created by windy on 2017/8/11.
 */
public class FormatString {
    public static void format(List<String> resultList, String str){
        if (str == null){
            resultList.add("�͹٣�����û������Ҫ�Ĳ�");
        }else {
            String[] result = str.split("\\n");
            for (String subRes : result)
                resultList.add(subRes);
        }
    }
}
