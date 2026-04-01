package org.hrd.demojwt.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.demojwt.model.entity.AppUser;
import java.util.List;

@Mapper
public interface AppUserRepository {
    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "getAllRolesByUserId"))
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{email}
    """)
    AppUser getUserByEmail(String email);

    @Select("""
        SELECT name FROM app_user_role aur
        INNER JOIN app_roles ar
        ON aur.role_id = ar.role_id
        WHERE user_id = #{userId}
    """)
    List<String> getAllRolesByUserId(Long userId);
}
