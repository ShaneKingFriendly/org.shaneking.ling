package org.shaneking.ling.persistence.entity.sql;

//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {Numbered.COLUMN__NO})})
public interface TenantNumbered extends Tenanted, Numbered {
}
