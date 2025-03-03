package com.lgh.demo;

import com.lgh.pojo.Question;
import com.lgh.services.QuestionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable{
    @FXML private Text txtContent;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;
    @FXML private Text txtA;
    @FXML private Text txtB;
    @FXML private Text txtC;
    @FXML private Text txtD;

    
    private int currentIdx = 0;
    private List<Question> question;

    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void checkHandler(ActionEvent e) throws SQLException{
        QuestionService s = new QuestionService();
        List<Question> questions = s.getQuestion(2);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION, questions.get(0).getContent(), ButtonType.OK);
        a.show();
                
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuestionService s = new QuestionService();
        try {
            this.question = s.getQuestion(2);
            
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void loadQuestionToUI(){
        QuestionService s = new QuestionService();
        Question q = this.question.get(currentIdx);
        txtContent.setText(q.getContent());
        
        if(q.getChoices() == null){
            try {
                q.setChoices(s.getChoices(q.getId()));
                
                txtA.setText(q.getChoices().get(0).toString());
                txtB.setText(q.getChoices().get(1).toString());
                txtC.setText(q.getChoices().get(2).toString());
                txtD.setText(q.getChoices().get(3).toString());

                
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
