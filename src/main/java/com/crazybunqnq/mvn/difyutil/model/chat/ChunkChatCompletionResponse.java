package com.crazybunqnq.mvn.difyutil.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对话消息流式响应（流式模式）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChunkChatCompletionResponse {
    
    /**
     * 事件类型
     * - message: LLM返回文本块事件
     * - agent_message: Agent模式下返回文本块事件
     * - agent_thought: Agent模式下思考步骤相关内容
     * - message_file: 文件事件
     * - message_end: 消息结束事件
     * - tts_message: TTS音频流事件
     * - tts_message_end: TTS音频流结束事件
     * - message_replace: 消息内容替换事件
     * - error: 错误事件
     * - ping: 保持连接存活的ping事件
     */
    private String event;
    
    /**
     * 任务ID，用于请求跟踪和停止响应
     */
    private String task_id;
    
    /**
     * 消息唯一ID
     */
    private String message_id;
    
    /**
     * 会话ID
     */
    private String conversation_id;
    
    /**
     * LLM返回文本块内容
     */
    private String answer;
    
    /**
     * 创建时间戳
     */
    private Long created_at;
    
    /**
     * 元数据（仅在message_end事件中返回）
     */
    private ChatCompletionResponse.Metadata metadata;
    
    /**
     * HTTP状态码（仅在error事件中返回）
     */
    private Integer status;
    
    /**
     * 错误码（仅在error事件中返回）
     */
    private String code;
    
    /**
     * 错误消息（仅在error事件中返回）
     */
    private String message;
    
    /**
     * Agent思考ID（仅在agent_thought事件中返回）
     */
    private String id;
    
    /**
     * Agent思考在消息中的位置（仅在agent_thought事件中返回）
     */
    private Integer position;
    
    /**
     * Agent的思考内容（仅在agent_thought事件中返回）
     */
    private String thought;
    
    /**
     * 工具调用的返回结果（仅在agent_thought事件中返回）
     */
    private String observation;
    
    /**
     * 使用的工具列表，以;分割多个工具（仅在agent_thought事件中返回）
     */
    private String tool;
    
    /**
     * 工具的输入，JSON格式的字符串（仅在agent_thought事件中返回）
     */
    private String tool_input;
    
    /**
     * 当前agent_thought关联的文件ID（仅在agent_thought事件中返回）
     */
    private List<String> message_files;
    
    /**
     * 文件唯一ID（仅在message_file事件中返回）
     */
    private String file_id;
    
    /**
     * 文件类型（仅在message_file事件中返回）
     */
    private String type;
    
    /**
     * 文件归属（仅在message_file事件中返回）
     */
    private String belongs_to;
    
    /**
     * 文件访问地址（仅在message_file事件中返回）
     */
    private String url;
    
    /**
     * 语音合成之后的音频块（仅在tts_message事件中返回）
     */
    private String audio;
}