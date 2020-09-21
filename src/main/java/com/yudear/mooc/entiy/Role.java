package com.yudear.mooc.entiy;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

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
    private String flag;
    @TableField(value = "create_time")
    @JSONField(serialize = false)
    private Date createTime;
    @TableField(value = "update_time")
    @JSONField(serialize = false)
    private Date updateTime;



}
