package com.tencent.wxcloudrun;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersEntity, String> {
    boolean existsByUsernameAndPassword(String username, String password);
}
