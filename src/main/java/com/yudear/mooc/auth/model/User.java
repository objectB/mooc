package com.yudear.mooc.auth.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private String id;
    private String username;
    private String roles;
    private String permissions;




}
