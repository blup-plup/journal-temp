package com.TheJournal.Login.BIz;

import com.TheJournal.Login.Controllers.Requests.LoginRequest;
import com.TheJournal.Login.Controllers.Requests.RegisterRequest;
import com.TheJournal.Login.Dtos.UserDto;
import com.TheJournal.Login.Dtos.UserSessionDto;
import com.TheJournal.Login.Entities.User;
import com.TheJournal.Login.Repositories.UserRepository;
import com.TheJournal.Login.Repositories.UserSessionRepository;
import com.TheJournal.Login.Utilities.EncryptionUtility;
import com.TheJournal.Login.Utilities.LoginDateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBizService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    UserSessionBizService userSessionBizService;

    private ModelMapper modelMapper = new ModelMapper();


    public UserDto registerUser(RegisterRequest request) throws Exception {

        if(request.getEmail() == null || request.getEmail().isEmpty()){
            throw new Exception("invalid email");
        }

        if(request.getName() == null || request.getName().isEmpty()){
            throw new Exception("Invalid name");
        }

        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new Exception("Invalid Password");
        }

        UserDto userDto = new UserDto();
        userDto.setName(request.getName());
        userDto.setEmail(request.getEmail());
        userDto.setPassword(EncryptionUtility.stringEncryption(request.getPassword()));
        userDto.setCreatedAt(LoginDateUtils.getUnixTimestampNow());
        User user = new User();
        modelMapper.map(userDto,user);

        userRepository.save(user);

        return modelMapper.map(user,UserDto.class);
    }


    public UserSessionDto userLogin(LoginRequest request) throws Exception {
        if(request.getEmail() == null || request.getEmail().isEmpty()){
            throw new Exception("invalid email");
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new Exception("Invalid Password");
        }

        try{
            User user = userRepository.findByEmail(request.getEmail());
            UserDto userDto = modelMapper.map(user,UserDto.class);

            UserSessionDto userSessionDto = userSessionBizService.createSession(userDto);

            return userSessionDto;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Failed");
        }
    }



}
