package com.kpi.services;

import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;

public class UserService {

    public User getAuthorizedUser(String token1){
        UsersDao usersDao = new UsersDao();
        int userId = UsersOnlineDao.getUserId(token1);
        return usersDao.getById(userId);
    }
}
