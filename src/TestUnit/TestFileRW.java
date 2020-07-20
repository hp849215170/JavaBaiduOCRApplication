package TestUnit;

import org.junit.Test;
import utils.DialogUtil;
import utils.FileIOUtil;

import java.io.IOException;

public class TestFileRW {
    @Test
    public void testWriteFile() throws IOException {
        if (FileIOUtil.fileExist("/test")==1){
            FileIOUtil.writeFile("/test","This is test!");
        }else {
            DialogUtil.showDialog(null,"The file not exist");
        }
    }
}
