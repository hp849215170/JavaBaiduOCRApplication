package sample;


import baiduocr.ApiOcrStart;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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

public class Main extends Application {


    private String path = System.getProperty("user.home");
    private TextArea textArea;
    private FileChooser fileChooser = new FileChooser();
    private Analysis analysis = new Analysis();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
         * 单张图片识别fxml
         * */
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        /*
         * 多张图片识别fxml
         * */
        Parent root = FXMLLoader.load(getClass().getResource("TestUI.fxml"));

        primaryStage.setTitle("图文识别");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //单张图片识别
        //setResultText(root, primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }

    /*
     * 界面事件操作
     * */
    public void setResultText(Parent parent, Stage stage) {

        Button upload = (Button) parent.lookup("#upload");
        Button start = (Button) parent.lookup("#start");
        ImageView image = (ImageView) parent.lookup("#image");

        textArea = (TextArea) parent.lookup("#resultText");

        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String dirPath = path.substring(0, path.lastIndexOf("\\"));
                fileChooser.setTitle("选择文件");
                if (FileIOUtil.checkDir(dirPath) == 1) {
                    fileChooser.setInitialDirectory(new File(dirPath));
                } else {
                    DialogUtil.showDialog(null, "文件夹不存在或已被删除，即将打开默认目录");
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                }
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    path = file.getPath();
                    System.out.println("图片路径：" + path);
                    Image image1 = new Image("file:///" + path);
                    image.setImage(image1);
                }
            }
        });

        /*
         * 单张图片开始识别
         * */
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startThread();
            }
        });


    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = ApiOcrStart.startOcr(path);
                    String result = analysis.jsonAnalysis(jsonStr);
                    System.out.println("解析结果—>"+result);
                    textArea.clear();
                    textArea.setText(result.trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
