package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.dom.core.Element;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
          //转发到登录页面
        return "settings/qx/user/login";
    }
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
             //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject =new ReturnObject();
                //根据查询结果生成响应信息
            if(user==null){
                   //登录失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMassage("用户名或密码错误");
            }else {
                //进一步判断是否合法
                /*user.getExpireTime()
                        new Date()*/
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String nowStr = sdf.format(new Date());
               if(nowStr.compareTo(user.getExpireTime())>0){
                          //登陆失败账号过期
                   returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                   returnObject.setMassage("账号已过期");
               }else if("0".equals(user.getLockState())){
                   //登录失败，状态锁定
                   returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                   returnObject.setMassage("状态锁定");
               }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                    //登录失败，ip受限
                   returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                   returnObject.setMassage("ip受限");
               }else{
                   //登陆成功
                   returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                     //把user保存到session中

                   session.setAttribute(Contants.SESSION_USER,user);
                   //如果需要记住密码，则往外写cookie
                   if("true".equals(isRemPwd)){
                       Cookie c1=new Cookie("loginAct",user.getLoginAct());
                       c1.setMaxAge(10*24*60*60);
                       response.addCookie(c1);
                       Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
                       c2.setMaxAge(10*24*60*60);
                       response.addCookie(c2);
                   }else{
                       //把没有过期cookie删除
                       Cookie c1=new Cookie("loginAct","1");
                       c1.setMaxAge(0);
                       response.addCookie(c1);
                       Cookie c2=new Cookie("loginPwd","1");
                       c2.setMaxAge(0);
                       response.addCookie(c2);
                   }
               }

            }
            return returnObject;
    }
               @RequestMapping("/settings/qx/user/logout.do")
                public String logout(HttpServletResponse response,HttpSession session){
                         //清空cookie
                   Cookie c1=new Cookie("loginAct","1");
                   c1.setMaxAge(0);
                   response.addCookie(c1);
                   Cookie c2=new Cookie("loginPwd","1");
                   c2.setMaxAge(0);
                   response.addCookie(c2);
                   //销毁session
                   session.invalidate();
                   //跳转到首页
                   return "redirect:/";
                }
}
