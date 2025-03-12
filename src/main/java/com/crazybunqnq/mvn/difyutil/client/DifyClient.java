package com.crazybunqnq.mvn.difyutil.client;

import com.crazybunqnq.mvn.difyutil.config.DifyProperties;
import com.crazybunqnq.mvn.difyutil.exception.DifyApiException;
import com.crazybunqnq.mvn.difyutil.model.file.FileUploadRequest;
import com.crazybunqnq.mvn.difyutil.model.file.FileUploadResponse;
import com.crazybunqnq.mvn.difyutil.model.info.AppInfoResponse;
import com.crazybunqnq.mvn.difyutil.model.parameters.ParametersResponse;
import com.crazybunqnq.mvn.difyutil.model.workflow.WorkflowRequest;
import com.crazybunqnq.mvn.difyutil.model.workflow.WorkflowResponse;
import com.crazybunqnq.mvn.difyutil.model.workflow.WorkflowStopRequest;
import com.crazybunqnq.mvn.difyutil.model.workflow.WorkflowStopResponse;
import com.crazybunqnq.mvn.difyutil.model.workflow.logs.WorkflowLogsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Dify API客户端
 * 用于与Dify Workflow API进行交互
 */
@Slf4j
@Component
public class DifyClient {

    private final DifyProperties properties;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;

    public DifyClient(DifyProperties properties) {
        this.properties = properties;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * 执行Workflow
     *
     * @param request Workflow请求参数
     * @return Workflow执行结果
     * @throws DifyApiException 如果API调用失败
     */
    public WorkflowResponse runWorkflow(WorkflowRequest request) throws DifyApiException {
        String url = properties.getApiBaseUrl() + "/workflows/run";
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new DifyApiException("序列化请求失败", e);
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer " + properties.getApiKey());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode != 200) {
                log.error("执行Workflow失败: {}, 状态码: {}", responseBody, statusCode);
                throw new DifyApiException("执行Workflow失败，状态码: " + statusCode + ", 响应: " + responseBody);
            }

            return objectMapper.readValue(responseBody, WorkflowResponse.class);
        } catch (IOException e) {
            throw new DifyApiException("执行Workflow请求失败", e);
        }
    }

    /**
     * 停止Workflow任务
     *
     * @param taskId  任务ID
     * @param request 停止请求参数
     * @return 停止结果
     * @throws DifyApiException 如果API调用失败
     */
    public WorkflowStopResponse stopWorkflowTask(String taskId, WorkflowStopRequest request) throws DifyApiException {
        String url = properties.getApiBaseUrl() + "/workflows/tasks/" + taskId + "/stop";
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new DifyApiException("序列化请求失败", e);
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer " + properties.getApiKey());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode != 200) {
                log.error("停止Workflow任务失败: {}, 状态码: {}", responseBody, statusCode);
                throw new DifyApiException("停止Workflow任务失败，状态码: " + statusCode + ", 响应: " + responseBody);
            }

            return objectMapper.readValue(responseBody, WorkflowStopResponse.class);
        } catch (IOException e) {
            throw new DifyApiException("停止Workflow任务请求失败", e);
        }
    }

    /**
     * 上传文件
     *
     * @param request 文件上传请求
     * @return 文件上传响应
     * @throws DifyApiException 如果API调用失败
     */
    public FileUploadResponse uploadFile(FileUploadRequest request) throws DifyApiException {
        String url = properties.getApiBaseUrl() + "/files/upload";

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer " + properties.getApiKey());

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", request.getFile(), ContentType.DEFAULT_BINARY, request.getFile().getName());
        builder.addTextBody("user", request.getUser());
        if (request.getType() != null) {
            builder.addTextBody("type", request.getType());
        }

        httpPost.setEntity(builder.build());

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode != 201) { // 文件上传成功返回201
                log.error("上传文件失败: {}, 状态码: {}", responseBody, statusCode);
                throw new DifyApiException("上传文件失败，状态码: " + statusCode + ", 响应: " + responseBody);
            }

            return objectMapper.readValue(responseBody, FileUploadResponse.class);
        } catch (IOException e) {
            throw new DifyApiException("上传文件请求失败", e);
        }
    }

    /**
     * 获取Workflow日志
     *
     * @param queryParams 查询参数，可包含keyword、status、page、limit
     * @return Workflow日志响应
     * @throws DifyApiException 如果API调用失败
     */
    public WorkflowLogsResponse getWorkflowLogs(Map<String, String> queryParams) throws DifyApiException {
        try {
            URIBuilder uriBuilder = new URIBuilder(properties.getApiBaseUrl() + "/workflows/logs");
            if (queryParams != null) {
                for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();

            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Authorization", "Bearer " + properties.getApiKey());

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                if (statusCode != 200) {
                    log.error("获取Workflow日志失败: {}, 状态码: {}", responseBody, statusCode);
                    throw new DifyApiException("获取Workflow日志失败，状态码: " + statusCode + ", 响应: " + responseBody);
                }

                return objectMapper.readValue(responseBody, WorkflowLogsResponse.class);
            }
        } catch (URISyntaxException | IOException e) {
            throw new DifyApiException("获取Workflow日志请求失败", e);
        }
    }

    /**
     * 获取应用基本信息
     *
     * @return 应用基本信息响应
     * @throws DifyApiException 如果API调用失败
     */
    public AppInfoResponse getAppInfo() throws DifyApiException {
        String url = properties.getApiBaseUrl() + "/info";

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer " + properties.getApiKey());

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode != 200) {
                log.error("获取应用基本信息失败: {}, 状态码: {}", responseBody, statusCode);
                throw new DifyApiException("获取应用基本信息失败，状态码: " + statusCode + ", 响应: " + responseBody);
            }

            return objectMapper.readValue(responseBody, AppInfoResponse.class);
        } catch (IOException e) {
            throw new DifyApiException("获取应用基本信息请求失败", e);
        }
    }

    /**
     * 获取应用参数
     *
     * @return 应用参数响应
     * @throws DifyApiException 如果API调用失败
     */
    public ParametersResponse getParameters() throws DifyApiException {
        String url = properties.getApiBaseUrl() + "/parameters";

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer " + properties.getApiKey());

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode != 200) {
                log.error("获取应用参数失败: {}, 状态码: {}", responseBody, statusCode);
                throw new DifyApiException("获取应用参数失败，状态码: " + statusCode + ", 响应: " + responseBody);
            }

            return objectMapper.readValue(responseBody, ParametersResponse.class);
        } catch (IOException e) {
            throw new DifyApiException("获取应用参数请求失败", e);
        }
    }
}