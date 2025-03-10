package org.crazybun.difyutil.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 停止Workflow任务响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowStopResponse {
    
    /**
     * 结果，固定返回"success"
     */
    private String result;
}