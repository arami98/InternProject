package com.medialog.InternProject.service;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    UserRepository userRepository;



    @Override
    public String register(User user) throws IllegalArgumentException{

        checkValidPhoneNumber(user.getTel());
        checkValidSSNNumber(user.getSsn());
        checkIfUserExists(user.getLoginId());
        userRepository.save(user);
        return user.getName();
    }

    private void checkIfUserExists(String id) throws IllegalArgumentException{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            throw new IllegalArgumentException("USER ALREADY EXISTS");
        }
    }
    private void checkValidSSNNumber(String ssn) throws IllegalArgumentException {
        String regex = "^\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])*[1-4]\\d{6}$";
        boolean isValid =  Pattern.matches(regex, ssn);

        if(!isValid)
            throw new IllegalArgumentException("SSN NUMBER IS INVALID");
    }

    private void checkValidPhoneNumber(String tel) throws IllegalArgumentException{
        String regex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
        boolean isValid =  Pattern.matches(regex, tel);

        if(!isValid)
            throw new IllegalArgumentException("PHONE NUMBER IS INVALID");
    }
}
