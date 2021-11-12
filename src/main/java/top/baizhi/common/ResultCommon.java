package top.baizhi.common;


public class ResultCommon {
    private Object data;
    private String message;
    private String status;

    private ResultCommon(){}

    //成功調用
    public static ResultCommon ok(){
        ResultCommon r = new ResultCommon();
        r.status="100";
        return r;
    }

    public static ResultCommon error(){
        ResultCommon r = new ResultCommon();
        r.status="104";
        return r;
    }


    public Object getData() {
        return data;
    }

    public ResultCommon setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultCommon setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ResultCommon setStatus(String status) {
        this.status = status;
        return this;
    }
}
