package ${packageName}.common;

import java.util.ArrayList;

/**
* @Description: REST调用返回类
**/
public class InvokeResult<T> {
    private int code;
    private String message;
    private T data;

    public static InvokeResult success(){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setMessage("成功!");
        invokeResult.setCode(200);
        invokeResult.setData(new ArrayList<>());
        return invokeResult;
    }

    public static InvokeResult success(Object data){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setData(data);
        invokeResult.setCode(200);
        invokeResult.setMessage("成功!");
        return invokeResult;
    }

    public static InvokeResult failure(String message){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setCode(400);
        invokeResult.setMessage(message);
        return invokeResult;
    }

    public static InvokeResult failure(int code, String message){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setCode(code);
        invokeResult.setMessage(message);
        return invokeResult;
    }

    public static InvokeResult error(){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setCode(500);
        invokeResult.setMessage("服务内部错误!");
        return invokeResult;
    }

    public static InvokeResult passNoData(){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setCode(201);
        invokeResult.setMessage("查询数据为空!");
        return invokeResult;
    }
    public static InvokeResult unauthorized(){
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setCode(StatusCode.UNAUTHORIZED.code());
        invokeResult.setMessage("授权失败!");
        return invokeResult;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus(){
        return this.code > 1000?400:this.code;
    }
}