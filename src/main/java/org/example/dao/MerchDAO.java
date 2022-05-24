package org.example.dao;

import org.example.models.Merch;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MerchDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MerchDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Merch> index() {

        return jdbcTemplate.query("SELECT * FROM merch", new BeanPropertyRowMapper<>(Merch.class));
    }
}
