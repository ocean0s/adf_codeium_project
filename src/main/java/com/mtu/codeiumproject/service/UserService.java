package com.mtu.codeiumproject.service;

import com.mtu.codeiumproject.entity.MyUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    public MyUser createUser(MyUser user);

    public MyUser updateUser(MyUser user);

    public void deleteUserById(Long id);

    public MyUser getUserById(Long id);
}
