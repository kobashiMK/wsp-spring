package com.example.wsp.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SignService {
 @Autowired
 private AuthnRepository authnRepository;

    public boolean doSignIn(String userId,String passphrase){
        var Authn = authnRepository.select(userId);
        try {
                if (Authn==null) {
                    return false;
                }
                else {
                    return Authn.getPassphrase().equals(passphrase);
                }
        }catch (DataAccessException e){

        }
            return false;
    }
    public Authn doprofile(String userId){
        var Authn = authnRepository.select(userId);
        return Authn;
    }
}
