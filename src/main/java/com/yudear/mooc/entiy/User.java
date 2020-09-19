package com.yudear.mooc.entiy;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "tab_user")
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer  id;
    private String  nickname;
    private String username;
    private String password;
    private String avatar;
    private String moto;
    private Integer sex;
    private String email;
    private Integer status;
    @JSONField(serialize = false)
    private Integer del;
    @TableField(value = "create_time")
    @JSONField(serialize = false)
    private Date createTime;
    @TableField(value = "update_time")
    @JSONField(serialize = false)
    private Date updateTime;



}
