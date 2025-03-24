package com.crazybunqnq.mvn.difyutil.model.workflow.logs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Workflow日志响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowLogsResponse {
    
    /**
     * 当前页码
     */
    private Integer page;
    
    /**
     * 每页条数
     */
    private Integer limit;
    
    /**
     * 总条数
     */
    private Integer total;
    
    /**
     * 是否还有更多数据
     */
    private Boolean has_more;
    
    /**
     * 日志数据列表
     */
    private List<WorkflowLogItem> data;
    
    /**
     * Workflow日志项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkflowLogItem {
        
        /**
         * 日志ID
         */
        private String id;
        
        /**
         * Workflow执行日志
         */
        private WorkflowRun workflow_run;
        
        /**
         * 来源
         */
        private String created_from;
        
        /**
         * 角色
         */
        private String created_by_role;
        
        /**
         * 帐号（可选）
         */
        private String created_by_account;
        
        /**
         * 用户信息
         */
        private EndUser created_by_end_user;
        
        /**
         * 创建时间
         */
        private Long created_at;
    }
    
    /**
     * Workflow执行信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkflowRun {
        
        /**
         * 执行ID
         */
        private String id;
        
        /**
         * 版本
         */
        private String version;
        
        /**
         * 执行状态
         * running / succeeded / failed / stopped
         */
        private String status;
        
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
    
    /**
     * 终端用户信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EndUser {
        
        /**
         * 用户ID
         */
        private String id;
        
        /**
         * 用户类型
         */
        private String type;
        
        /**
         * 是否匿名
         */
        private Boolean is_anonymous;
        
        /**
         * 会话ID
         */
        private String session_id;
    }
}