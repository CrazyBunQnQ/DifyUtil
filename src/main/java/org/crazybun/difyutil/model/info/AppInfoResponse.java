package org.crazybun.difyutil.model.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 应用基本信息响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoResponse {
    
    /**
     * 应用名称
     */
    private String name;
    
    /**
     * 应用描述
     */
    private String description;
    
    /**
     * 应用标签
     */
    private List<String> tags;
}