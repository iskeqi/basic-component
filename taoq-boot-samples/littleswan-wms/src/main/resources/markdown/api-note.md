# API 文档使用说明

## 全局说明

### 调用说明
- 所有 HTTP 请求的`Content-Type`必须为`application/json;charset=UTF-8`
- 系统所有响应消息体均会包含`status`、`message`、`data` 3 个一级字段
- 无论业务上的请求成功还是失败，系统所有接口 HTTP 响应状态码均为 200，通过`status`字段区分请求是否成功，响应成功消息体，示例如下
    ```json
    {
      "status": "00000",
      "message": "success",
      "data": {
      }
    }
    ```

### 全局响应状态码

| 状态码(status) | 响应内容(message) | 说明 |
| --- | --- | --- |
| 00000 | success | 请求成功 |
| A0001 | client error | 客户端错误 |
| A0002 | token invalid or error | token失效或错误 |
| A0003 | param error | 参数错误 |
| B0001 | system is busy, please try again later | 系统繁忙，请稍后重试 |
| C0001 | error in third party service | 第三方服务错误 |