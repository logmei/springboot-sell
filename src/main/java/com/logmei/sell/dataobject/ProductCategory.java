package com.logmei.sell.dataobject;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate //更新时间为系统生成时间，可以动态改变
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length =8)
    private Integer categoryId;

    @Column(length = 128,nullable = false)
    private String categoryName;

    @Column(length = 1,nullable = false)
    private Integer categoryType;

//    colmenDefinition可以写ddl命令  insertable和updatable设置为false不会组装到sql
    @Column(nullable = false,insertable = false,updatable = false,columnDefinition = "timestamp default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;
//修改时间这必须要有on update current_timestamp
    @Column(nullable = false,insertable = false,updatable = false,columnDefinition = "timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间'")
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String  toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
