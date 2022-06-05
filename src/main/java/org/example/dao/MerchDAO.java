package org.example.dao;

import org.example.models.Merch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
@Component

public class MerchDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public MerchDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Merch show(int id) {
        return jdbcTemplate.query("SELECT * FROM merch WHERE id=? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Merch.class)).stream().findAny().orElse(null);
    }
    public List<Merch> search(String property) {
        return jdbcTemplate.query("SELECT * FROM merch WHERE institute=?",
                new BeanPropertyRowMapper<>(Merch.class), property);
    }

    public List<Merch> index() {
        return jdbcTemplate.query("SELECT * FROM merch ORDER BY id", new BeanPropertyRowMapper<>(Merch.class));
    }

    public void update(int id, Merch updatedMerch) {
        jdbcTemplate.update("UPDATE merch SET name=?, value =?, types =?, institute = ?,description = ? WHERE id=?",
                updatedMerch.getName(), updatedMerch.getValue(), updatedMerch.getTypes(),
                updatedMerch.getInstitute(), updatedMerch.getDescription(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM merch WHERE id=?", id);
    }

    public void save(Merch merch) {
        int idLast = jdbcTemplate.queryForObject("SELECT id FROM merch ORDER BY id DESC LIMIT 1", Integer.class);
        jdbcTemplate.update("INSERT INTO merch VALUES(?, ?, ?, ?,?,?)", (idLast+1), merch.getName(), merch.getValue(),
                merch.getTypes(), merch.getInstitute(), merch.getDescription());
    }
}