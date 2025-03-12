package com.crazybunqnq.mvn.difyutil.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 停止Workflow任务请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowStopRequest {
    
    /**
     * 用户标识，必须和发送消息接口传入user保持一致
     */
    private String user;
}