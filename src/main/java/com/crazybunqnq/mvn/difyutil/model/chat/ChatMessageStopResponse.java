package com.crazybunqnq.mvn.difyutil.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对话消息停止响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageStopResponse {
    
    /**
     * 结果，固定返回success
     */
    private String result;
}