package com.yudear.mooc.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "tab_user")
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer  id;
    private String  name;
    private String password;
    private String sex;
}
