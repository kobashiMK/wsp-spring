package com.example.wsp.spring.model;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
import static java.util.Collections.emptyList;

@Service
public class RetrospectService {

        //public int register(String text){
          //  var retrospect = new Retrospect(text);
            //return 0;
    //}
@Autowired
private RetrospectRepository repository;

public String register(String text){
    var retrospect = new Retrospect(text);
    try{
        var n = repository.insert(retrospect);
        var message = n > 0?n+"件を追加":"追加失敗";//note: 三項演算子
        return message;
    }catch (DataAccessException e){
        e.printStackTrace();
    }
    return "追加失敗";
}

public List<Retrospect>findAll(){
    try {
        return repository.select();
    }catch (DataAccessException e){
        e.printStackTrace();
    }
    return emptyList();
}
}
