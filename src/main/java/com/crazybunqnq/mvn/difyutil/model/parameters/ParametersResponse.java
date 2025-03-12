package com.crazybunqnq.mvn.difyutil.model.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 应用参数响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametersResponse {
    
    /**
     * 用户输入表单配置
     */
    @JsonProperty("user_input_form")
    private List<InputControl> userInputForm;
    
    /**
     * 输入控件基类
     */
    @Data
    @NoArgsConstructor
    public static class InputControl {
        /**
         * 控件类型
         */
        private String type;
        
        /**
         * 控件展示标签名
         */
        private String label;
        
        /**
         * 控件ID
         */
        private String variable;
        
        /**
         * 是否必填
         */
        private Boolean required;
    }
    
    /**
     * 文本输入控件
     */
    @Data
    public static class TextInput extends InputControl {
        /**
         * 默认值
         */
        private String default_value;
        
        public TextInput() {
            super.setType("text-input");
        }
    }
    
    /**
     * 段落文本输入控件
     */
    @Data
    public static class Paragraph extends InputControl {
        /**
         * 默认值
         */
        private String default_value;
        
        public Paragraph() {
            super.setType("paragraph");
        }
    }
    
    /**
     * 下拉控件
     */
    @Data
    public static class Select extends InputControl {
        /**
         * 选项列表
         */
        private List<Option> options;
        
        /**
         * 默认值
         */
        private String default_value;
        
        public Select() {
            super.setType("select");
        }
        
        /**
         * 下拉选项
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Option {
            /**
             * 选项标签
             */
            private String label;
            
            /**
             * 选项值
             */
            private String value;
        }
    }
}