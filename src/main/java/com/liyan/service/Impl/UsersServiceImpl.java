package com.liyan.service.Impl;

import com.liyan.mapper.UsersMapper;
import com.liyan.pojo.Users;
import com.liyan.service.UsersService;

public class UsersServiceImpl implements UsersService {
    private UsersMapper usersMapper;

    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Users Login(Users users) {
        return usersMapper.findByUsePwd(users);
    }
}
