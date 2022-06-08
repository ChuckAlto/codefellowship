package com.lab16.lab16.controller;

import com.lab16.lab16.model.AppUser;
import com.lab16.lab16.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    AppRepository appRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage(){

        return "signup";
    }

//    @GetMapping("/")
//    public String
//        return
//
//    @GetMapping("/")
//    public String
//        return

//    @PostMapping("/login")
//    public RedirectView logInUser(String username, String password) {
//        return new RedirectView("/");
//    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        String hashedPw = passwordEncoder.encode(password);
        AppUser newUser = new AppUser(username, hashedPw);
        appRepository.save(newUser);

        authWithHttpServletRequest(username, password, firstName, lastName, dateOfBirth, bio);


        return new RedirectView("/");
    }
    public void authWithHttpServletRequest(String username, String password, String firstName, String lastName, String dateOfBirth, String bio)
    {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in.");
            e.printStackTrace();
        }
    }
}
