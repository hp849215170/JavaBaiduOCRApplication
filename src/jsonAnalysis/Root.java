package jsonAnalysis;

import java.util.List;

public class Root {

    private String log_id;
    private String words_result_num;
    private List<Words_result> words_result;
    private String error_msg;
    private int error_code;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }


    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<Words_result> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<Words_result> words_result) {
        this.words_result = words_result;
    }
}
