package com.crazybunqnq.mvn.difyutil.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Workflow执行响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowResponse {
    
    /**
     * 任务ID，可用于停止任务
     */
    private String task_id;
    
    /**
     * 会话ID
     */
    private String conversation_id;
    
    /**
     * 消息ID
     */
    private String message_id;
    
    /**
     * 执行结果，仅在阻塞模式下返回
     */
    private String answer;
    
    /**
     * 创建时间戳
     */
    private Long created_at;
    
    /**
     * 是否需要反馈
     */
    private Boolean need_feedback;
    
    /**
     * 文本转语音消息，仅当启用TTS时返回
     */
    private String tts;
}