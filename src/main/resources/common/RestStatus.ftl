package ${packageName}.common;

/**
* @Description: TODO

**/
public interface RestStatus {
    /**
    * 状态码
    * @return
    */
    int code();

    /**
    * 状态描述
    * @return
    */
    String message();

    /**
    * 设置异常描述
    * @param msg
    */
    void setMessage(String msg);
}