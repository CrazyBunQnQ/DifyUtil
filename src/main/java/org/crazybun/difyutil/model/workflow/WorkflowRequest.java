package org.crazybun.difyutil.model.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Workflow执行请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowRequest {
    
    /**
     * 输入参数，包含App定义的各变量值
     */
    private Map<String, Object> inputs;
    
    /**
     * 响应模式
     * - streaming: 流式模式，基于SSE实现类似打字机输出方式的流式返回
     * - blocking: 阻塞模式，等待执行完毕后返回结果
     */
    private String response_mode;
    
    /**
     * 用户标识，用于定义终端用户的身份
     */
    private String user;
    
    /**
     * 文件列表，适用于传入文件结合文本理解并回答问题
     */
    private List<FileInput> files;
    
    /**
     * 文件输入
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileInput {
        /**
         * 文件类型
         * - document: TXT, MD, MARKDOWN, PDF, HTML, XLSX, XLS, DOCX, CSV, EML, MSG, PPTX, PPT, XML, EPUB
         * - image: JPG, JPEG, PNG, GIF, WEBP, SVG
         * - audio: MP3, M4A, WAV, WEBM, AMR
         * - video: MP4, MOV, MPEG, MPGA
         * - custom: 其他文件类型
         */
        private String type;
        
        /**
         * 传递方式
         * - remote_url: 图片地址
         * - local_file: 上传文件
         */
        private String transfer_method;
        
        /**
         * 文件URL，当transfer_method为remote_url时使用
         */
        private String url;
        
        /**
         * 上传文件ID，当transfer_method为local_file时使用
         */
        private String upload_file_id;
    }
}