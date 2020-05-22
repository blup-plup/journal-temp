package com.TheJournal.Login.BIz;

import com.TheJournal.Login.Dtos.UserDto;
import com.TheJournal.Login.Dtos.UserSessionDto;
import com.TheJournal.Login.Entities.UserSession;
import com.TheJournal.Login.Repositories.UserRepository;
import com.TheJournal.Login.Repositories.UserSessionRepository;
import com.TheJournal.Login.Utilities.LoginDateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserSessionBizService {

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Scheduled(fixedRate = 500)
    public void autoLogoutService() {
        List<UserSession> userSessionsList = (List<UserSession>) userSessionRepository.findAll();

        for (UserSession session: userSessionsList
             ) {
            UserSessionDto sessionDto =  modelMapper.map(session,UserSessionDto.class);
            if(sessionDto.getEnd() == null || session.getEnd() == 0){
                long sessionTime = LoginDateUtils.getUnixTimestampNow() - sessionDto.getStart();
                if(sessionTime >= 300){
                    sessionDto.setEnd(LoginDateUtils.getUnixTimestampNow());
                    session = modelMapper.map(sessionDto,UserSession.class);
                    userSessionRepository.save(session);
                }
            }
        }

    }

    public UserSessionDto createSession(UserDto userDto){
        UserSessionDto userSessionDto = new UserSessionDto();

        userSessionDto.setParentId(userDto.getId());
        userSessionDto.setStart(LoginDateUtils.getUnixTimestampNow());
        userSessionDto.setSugarId(UUID.randomUUID().toString());

        UserSession userSession = modelMapper.map(userSessionDto,UserSession.class);
        userSession = userSessionRepository.save(userSession);

        return modelMapper.map(userSession,UserSessionDto.class);
    }
}
