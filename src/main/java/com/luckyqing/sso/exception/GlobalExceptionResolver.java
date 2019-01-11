package com.luckyqing.sso.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.luckyqing.sso.result.Result;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: notice
 * @description: 全局异常处理器
 * @author: "清歌"
 * @create: 2018-08-22 14:48
 **/
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv;
        //进行异常判断。如果捕获异常请求跳转。
        if(ex instanceof UnauthorizedException){
            mv = new ModelAndView("error");
            return mv;
        }else {
            mv = new ModelAndView();
            FastJsonJsonView view = new FastJsonJsonView();
            Result result = new Result();
            result.setMsg("服务器异常");
            ex.printStackTrace();
            logger.error(ExceptionUtils.getFullStackTrace(ex));
            Map<String,Object> map = new HashMap<>();
            String beanString = JSON.toJSONString(result);
            map = JSON.parseObject(beanString,Map.class);
            view.setAttributesMap(map);
            mv.setView(view);
            return mv;
        }
    }
}
