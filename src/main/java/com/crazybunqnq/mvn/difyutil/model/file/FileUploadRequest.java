package com.crazybunqnq.mvn.difyutil.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * 文件上传请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {
    
    /**
     * 要上传的文件
     */
    private File file;
    
    /**
     * 用户标识
     */
    private String user;
    
    /**
     * 文件类型
     * 可选值：
     * - document: TXT, MD, MARKDOWN, PDF, HTML, XLSX, XLS, DOCX, CSV, EML, MSG, PPTX, PPT, XML, EPUB
     * - image: JPG, JPEG, PNG, GIF, WEBP, SVG
     * - audio: MP3, M4A, WAV, WEBM, AMR
     * - video: MP4, MOV, MPEG, MPGA
     * - custom: 其他文件类型
     */
    private String type;
}