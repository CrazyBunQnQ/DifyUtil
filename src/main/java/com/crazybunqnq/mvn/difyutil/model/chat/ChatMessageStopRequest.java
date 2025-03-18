package com.crazybunqnq.mvn.difyutil.model.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对话消息停止请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageStopRequest {
    
    /**
     * 用户标识，用于定义终端用户的身份
     * 必须和发送消息接口传入的user保持一致
     */
    private String user;
}