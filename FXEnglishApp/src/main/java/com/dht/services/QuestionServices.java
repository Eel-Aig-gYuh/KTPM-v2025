/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.JdbcUtils;
import com.dht.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class QuestionServices {

    public List<Question> getQuestions(int num, String kw) throws SQLException {
        List<Question> questions = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm;
            
            if (num == 0) {
                stm = conn.prepareCall("SELECT * FROM question where content like concat('%', ?, '%') ORDER BY id desc");
                stm.setString(1, kw);
            }
            else {
                stm = conn.prepareCall("SELECT * FROM question ORDER BY id desc LIMIT ?");
                stm.setInt(1, num);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question(rs.getString("id"), rs.getString("content"), rs.getInt("category_id"));
                questions.add(q);
            }
        }
        return questions;
    }
    
    public void addQuestion(Question q, List<Choice> choices) {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            String sql1 = "INSERT INTO question(id, content, category_id) VALUES (?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql1);
            
            stm.setString(1, q.getId());
            stm.setString(2, q.getContent());
            stm.setInt(3, q.getCategoryId());
          
            int k = stm.executeUpdate();
            
            if (k>0) {
                String sql2 = "INSERT INTO choice(id, content, is_correct, question_id) VALUES (?, ?, ?, ?)";
                
                PreparedStatement stm2 = conn.prepareCall(sql2);
                
                for (var c: choices) {
                    stm2.setString(1, c.getId());
                    stm2.setString(2, c.getContent());
                    stm2.setBoolean(3, c.isCorrect());
                    stm2.setString(4, c.getQuestionId());
                    
                    stm2.executeUpdate();
                }
            }
                  
            conn.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Choice> getChoices(String questionId) throws SQLException {
        List<Choice> choices = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm;
            if (!questionId.isEmpty()) {
                stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
                stm.setString(1, questionId);
            }
            else {
                stm = conn.prepareCall("SELECT * FROM choice");
                stm.setString(1, questionId);
            }
            

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Choice c = new Choice(rs.getString("id"), rs.getString("content"), rs.getBoolean("is_correct"), rs.getString("question_id"));
                choices.add(c);
            }
        }

        return choices;
    }
}
