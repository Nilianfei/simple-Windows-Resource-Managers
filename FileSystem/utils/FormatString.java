package FileSystem.utils;

import java.util.List;


/**
 * Created by windy on 2017/8/11.
 */
public class FormatString {
    public static void format(List<String> resultList, String str){
        if (str == null){
            resultList.add("客官，这里没有您想要的菜");
        }else {
            String[] result = str.split("\\n");
            for (String subRes : result)
                resultList.add(subRes);
        }
    }
}
