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
    NumberKeyboard key = new NumberKeyboard();//数字小键盘
    MenuButton menuButton = new MenuButton();//侧栏菜单

    GridPane gridPane = new GridPane();




    TextField textField = new TextField();//输入框   
	TextField textFieldUserBalance = new TextField();//用户余额输入框
	TextField textFieldUserAnnualInterestRate = new TextField();//用户年利率输入框
    PasswordField pswdF = new PasswordField();//密码框
    PasswordField pField2 = new PasswordField();//确认密码


	public ATMUI(){
		
        this.setUi();//设置ATM的UI
		this.textField.setEditable(false);
		this.textFieldUserBalance.setEditable(false);
		this.textFieldUserAnnualInterestRate.setEditable(false);
		this.pswdF.setEditable(false);
		this.pField2.setEditable(false);
	}

	//设置ATM的UI
    public void setUi(){


    	 pswdF.setOnMouseClicked(e -> {//当选中输出输入的
         	key.setControl(pswdF);
         });
    	 pField2.setOnMouseClicked(e -> {//当选中输出输入的
          	key.setControl(pField2);
          });
         textField.setOnMouseClicked(e -> {//当选中输出输入的
         	
         	key.setControl(textField);
         });
        textField.setPromptText("请输入卡号");//设置文本框的悲剧文字
        pswdF.setPromptText("请输入密码");//设置密码框的背景文字
        pField2.setPromptText("请输入密码");//设置密码框的背景文字

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

        menuButton.setLoginMenu();//默认设为登录界面



        borderPane.setPadding(new Insets(20, 20, 0, 20));
        borderPane.setStyle("-fx-background-color: lightblue");       
       
    
    }
    
    public void setRegisterMenuT() {    	
		gridPane.getChildren().clear();
		textField.setPromptText("请输入您的名字");
		textFieldUserBalance.setPromptText("请输入您的余额");
		textFieldUserAnnualInterestRate.setPromptText("请输入您的年利率");
		
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
		textField.setPromptText("请输入您的名字");
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



