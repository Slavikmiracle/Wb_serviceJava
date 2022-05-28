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

    public Merch show(int id) {
        return jdbcTemplate.query("SELECT * FROM merch WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Merch.class))
                .stream().findAny().orElse(null);
    }

    public List<Merch> index() {

        return jdbcTemplate.query("SELECT * FROM merch", new BeanPropertyRowMapper<>(Merch.class));
    }

    public void update(int id, Merch updatedMerch) {
        jdbcTemplate.update("UPDATE merch SET name=?, value =?, types =?, institute = ? WHERE id=?", updatedMerch.getName(),
                updatedMerch.getValue(), updatedMerch.getTypes(), updatedMerch.getInstitute(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM merch WHERE id=?", id);
    }

   /* public void save(Merch merch) {
        jdbcTemplate.update("INSERT INTO merch VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());
    }*/
}
