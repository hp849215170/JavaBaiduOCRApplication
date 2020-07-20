package baiduocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiOcrStart {
    /*
    平
    private static final String APP_ID = "17789581";
    private static final String API_KEY = "oiRl3rdYD2yq2hKFYlonIu2I";
    private static final String SECRET_KEY = "jgbt0TA3hRXAWGAbE1nR2yR2DtrwbLvg";*/
    //通
    private static final String APP_ID = "21477643";
    private static final String API_KEY = "4WIkGcRplBAR0MKrWAeVtiKQ";
    private static final String SECRET_KEY = "F0UltX2A5kyYsqK1G6dP0Sa3tXzR6hRo";
    private static AipOcr client;
    private static HashMap<String, String> options;

    /*
     * 设置调用文字识别接口参数
     * */
    public static void setRequestParams() {
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "false");
        options.put("detect_language", "false");
        options.put("probability", "false");
    }

    /*
     * 单张图片识别
     * 返回识别之后的json结果
     * */
    public static String startOcr(String picUrl) {
        setRequestParams();
        /*
         * 通用文字识别高精度接口
         * */
        JSONObject json = client.basicAccurateGeneral(picUrl, options);
        String jsonStr = json.toString(2);
        System.out.println(jsonStr);
        return jsonStr;
    }

    /*
     * 两张图片同时识别
     * 返回识别之后的json结果集合
     * */
    public static List<String> startOcrMultiple(List<File> picUrlList) throws InterruptedException {
        setRequestParams();
        /*
         * 通用文字识别高精度接口
         * */
        JSONObject json = null;
        List<String> jsonList = new ArrayList<>();
        for (int i = 0; i < picUrlList.size(); i++) {
            json = client.basicAccurateGeneral(picUrlList.get(i).getPath(), options);
            //延迟100ms在调用接口
            Thread.sleep(100);
            jsonList.add(json.toString(2));
        }
        return jsonList;
    }
}
