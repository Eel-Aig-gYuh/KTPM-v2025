package com.dht.fxenglishapp;

import com.dht.pojo.Question;
import com.dht.services.QuestionServices;
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

public class PrimaryController implements Initializable {
    @FXML private Text txtContent;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;
    @FXML private Text txtA;
    @FXML private Text txtB;
    @FXML private Text txtC;
    @FXML private Text txtD;
    private List<Question> questions;
    private int currentIdx = 0;

    public void checkHandler(ActionEvent e) throws SQLException {
        Question q = this.questions.get(currentIdx);
        boolean d1 = rdoA.isSelected() && q.getChoices().get(0).isCorrect();
        boolean d2 = rdoB.isSelected() && q.getChoices().get(1).isCorrect();
        boolean d3 = rdoC.isSelected() && q.getChoices().get(2).isCorrect();
        boolean d4 = rdoD.isSelected() && q.getChoices().get(3).isCorrect();
        
        if (d1 || d2 || d3 || d4)
            Utils.getAlert("EXACTLY!").show();
        else
            Utils.getAlert("WRONGLY!").show();
    }
    
    public void nextHandler(ActionEvent e) {
        if (this.currentIdx < this.questions.size() - 1) {
            this.currentIdx++;
            
        } else
            this.currentIdx = 0;
        
        loadQuestionToUI();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuestionServices s = new QuestionServices();
        try {
            this.questions =  s.getQuestions(3, null);
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadQuestionToUI() {
        QuestionServices s = new QuestionServices();
        Question q = this.questions.get(currentIdx);
        txtContent.setText(q.getContent());
        
        if (q.getChoices() == null)
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
