package com.example.TBA.controller;

import com.example.TBA.model.UserBean;
import com.example.TBA.repository.UserRepository;
import com.example.TBA.security.Autologin;
import com.example.TBA.service.EmailServiceImpl;
import com.example.TBA.service.UserInfoExtracter;
import com.example.TBA.social.providers.FacebookProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    FacebookProvider facebookProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Autologin autologin;

    @Autowired
    EmailServiceImpl emailServiceImpl;


    @Autowired
    UserInfoExtracter extracter;

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        UserBean userBean = facebookProvider.getUserBean();
        facebookProvider.saveUserDetails(userBean);
        return facebookProvider.login(model, userBean);
    }

    @RequestMapping(value = { "/login" })
    public String login(HttpServletRequest request) {
        extracter.extract(request);
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistration(UserBean userBean) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(HttpServletResponse httpServletResponse, Model model, @Valid UserBean userBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userBean.setProvider("REGISTRATION");
        // Save the details in DB
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
        userRepository.save(userBean);

        emailServiceImpl.sendSimpleMessage(userBean.getEmail(), "MidaKanda Registration", "Nimi: " + userBean.getFirstName());

        autologin.setSecuritycontext(userBean);

        model.addAttribute("loggedInUser", userBean);
        return "user";
    }

    /** If we can't find a user/email combination */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
