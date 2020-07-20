package jsonAnalysis;

import com.google.gson.Gson;
import utils.DialogUtil;

import java.io.*;

public class Analysis {
    /*
     * 解析Json数据
     * 返回解析结果文本
     * */

    public String jsonAnalysis(String jsonString) throws IOException {

        Root root = new Gson().fromJson(jsonString, Root.class);

        String string = "";

        if (root.getWords_result() == null) {
            if (root.getError_code() == 17) {
                DialogUtil.showDialog(null,"超出每日调用次数！");
                return root.getError_msg();
            }
        }

        for (int i = 0; i < root.getWords_result().size(); i++) {
            string += root.getWords_result().get(i).getWords() + "\n";
        }
        return string;
    }

}
