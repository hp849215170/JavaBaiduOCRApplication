package sample;

import baiduocr.ApiOcrStart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jsonAnalysis.Analysis;
import utils.DialogUtil;
import utils.FileIOUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public ImageView iv1, iv2;
    @FXML
    public TextArea ta_1, ta_2;
    //选择多个文件集合
    private List<File> files = null;
    //当前用户所在目录
    private String path = System.getProperty("user.dir");

    private TextArea[] areas = new TextArea[2];
    private ImageView[] imageView = new ImageView[2];


    /*
     * 上传按钮
     * */
    @FXML
    public void onUploadClick(ActionEvent actionEvent) {

        String dirPath = path.substring(0, path.lastIndexOf("\\"));
        FileChooser fileChooser = new FileChooser();

        try {
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("图片", "*.png", "*.jpg"));
            if (FileIOUtil.checkDir(dirPath) == 1) {
                fileChooser.setInitialDirectory(new File(dirPath));
            } else {
                DialogUtil.showDialog(null, "文件夹不存在或已被删除，即将打开默认目录");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            }
            files = fileChooser.showOpenMultipleDialog(Stage.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (files == null){
            return;
        }
        int file_number = files.size();

        if (files != null && file_number <= 2) {

            for (int i = 0; i < file_number; i++) {
                System.out.println("已选文件：" + files.get(i).getPath());
                imageView[i].setImage(new Image("file:///" + files.get(i)));
            }
            path = files.get(0).getPath();
        } else {
            DialogUtil.showDialog(null, "不能超过两张图片！");
        }
    }

    /*
     * 开始识别按钮
     * */
    public void onStartOcr(ActionEvent actionEvent) {
        if (files != null) {
            startThread();
        } else {
            DialogUtil.showDialog(null, "请先选择图片！");
        }
    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Analysis analysis = new Analysis();
                List<String> stringList = null;
                try {
                    stringList = ApiOcrStart.startOcrMultiple(files);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < stringList.size(); i++) {
                    try {
                        areas[i].setText(analysis.jsonAnalysis(stringList.get(i)).replace(" ", ""));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
        areas[0] = ta_1;
        areas[1] = ta_2;
        imageView[0] = iv1;
        imageView[1] = iv2;
    }
}
