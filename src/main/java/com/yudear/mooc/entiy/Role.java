package com.yudear.mooc.entiy;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@TableName("tab_role")
public class Role implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer  id;
    private String name;
    private String desc;
    private String avatar;
    private Integer sort;
    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "update_time")
    private Date updateTime;



}
