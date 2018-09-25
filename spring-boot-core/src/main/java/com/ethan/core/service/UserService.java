package com.ethan.core.service;

import java.util.List;

/**
 * @Author Ethan Cao
 * @Description //TODO P0500066
 * @Date 11:11 AM 9/24/2018
 * @Param 
 * @return 
 **/
public interface UserService<T> {
    /**
     * fetch data by rule id
     * @return user list
     */
    List<T> getUsers();
}
