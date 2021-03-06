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
    private String typename;
    private String permissionname;


}
