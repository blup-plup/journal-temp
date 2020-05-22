package com.TheJournal.Login.Controllers;

import com.TheJournal.Login.BIz.UserBizService;
import com.TheJournal.Login.Controllers.Requests.RegisterRequest;
import com.TheJournal.Login.Controllers.Requests.LoginRequest;
import com.TheJournal.Login.Controllers.Response.SimpleResponse;
import com.TheJournal.Login.Dtos.UserDto;
import com.TheJournal.Login.Dtos.UserSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserBizService userBizService;


    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {

        UserDto userDto = null;
        try{
            userDto = userBizService.registerUser(registerRequest);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(new SimpleResponse("Failure",400, null));
        }
        return ResponseEntity.ok().body(new SimpleResponse("Success", 200, userDto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){

        UserSessionDto userSessionDto = null;
        try{
            userSessionDto = userBizService.userLogin(loginRequest);
            return ResponseEntity.ok().body(new SimpleResponse("Success",200,userSessionDto));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(new SimpleResponse("Failed",400,null));
        }
    }
}
