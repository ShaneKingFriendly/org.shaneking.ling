## 请求响应（Request and Response）报文格式定义

### Request

```json
{
    "cno": "【字符串】【必给】ChannelNo，渠道编号",
    "tkn": "【字符串】【按需】Token，令牌。针对一次一密场景，需要先请求获得令牌，再附加令牌进行请求。",
    "mvc": "【字符串】【按约。通常为msg字符串形式的校验码或enc字符串的校验码】Message Verification Code，消息校验码。根据约定的算法校验请求和响应数据完整性",
    "enc": "【字符串】【按约。为msg节点字符串形式的密文】Encoded，密文。根据约定的算法解密请求和加密响应数据",
    "msg【json】【按约。如果enc存在，以enc为准】Message，请求消息": {
      "rno": "【字符串】【可选。不给时，响应会自动生成】【全局永久唯一递增编号】RequestNo，请求编号。用于标识唯一请求，可防止重放。由org.shaneking.ling.zero.util.UUID0.cUl33()生成。样例：1612263653223_oGFvE5Hyndf0njoFhyK",
      "tno": "【字符串】【可选。不给时，响应会自动生成】TracingNo，追踪编号。用于调用方处理一笔请求时，多次调用我方场景，比如：一次支付，可能需要多次调银联",
      "asy": "【自然数】【可选】Asynchronous，异步等待秒数Seconds",
      "ano": "【字符串】【按需。如果asy>0且此值未给，则响应会自动生成】【全局永久唯一递增编号】AsynchronousNo，异步编号",
      "dsz": "【字符串】【可选】DateTimeSssZone，请求时间。格式：yyyy-MM-dd HH:mm:ss.SSSXXX",
      "uno": "【字符串】【按需。如不给，则等于cno】UserNo，用户编号（当前）",
      "lan": "【字符串】【可选】language，语言。例如：`zh-CN`。参考：<http://www.rfc-editor.org/rfc/bcp/bcp47.txt>",
      "jsn": "【json】【按需。暂无用，用于扩展】",
      "bdy【json】【必给】【该节点将用于请求级缓存】Request Body，请求业务数据": {
        "tno": "【字符串】【按需。如不给，则等于cno】TenantNo，租户编号",
        "uno": "【字符串】【按需。如不给，则等于parent.uno，表示parent.uno代理uno请求】UserNo，用户编号（权限）",
        "tbl": "【json】【按需。对于表格类（含page/sort/filter等）请求需传入，其他情况可不给】",
        "obj": "【json】【按需】请求业务对象",
        "jsn": "【json】【按需。暂无用，用于扩展】"
      }
    }
  }
```

### Response

```json
{
  "mvc": "【字符串】【按约。通常为msg字符串形式的校验码或enc字符串的校验码】Message Verification Code，消息校验码。根据约定的算法校验请求和响应数据完整性",
  "enc": "【字符串】【按约。为msg节点字符串形式的密文】Encoded，密文。根据约定的算法解密请求和加密响应数据",
  "msg【json】【按约。如果enc存在，以enc为准】Message，响应消息": {
    "rno": "【字符串】【必给】如请求未给，则生成全局永久唯一递增编号。否则原样返回",
    "ano": "【字符串】【按需】如果asy>0且此值请求未给，则生成全局永久唯一递增编号。否则原样返回",
    "dsz": "【字符串】【必给】DateTimeSssZone，响应时间。格式：yyyy-MM-dd HH:mm:ss.SSSXXX",
    "jsn": "【json】【按需。暂无用，用于扩展】",
    "body【json】【必给】【该节点将用于请求级缓存】Response Body，响应业务数据": {
      "code": "【字符串】【必给】响应代码",
      "info": "【字符串】【按需】响应信息",
      "page": "【json】【按需】对于分页表格类请求返回总条数等信息",
      "data": "【json】【按需】响应业务对象",
      "json": "【json】【按需。暂无用，用于扩展】"
    }
  }
}
```
