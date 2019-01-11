package com.luckyqing.sso.controller;

import com.alibaba.fastjson.JSONObject;
import com.luckyqing.sso.entity.TygSysUser;
import com.luckyqing.sso.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: notice
 * @description: 登录控制类
 * @author: "清歌"
 * @create: 2018-08-20 17:46
 **/
@Controller
public class LoginController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${SESSION.USER.PRIFIX}")
    private String SESSION_USER_PRIFIX;


    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req,String service, HttpServletResponse response, Model model) throws Exception{
        //判断session中的token是否存在
        String tokenString = (String)req.getSession().getAttribute("token");
        System.out.println("token值 ==== "+ tokenString);
        if (StringUtils.isNotBlank(tokenString)){
        //如果存在根据key取redis中的值，如果有则重定向回访问的服务器，并重新设置过期时间,把token值作为参数传递到访问的服务器
        Boolean tokenKey = stringRedisTemplate.hasKey(SESSION_USER_PRIFIX + tokenString);
        if (tokenKey) {
            stringRedisTemplate.expire(SESSION_USER_PRIFIX + tokenString,30, TimeUnit.MINUTES);
            if (StringUtils.isBlank(service)){
                return "redirect:/index";
            }
            service = service + "?ticket=" + tokenString;
            response.sendRedirect(service);
            return null;
            }
        }
        // 判断已登录情况
        String error = null;
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //当GET请求的时候，没有登录直接跳转登录页面，否则进行认证
            String method = req.getMethod();
            if ("get".equals(method.toLowerCase())){
                req.getSession().setAttribute("service",service);
                return "login";
            }
            //获取当前用户对象
            Subject subject = SecurityUtils.getSubject();
            //生成令牌(传入用户输入的账号和密码)
            UsernamePasswordToken token = new UsernamePasswordToken(req.getParameter("userName"), MD5Utils.getMD5(req.getParameter("userPwd")));
            //认证登录
            //这里会加载自定义的realm
            try {
                //把令牌放到login里面进行查询,如果查询账号和密码时候匹配,如果匹配就把user对象获取出来,失败就抛异常
                subject.login(token);
                //登录成功在session中存放token值,并将token值作为键值存放到redis中，值为用户信息，方便client取，记得一定要实现Serializable
                String uuid = UUID.randomUUID().toString().replace("-", "");
                req.getSession().setAttribute("token",uuid);
                PrincipalCollection principals = subject.getPrincipals();
                TygSysUser userInfo = (TygSysUser) principals.getPrimaryPrincipal();
                String user = JSONObject.toJSONString(userInfo);
                stringRedisTemplate.opsForValue().set(SESSION_USER_PRIFIX+ uuid,user);
                stringRedisTemplate.expire(SESSION_USER_PRIFIX+ uuid,30,TimeUnit.MINUTES);
                //重定向回访问的服务器
                if (StringUtils.isBlank(service)){
                    return "index";
                }
                service = service + "?ticket=" + uuid;
                response.sendRedirect(service);
                return null;
                //return "index";
            }catch (UnknownAccountException ue){
                error = "用户名或者密码错误";
                System.out.println(error);
            } catch (IncorrectCredentialsException ie){
                error = "用户名或者密码错误";
                System.out.println(error);
            }catch (DisabledAccountException de){
                error = "该账号不允许登录";
                System.out.println(error);
            }catch (AuthenticationException ae){
                error = "用户异常";
                System.out.println(error);
            }
            model.addAttribute("error", error);
            req.getSession().setAttribute("service",service);
            return "/login";
        }else {
            System.out.println(SecurityUtils.getSubject().getPrincipal()+"------认证结果");
            return "redirect:index";
        }
    }
}
