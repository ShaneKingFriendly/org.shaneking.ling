package org.shaneking.ling.persistence.entity.sql;

//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {Numbered.COLUMN__NO, Tenanted.COLUMN__TENANT_ID})})
public interface TenantedNumbered extends Tenanted, Numbered {
}
