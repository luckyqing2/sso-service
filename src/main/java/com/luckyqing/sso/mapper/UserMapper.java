package com.luckyqing.sso.mapper;

import com.luckyqing.sso.entity.TygSysUser;
import com.luckyqing.sso.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @program: notice
 * @description: 角色DAO
 * @useror: "清歌"
 * @create: 2018-08-21 14:13
 **/
public interface UserMapper {

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into tyg_sys_user(user_name,user_pwd,creat_time) " +
            "values(#{userName},#{userPwd},#{creatTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertUser(TygSysUser user);

    /**
     * 根据条件查找users
     * @param user
     * @return
     */
    @Results(id = "userMap" ,value = {
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "userName", column = "user_name", javaType = String.class),
            @Result(property = "userPwd", column = "user_pwd", javaType = String.class),
            @Result(property = "creatTime", column = "creat_time", javaType = Long.class),
            @Result(property = "cutOff", column = "cut_off", javaType = Long.class),
            @Result(property = "roles", column = "id", many = @Many(select = "com.luckyqing.sso.mapper.RoleMapper.findRolesByUserId")),
    })
    @SelectProvider(type = UserProvider.class,method = "findUsersByCriteria")
    List<TygSysUser> findUsersByCriteria(TygSysUser user);

    /**
     * 删除用户，非实际删除
     * @param user
     */
    @Select("update tyg_sys_user set cut_off = 1,cut_off_time = #{cutOffTime} where id = #{id}")
    void deleteUserById(TygSysUser user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update tyg_sys_user set user_name = #{userName},user_pwd=#{userPwd} where id = #{id}")
    void updateUser(TygSysUser user);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    @ResultMap("userMap")
    @Select("select * from tyg_sys_user where id = #{id}")
    TygSysUser findUserById(Long id);

    @ResultMap("userMap")
    @Select("select * from tyg_sys_user where user_name = #{userName}")
    TygSysUser findUserByUserName(String userName);

    @Select("SELECT\n" +
            "\tDISTINCT a.auth_permission\n" +
            "FROM\n" +
            "\ttyg_sys_user u\n" +
            "INNER JOIN tyg_user_role ur ON u.id = ur.user_id\n" +
            "INNER JOIN tyg_sys_role r ON ur.role_id = r.id\n" +
            "INNER JOIN tyg_role_auth ra ON r.id = ra.role_id\n" +
            "INNER JOIN tyg_sys_auth a ON ra.auth_id = a.id\n" +
            "WHERE\n" +
            "\tu.id = #{userId}")
    List<String> findAuthsByUserId(Long userId);

    @Insert("INSERT INTO tyg_user_role(user_id,role_id) VALUES(#{userId},#{roleId})")
    void addRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Insert("delete from tyg_user_role where user_id=#{userId}")
    void deleteRolesIdByUserId(@Param("userId") Long userId);
}


