# org.shaneking.ling.persistence

Persistence for ShaneKing

## 属性说明

```json
{
  "id": "技术上唯一标识，有序递增的唯一标识。常使用 UUID0.cUl33() 生成",
  "version": "INT类型。用于乐观锁",
  "dd": "技术删除标记，如果为`N`表示`未删除`，其他值表示已删除。通常为 UUID0.cUl33() 生成的值，便于追溯删除时间。如果技术删除后，记录长时间不物理删除，建议所有唯一索引添加该字段",
  "invalid": "业务失效标记，常用于活动到期失效，用户冻结失效等",
  "lastModifyDateTime": "最后修改时间，yyyy-MM-dd HH:mm:ss",
  "lastModifyUserId": "最后修改人id",
  "no": "业务上唯一标识（多租户模式下，可能租户内唯一，看唯一索引定义的方式），用于与对接系统对接。默认与`id`值一致"
}
```
