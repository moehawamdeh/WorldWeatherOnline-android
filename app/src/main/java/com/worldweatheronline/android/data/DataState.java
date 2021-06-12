package com.worldweatheronline.android.data;

/**
 * @param <T>
 *           Data Class
 */
public class DataState<T> {
    private T mData;
    private Status mStatus;
    private String mMessage;


    public T getData() {
        return mData;
    }

    public Status getStatus() {
        return mStatus;
    }

    public String getMsg() {
        return mMessage;
    }

    public DataState<T> success(T data){
        mData=data;
        mStatus=Status.SUCCESS;
        mMessage=null;
        return this;
    }
    public DataState<T> error(String msg){
        mStatus=Status.ERROR;
        mMessage=msg;
        mData=null;
        return this;
    }

    public DataState<T> loading(){
        mStatus=Status.LOADING;
        mMessage=null;
        mData=null;
        return this;
    }

    enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
