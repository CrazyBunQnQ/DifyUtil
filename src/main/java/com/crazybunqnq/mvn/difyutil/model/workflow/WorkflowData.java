package com.crazybunqnq.mvn.difyutil.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Workflow执行数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowData {
    
    /**
     * 执行ID
     */
    private String id;
    
    /**
     * 关联的Workflow ID
     */
    private String workflow_id;
    
    /**
     * 执行状态
     * running / succeeded / failed / stopped
     */
    private String status;
    
    /**
     * 任务输出内容
     */
    private Map<String, Object> outputs;
    
    /**
     * 错误信息（可选）
     */
    private String error;
    
    /**
     * 耗时（秒）
     */
    private Double elapsed_time;
    
    /**
     * 消耗的token数量
     */
    private Integer total_tokens;
    
    /**
     * 执行步骤长度
     */
    private Integer total_steps;
    
    /**
     * 开始时间
     */
    private Long created_at;
    
    /**
     * 结束时间
     */
    private Long finished_at;
}