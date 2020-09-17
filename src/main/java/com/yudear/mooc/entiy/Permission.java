package com.yudear.mooc.entiy;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class Permission  implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer  id;
    private Integer pid;
    private String title;
    private String icon;
    private String name;
    private String path;
    private Integer type;
    private Integer sort;
    private Integer hidden;

    private List<Permission> children= Collections.emptyList();

    @TableField(value = "create_time")
    @JSONField(serialize = false)
    private Date createTime;

    @TableField(value = "update_time")
    @JSONField(serialize = false)
    private Date updateTime;
}
