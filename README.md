# DifyUtil - Dify API Java 工具库

[![Java Version](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.14-brightgreen.svg)](https://spring.io/projects/spring-boot)

## 项目简介

DifyUtil 是一个用于与 [Dify](https://dify.ai) API 进行交互的 Java 工具库。Dify 是一个强大的 AI 应用开发平台，本工具库封装了 Dify Workflow API 的调用，使 Java 开发者能够轻松地在自己的应用中集成 Dify 的功能。

## 功能特性

- 完整支持 Dify Workflow API
- 支持文件上传和处理
- 获取应用基本信息和参数
- 执行 Workflow 并获取结果
- 支持 Workflow 任务的停止和日志查询
- 与 Spring Boot 无缝集成
- 支持通过配置文件或环境变量进行配置

## 安装说明

### Maven 依赖

将以下依赖添加到您的 `pom.xml` 文件中：

```xml
<dependency>
    <groupId>com.crazybunqnq.mvn</groupId>
    <artifactId>dify-util</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 配置

在 `application.yml` 或 `application.properties` 中添加以下配置：

```yaml
# Dify API配置
dify:
  # API基础URL，可通过环境变量DIFY_API_BASE_URL覆盖
  api-base-url: ${DIFY_API_BASE_URL:https://api.dify.ai/v1}
  # API密钥，可通过环境变量DIFY_API_KEY覆盖
  api-key: ${DIFY_API_KEY:your-api-key-here}
```

您可以通过以下两种方式设置 API 密钥：
1. 直接在配置文件中修改 `your-api-key-here`
2. 设置环境变量 `DIFY_API_KEY` 和 `DIFY_API_BASE_URL`

## 使用示例

### 注入 DifyClient

```java
@Service
public class YourService {
    
    @Autowired
    private DifyClient difyClient;
    
    // 使用 difyClient 的方法...
}
```

### 执行 Workflow

```java
// 创建 Workflow 请求
WorkflowRequest request = WorkflowRequest.builder()
    .inputs(Map.of("question", "What is AI?"))
    .response_mode("blocking") // 或 "streaming"
    .user("user-123")
    .build();

// 执行 Workflow
try {
    WorkflowResponse response = difyClient.runWorkflow(request);
    System.out.println("Workflow 执行结果: " + response.getAnswer());
} catch (DifyApiException e) {
    System.err.println("执行 Workflow 失败: " + e.getMessage());
}
```

### 上传文件

```java
// 创建文件上传请求
FileUploadRequest request = FileUploadRequest.builder()
    .file(new File("/path/to/your/file.pdf"))
    .user("user-123")
    .type("document") // 文件类型: document, image, audio, video, custom
    .build();

// 上传文件
try {
    FileUploadResponse response = difyClient.uploadFile(request);
    System.out.println("文件上传成功，ID: " + response.getId());
} catch (DifyApiException e) {
    System.err.println("上传文件失败: " + e.getMessage());
}
```

### 获取应用信息

```java
try {
    AppInfoResponse appInfo = difyClient.getAppInfo();
    System.out.println("应用名称: " + appInfo.getName());
    System.out.println("应用描述: " + appInfo.getDescription());
} catch (DifyApiException e) {
    System.err.println("获取应用信息失败: " + e.getMessage());
}
```

### 获取应用参数

```java
try {
    ParametersResponse parameters = difyClient.getParameters();
    List<ParametersResponse.InputControl> inputs = parameters.getUserInputForm();
    for (ParametersResponse.InputControl input : inputs) {
        System.out.println("参数: " + input.getLabel() + ", 类型: " + input.getType());
    }
} catch (DifyApiException e) {
    System.err.println("获取应用参数失败: " + e.getMessage());
}
```

## 支持的 API

- `runWorkflow(WorkflowRequest request)` - 执行 Workflow
- `stopWorkflowTask(String taskId, WorkflowStopRequest request)` - 停止 Workflow 任务
- `uploadFile(FileUploadRequest request)` - 上传文件
- `getWorkflowLogs(Map<String, String> queryParams)` - 获取 Workflow 日志
- `getAppInfo()` - 获取应用基本信息
- `getParameters()` - 获取应用参数

## 错误处理

所有 API 调用都可能抛出 `DifyApiException` 异常，您应该捕获并适当处理这些异常。

```java
try {
    // 调用 DifyClient 的方法
} catch (DifyApiException e) {
    // 处理异常
    System.err.println("API 调用失败: " + e.getMessage());
    e.printStackTrace();
}
```

## 许可证

[MIT License](LICENSE)

## 贡献

欢迎提交 Issue 和 Pull Request！