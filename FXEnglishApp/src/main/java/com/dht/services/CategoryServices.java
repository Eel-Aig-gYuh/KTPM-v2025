/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Category;
import com.dht.pojo.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryServices {
    public List<Category> getCategories() throws SQLException {
        List<Category> cates = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM category");
            
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category q = new Category(rs.getInt("id"), rs.getString("name"));
                cates.add(q);
            }
        }
        
        return cates;
    }
    
    
}
