package beta3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ATMUI{


    BorderPane borderPane = new BorderPane();

    VBox vbox = new VBox();
    NumberKeyboard key = new NumberKeyboard();//����С����
    MenuButton menuButton = new MenuButton();//�����˵�

    GridPane gridPane = new GridPane();




    TextField textField = new TextField();//�����   
	TextField textFieldUserBalance = new TextField();//�û���������
	TextField textFieldUserAnnualInterestRate = new TextField();//�û������������
    PasswordField pswdF = new PasswordField();//�����
    PasswordField pField2 = new PasswordField();//ȷ������


	public ATMUI(){
		
        this.setUi();//����ATM��UI
		this.textField.setEditable(false);
		this.textFieldUserBalance.setEditable(false);
		this.textFieldUserAnnualInterestRate.setEditable(false);
		this.pswdF.setEditable(false);
		this.pField2.setEditable(false);
	}

	//����ATM��UI
    public void setUi(){


    	 pswdF.setOnMouseClicked(e -> {//��ѡ����������
         	key.setControl(pswdF);
         });
    	 pField2.setOnMouseClicked(e -> {//��ѡ����������
          	key.setControl(pField2);
          });
         textField.setOnMouseClicked(e -> {//��ѡ����������
         	
         	key.setControl(textField);
         });
        textField.setPromptText("�����뿨��");//�����ı���ı�������
        pswdF.setPromptText("����������");//���������ı�������
        pField2.setPromptText("����������");//���������ı�������

        gridPane.addRow(0, textField);
        gridPane.addRow(1,  pswdF);
       
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        vbox.getChildren().addAll(gridPane, key);
        
        pswdF.setAlignment(Pos.BASELINE_CENTER);
        pField2.setAlignment(Pos.BASELINE_CENTER);
        textField.setAlignment(Pos.BASELINE_CENTER);
        textFieldUserBalance.setAlignment(Pos.BASELINE_CENTER);
        textFieldUserAnnualInterestRate.setAlignment(Pos.BASELINE_CENTER);

        borderPane.setLeft(menuButton.leftButtonVbox);
        borderPane.setCenter(vbox);
        borderPane.setRight(menuButton.rightButtonVbox);

        menuButton.setLoginMenu();//Ĭ����Ϊ��¼����



        borderPane.setPadding(new Insets(20, 20, 0, 20));
        borderPane.setStyle("-fx-background-color: lightblue");       
       
    
    }
    
    public void setRegisterMenuT() {    	
		gridPane.getChildren().clear();
		textField.setPromptText("��������������");
		textFieldUserBalance.setPromptText("�������������");
		textFieldUserAnnualInterestRate.setPromptText("����������������");
		
		gridPane.addRow(0, textField);
		gridPane.addRow(1, textFieldUserBalance);
	    gridPane.addRow(2,  textFieldUserAnnualInterestRate);
	    gridPane.addRow(3,  pswdF);
	    menuButton.setRegisterMenu();
	    
//	    menuButton.leftButtonVbox.getChildren().remove(menuButton.btLogin);
//	    menuButton.rightButtonVbox.getChildren().remove(menuButton.btRegister);
//	    
//	    menuButton.leftButtonVbox.getChildren().add(menuButton.btRegisterBack);
//	    menuButton.rightButtonVbox.getChildren().add(menuButton.btRegisterEnter);
	}
    
    public void setRegisterA() {
    	gridPane.getChildren().clear();
		textField.setPromptText("��������������");
		gridPane.addRow(0, textField);
		gridPane.addRow(1,  pswdF);
		menuButton.setLoginMenu();
	}


	public void setPasswordText() {
		gridPane.getChildren().clear();
		gridPane.getChildren().add(0, pField2);
		gridPane.getChildren().add(1, pswdF);
	}
	
	public void setNormalText() {
		gridPane.getChildren().clear();
		gridPane.getChildren().add(0, textField);
		gridPane.getChildren().add(1, pswdF);
	}

}



