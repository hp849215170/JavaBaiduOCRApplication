package utils;

import java.io.*;

public class FileIOUtil {


    /*
     * 判断是不是文件夹
     * 1是，0不是
     * */
    public static int checkDir(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return 1;
        }
        return 0;
    }

    /*
     * 判断文件是否存在，不存在新建文件
     * 1表示存在
     * 0表示不存在
     * */
    public static int fileExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return 0;
        }
        return 1;
    }

    /*
     * 读取文件
     * */
    public static String readFile(String filePath) {

        byte[] b = null;
        try {
            fileExist(filePath);
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("没有找到读取的文件！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("文本内容："+ new String(b));
        return new String(b);

    }

    /*
     * 保存文件
     * */
    public static void writeFile(String path, String str) {
        try {
            fileExist(path);
            FileOutputStream fileOutputStream = new FileOutputStream(path);

            for (int i = 0; i < str.length(); i++) {
                int b = str.charAt(i);
                fileOutputStream.write(b);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到要写的文件！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
