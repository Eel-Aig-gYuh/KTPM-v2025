/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.fxenglishapp;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.CategoryServices;
import com.dht.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXQuestionsController implements Initializable {
    @FXML ComboBox<Category> cbCates;
    @FXML TableView<Question> tbQuestions;
    @FXML TextField txtSearch;
    
    @FXML RadioButton rdoA;
    @FXML RadioButton rdoB;
    @FXML RadioButton rdoC;
    @FXML RadioButton rdoD;

    @FXML TextField txtA;
    @FXML TextField txtB;
    @FXML TextField txtC;
    @FXML TextField txtD;

    @FXML TextArea txtContent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        CategoryServices s = new CategoryServices();
        
        try {
            this.cbCates.setItems(FXCollections.observableList(s.getCategories()));
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadColums();
        loadTableData("");
        txtSearch.textProperty().addListener((e) -> {
            loadTableData(txtSearch.getText());
        });
    }    
    
    
    public void addHandler (ActionEvent e) {
        Question q = new Question(UUID.randomUUID().toString(), txtContent.getText(), 
            this.cbCates.getSelectionModel().getSelectedItem().getId());
        
        Choice c1 = new Choice(UUID.randomUUID().toString(), txtA.getText(), rdoA.isSelected(), q.getId());
        Choice c2 = new Choice(UUID.randomUUID().toString(), txtB.getText(), rdoB.isSelected(), q.getId());
        Choice c3 = new Choice(UUID.randomUUID().toString(), txtC.getText(), rdoC.isSelected(), q.getId());
        Choice c4 = new Choice(UUID.randomUUID().toString(), txtD.getText(), rdoD.isSelected(), q.getId());
        
        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);

        QuestionServices s = new QuestionServices();
        
        s.addQuestion(q, choices);
        Utils.getAlert("Them thanh cong!!");
        loadTableData("");
    }
 
    
    public void loadTableData(String kw){
        QuestionServices s = new QuestionServices();
        
        try {
            this.tbQuestions.setItems(FXCollections.observableList(s.getQuestions(0, kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void loadColums() {
        TableColumn colContent = new TableColumn("Noi dung cau hoi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(400);
        
        TableColumn colCate = new TableColumn("Danh muc");
        colCate.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCate.setPrefWidth(200);
        
        this.tbQuestions.getColumns().addAll(colContent, colCate);
    }
    
}
