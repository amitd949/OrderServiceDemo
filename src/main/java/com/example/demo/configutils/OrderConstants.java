package com.example.demo.configutils;

import com.example.demo.Auth.User;

import java.util.Map;

public class OrderConstants {

    public static final Map<String,String>loginUserConfigs =
            Map.of("Amit","amit1", "Garima", "garima","Ramu","ramu", "john", "password", "admin","admin");


    public static final Map<String, User> loginUserConfigsNew =
            Map.of(
                    "Amit", new User("Amit", "amit1", "USER"),
                    "Garima", new User("Garima", "garima", "USER"),
                    "Ramu", new User("Ramu", "ramu", "USER"),
                    "john", new User("john", "password", "USER"),
                    "admin", new User("admin", "admin", "ADMIN")
            );



}
