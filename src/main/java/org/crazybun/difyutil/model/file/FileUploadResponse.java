package org.crazybun.difyutil.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    
    /**
     * 文件ID
     */
    private String id;
    
    /**
     * 文件名称
     */
    private String name;
    
    /**
     * 文件大小（字节）
     */
    private Long size;
    
    /**
     * 文件扩展名
     */
    private String extension;
    
    /**
     * 文件MIME类型
     */
    private String mime_type;
    
    /**
     * 创建者ID
     */
    private Long created_by;
    
    /**
     * 创建时间戳
     */
    private Long created_at;
}