package com.crazybunqnq.mvn.difyutil.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 对话消息完整响应（阻塞模式）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponse {
    
    /**
     * 消息唯一ID
     */
    private String message_id;
    
    /**
     * 会话ID
     */
    private String conversation_id;
    
    /**
     * App模式，固定为chat
     */
    private String mode;
    
    /**
     * 完整回复内容
     */
    private String answer;
    
    /**
     * 元数据
     */
    private Metadata metadata;
    
    /**
     * 消息创建时间戳
     */
    private Long created_at;
    
    /**
     * 元数据
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        
        /**
         * 模型用量信息
         */
        private Usage usage;
        
        /**
         * 引用和归属分段列表
         */
        private List<RetrieverResource> retriever_resources;
    }
    
    /**
     * 模型用量信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        
        /**
         * 提示词token数量
         */
        private Integer prompt_tokens;
        
        /**
         * 提示词单价
         */
        private String prompt_unit_price;
        
        /**
         * 提示词价格单位
         */
        private String prompt_price_unit;
        
        /**
         * 提示词价格
         */
        private String prompt_price;
        
        /**
         * 补全token数量
         */
        private Integer completion_tokens;
        
        /**
         * 补全单价
         */
        private String completion_unit_price;
        
        /**
         * 补全价格单位
         */
        private String completion_price_unit;
        
        /**
         * 补全价格
         */
        private String completion_price;
        
        /**
         * 总token数量
         */
        private Integer total_tokens;
        
        /**
         * 总价格
         */
        private String total_price;
        
        /**
         * 货币单位
         */
        private String currency;
        
        /**
         * 延迟时间
         */
        private Double latency;
    }
    
    /**
     * 引用和归属分段
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieverResource {
        
        /**
         * 位置
         */
        private Integer position;
        
        /**
         * 数据集ID
         */
        private String dataset_id;
        
        /**
         * 数据集名称
         */
        private String dataset_name;
        
        /**
         * 文档ID
         */
        private String document_id;
        
        /**
         * 文档名称
         */
        private String document_name;
        
        /**
         * 分段ID
         */
        private String segment_id;
        
        /**
         * 相关度分数
         */
        private Double score;
        
        /**
         * 内容
         */
        private String content;
    }
}