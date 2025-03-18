package com.crazybunqnq.mvn.difyutil.model.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 对话消息请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {
    
    /**
     * 用户输入/提问内容
     */
    private String query;
    
    /**
     * 允许传入App定义的各变量值
     * 包含多组键值对，每组的键对应一个特定变量，每组的值则是该变量的具体值
     */
    private Map<String, Object> inputs;
    
    /**
     * 响应模式
     * - streaming: 流式模式（推荐）
     * - blocking: 阻塞模式，等待执行完毕后返回结果
     */
    private String response_mode;
    
    /**
     * 用户标识，用于定义终端用户的身份，方便检索、统计
     * 由开发者定义规则，需保证用户标识在应用内唯一
     */
    private String user;
    
    /**
     * 会话ID，需要基于之前的聊天记录继续对话，必须传之前消息的conversation_id
     */
    private String conversation_id;
    
    /**
     * 上传的文件列表
     */
    private List<FileInfo> files;
    
    /**
     * 是否自动生成标题，默认true
     */
    private Boolean auto_generate_name;
    
    /**
     * 文件信息
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileInfo {
        
        /**
         * 文件类型，目前支持：image
         */
        private String type;
        
        /**
         * 传递方式
         * - remote_url: 图片地址
         * - local_file: 上传文件
         */
        private String transfer_method;
        
        /**
         * 图片地址（仅当传递方式为remote_url时）
         */
        private String url;
        
        /**
         * 上传文件ID（仅当传递方式为local_file时）
         */
        private String upload_file_id;
    }
}