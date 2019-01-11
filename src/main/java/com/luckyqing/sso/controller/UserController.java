package com.luckyqing.sso.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luckyqing.sso.common.domain.DataTableResult;
import com.luckyqing.sso.entity.TygSysRole;
import com.luckyqing.sso.entity.TygSysUser;
import com.luckyqing.sso.result.Result;
import com.luckyqing.sso.service.RoleService;
import com.luckyqing.sso.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: notice
 * @description: 用户控制层
 * @useror: "清歌"
 * @create: 2018-08-20 17:44
 **/
@Controller
@RequestMapping("/userAdmin")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/admin/userAdmin/index";
    }

    /**
     * 权限列表页面跳转
     * @return
     */
    @RequestMapping("/toUsersList")
    public String toUsersList(){
        return "/admin/userAdmin/usersList";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser(Model model){
        TygSysRole role = new TygSysRole();
        List<TygSysRole> roleList = roleService.findRolesByCriteria(role);
        model.addAttribute("roleList", roleList);
        return "/admin/userAdmin/addUser";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEditPage(@PathVariable Long id, Model model){
        String msg = null;
        if (null != id && id > 0) {
            TygSysUser user = userService.findUserById(id);
            if (user != null){
                user.setUserPwd("");
                model.addAttribute("user",user);
                model.addAttribute("roleList",roleService.findRolesByCriteria(new TygSysRole()));
                return "/admin/userAdmin/editUser";
            }else {
                msg="数据为空，请稍后再试";
                return msg;
            }

        }
        msg = "您提交的数据为空";
        return msg;
    }

    /**
     * 根据条件查询权限
     * @param start 开始页
     * @param length ，一页显示的条数
     * @param user 清歌
     * @return
     */
    @RequestMapping(value = "/findUsersByCriteria",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DataTableResult findUsersByCriteria(@RequestParam(value = "start", defaultValue = "1")int start,
                                               @RequestParam(value = "length", defaultValue = "5")int length,
                                               TygSysUser user){

        Integer pageNo =(start+1)%length;
        pageNo = start/length +pageNo;
        PageHelper.startPage(pageNo,length);
        if (user != null && null != user.getUserName() && !"".equals(user.getUserName())){
            StringBuilder sb = new StringBuilder();
            sb.append("%").append(user.getUserName()).append("%");
            user.setUserName(sb.toString());
        }
        List<TygSysUser> usersList = userService.findUsersByCriteria(user);
        Page page = (Page)usersList;
        DataTableResult result = new DataTableResult();
        result.setStart(start);
        result.setLength(length);
        Long total = page.getTotal();
        result.setRecordsTotal(total.intValue());
        result.setRecordsFiltered(total.intValue());
        result.setData(usersList);
        return result;
    }

    /**
     * 添加用戶方法
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(TygSysUser user, @RequestParam(value = "roleId[]") Long[] roleIds){
        Result result = new Result();
        /*
        * 代码层
        * */
        result.setSuccess(false);
        result.setCode("4001");
        return result;
    }

    /**
     * 删除用戶，逻辑删除
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result deleteUserById(@PathVariable Long id){
        Result result = new Result();
        if (null == id || StringUtils.isBlank(id.toString())){
            result.setMsg("请上传合法数据");
            result.setSuccess(false);
            return result;
        }
        TygSysUser user = new TygSysUser();
        user.setCutOffTime(System.currentTimeMillis());
        user.setCutOff(1L);
        user.setId(id);
        /*
        * 如果报错，会捕获异常，看GlobalExceptionResolver
        * */
        userService.deleteUserById(user);
        result.setSuccess(true);
        result.setCode("200");
        result.setMsg("删除成功");
        return result;
    }

    /**
     * 更新用户
     * @param "清歌"
     * @return
     */
    @RequestMapping(value = "/updateUser" ,method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(TygSysUser user,@RequestParam(value = "roleId[]") Long[] roleIds){
        Result result = new Result();
       /*
       * 代码层
       * */
        result.setSuccess(false);
        result.setCode("4001");
        return result;
    }
}
