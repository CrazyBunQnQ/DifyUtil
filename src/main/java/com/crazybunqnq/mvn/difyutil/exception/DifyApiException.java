package com.crazybunqnq.mvn.difyutil.exception;

/**
 * Dify API异常类
 * 用于封装与Dify API交互过程中发生的异常
 */
public class DifyApiException extends Exception {

    /**
     * 创建一个新的DifyApiException实例
     * 
     * @param message 异常消息
     */
    public DifyApiException(String message) {
        super(message);
    }

    /**
     * 创建一个新的DifyApiException实例
     * 
     * @param message 异常消息
     * @param cause 原始异常
     */
    public DifyApiException(String message, Throwable cause) {
        super(message, cause);
    }
}