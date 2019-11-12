package com.example.wsp.spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
public class AuthnRepository {

    @Autowired
    private JdbcTemplate jdbc;
    public Authn select(String useId) {
        try {
            var sql = "select user_id,passphrase,user_name,user_role from authn where user_id = ?";
            return jdbc.queryForObject(sql, newInstance(Authn.class), useId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
