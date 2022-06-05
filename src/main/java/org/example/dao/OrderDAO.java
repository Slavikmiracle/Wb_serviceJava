package org.example.dao;

import org.example.models.Merch;
import org.example.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Order> index() {
        return jdbcTemplate.query("SELECT * FROM orderingthings ORDER BY id", new BeanPropertyRowMapper<>(Order.class));
    }

    public List<Order> search(String property) {
        return jdbcTemplate.query("SELECT * FROM orderingthings WHERE users=?",
                new BeanPropertyRowMapper<>(Order.class), property);
    }
    public void save(Order order, String userName) {
        int idLast = jdbcTemplate.queryForObject("SELECT id FROM orderingthings ORDER BY id DESC LIMIT 1", Integer.class);
        jdbcTemplate.update("INSERT INTO orderingthings VALUES(?, ?, ?, ?)", (idLast + 1), order.getThings(), userName, "Отправлено");
    }

    public void update(int id, Order updatedOrder) {
        jdbcTemplate.update("UPDATE orderingthings SET ready = ? WHERE id=?",
                updatedOrder.getReady(), id);
    }

    public Order show(int id) {
        return jdbcTemplate.query("SELECT * FROM orderingthings WHERE id=? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Order.class)).stream().findAny().orElse(null);
    }
}
