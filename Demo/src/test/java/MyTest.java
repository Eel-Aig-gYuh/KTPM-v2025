/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.lgh.pojo.Choice;
import com.lgh.pojo.Question;
import com.lgh.services.QuestionService;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


/**
 *
 * @author admin
 */
public class MyTest {
    
    @Test
    public void testCates() throws SQLException {
        QuestionService s = new QuestionService();
        List<Question> questions = s.getQuestion(2);
        
        System.out.println(questions.get(0).getContent());
        
        boolean expected = true;

        for (var i : questions) {
            boolean isHasCate = true;
            if (i.getCategoryId() > 0) {
                isHasCate = false;
            }
            Assertions.assertEquals(expected, isHasCate);
        }

        
    }
    
    
    @Test
    public void testChoices() throws SQLException {
        QuestionService s = new QuestionService();
        Question question = (Question) s.getQuestion(0);
        
        List<Choice> choices = s.getChoices(question.getId());
        
        boolean expectedOutput = true;
        boolean input = choices.size() == 4;
        
        
        Assertions.assertEquals(expectedOutput, input);
    }
}
