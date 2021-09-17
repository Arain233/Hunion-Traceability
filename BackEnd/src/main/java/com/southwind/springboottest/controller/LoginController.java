package com.southwind.springboottest.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.southwind.springboottest.bean.LoginBean;
import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.fabric.Init;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.security.JwtAuthenticatioToken;
import com.southwind.springboottest.service.UserService;
import com.southwind.springboottest.utils.JwtTokenUtils;
import com.southwind.springboottest.utils.PasswordEncoder;
import com.southwind.springboottest.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;


@Api(value="/api/login",tags = "登录操作")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private Init init;

    @Autowired
    private Producer producer;

    @PostMapping(value = "/api/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws Exception
    {
        String username = loginBean.getName();
        String password = loginBean.getPassword();
//        String kap=loginBean.getKapt();

//        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(kap == null){
//            return HttpResult.error("验证码已失效");
//        }
//        if(!kap.equals(kaptcha)){
//            return HttpResult.error("验证码不正确");
//        }

        SecureRandom random = new SecureRandom();
        byte[] salt1 = new byte[48];
        random.nextBytes(salt1);

        if(userService.findByName("admin")==null)
        {
            userService.save(new User(
                    "admin","admin",
                    "admin",Base64.getEncoder().encodeToString(salt1),
                    "abc@163.com","13130847823",1));
        }

        User user = userService.findByName(username);
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        String salt2=user.getSalt();
        if (!Objects.equals(userService.findByName(username).getPassword(),PasswordEncoder.Encrypt(password,salt2))) {
            return HttpResult.error("密码不正确");
        }
        HttpResult r=new HttpResult();
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, user.getId().toString());
        r.setMsg(JwtTokenUtils.getRoleFromToken(token.getToken()));
        r.setData(token);
        init.init();
        return r;
    }
    @ApiOperation(value = "注册用户")
    @PostMapping("/api/save")
    public HttpResult save(@RequestBody User user)
    {
        if(Objects.equals(userService.findByName(user.getName()),null))
        {
            HttpResult.error();
        }
        return HttpResult.ok(userService.save(user));
    }

    @GetMapping("/api/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

        try {
            if (out != null) {
                out.close();
            }
        } catch (final IOException ioe) {
        }
    }
}
