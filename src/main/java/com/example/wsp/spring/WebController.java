package com.example.wsp.spring;

import com.example.wsp.spring.model.Authn;
import com.example.wsp.spring.model.RetrospectService;
import com.example.wsp.spring.model.SignService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    private RetrospectService service;

    @Autowired
    private SignService service2;

    @GetMapping("test")
@ResponseBody
public String test(Model model){
    return LocalDateTime.now().toString();
 }
    @GetMapping("GetPost")
    public String get(Model model){
        return "retrospect";
    }
//@PostMapping("GetPost")
//public String post(String text, Model model){
  //  var tmp = "name = 'text'でPOSTされたデータ:"+text;
    //System.out.println(tmp);
    //model.addAttribute("message",tmp);
    //model.addAttribute("postedAt",LocalDateTime.now());
    //return "retrospect";

    @PostMapping("GetPost")
    public String post(String text,Model model){
        var message = service.register(text);
        //var message = n > 0?n+"件を追加":"追加失敗";//note: 三項演算子
        model.addAttribute("message",message);
        var retrospectives = service.findAll();
        model.addAttribute("retrospectives",retrospectives);
        return "retrospect";
    }

    @GetMapping("SignIn")
    public String SingIn(Model model){
        return "signin";
    }

    @PostMapping("Signed")
    public String SingEd(Model model,String userId,String passphrase){
        var authn = service2.doSignIn(userId,passphrase);
        if(authn) {
            model.addAttribute("userId",userId);
            System.out.println("利用中のブラウザ識別番号:"+ httpSession.getId());
            httpSession.setAttribute("userId",userId);
            return "signed";
        }
        else{
            return "signin";
        }
    }

    @GetMapping("Profile")
    public String profile(Model model){
        System.out.println("利用中のブラウザ識別番号:"+ httpSession.getId());
        var userId = (String) httpSession.getAttribute("userId");
        model.addAttribute("userId",userId);
        Authn authn = service2.doprofile(userId);
        var user_name = authn.getUserName();
        var user_role = authn.getUserRole();
        model.addAttribute("userName",user_name);
        model.addAttribute("userRole",user_role);
        return "profile";
    }



    @GetMapping("Signed")
    public String signed(Model model){
        System.out.println("利用中のブラウザ識別番号:"+ httpSession.getId());
        var userId = (String)httpSession.getAttribute("userId");
        model.addAttribute("userId",userId);
        return "signed";
    }

    @Autowired
    private HttpSession httpSession;

    @GetMapping("SignOut")
    public String signout(Model model){
        httpSession.invalidate();
        return "signin";
    }



//@PostMapping("GetPost")
  //  public String post(String text,Model model){
    //var n = service.register(text);
    //var message = n > 0?n+"件を追加":"追加失敗";//note: 三項演算子
    //model.addAttribute("message",message);
   // return "retrospect";
//}
}

