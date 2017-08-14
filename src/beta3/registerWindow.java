package beta3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import beta2.Account2;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegisterWindow extends Application{
	
	protected ArrayList<Integer> notExistId2 = null;
    protected HashMap<Integer, Account2> accounts2  = null;
	protected ATMMachine3 atmMachine3 = null;
    
	TextField textFieldUserName = new TextField();//用户名输入框
	TextField textFieldUserBalance = new TextField();//用户余额输入框
	TextField textFieldUserAnnualInterestRate = new TextField();//用户年利率输入框
	PasswordField pswdF = new PasswordField();//密码框
	
	private Label labelUserName = new Label("请输入您的名字:"); 
	private Label labelUserBalance = new Label("请输入您的余额:"); 
	private Label labelUserAnnualInterestRate = new Label("请输入您的年利率:"); 
	private Label labelUserPassword = new Label("请输入您的密码:"); 
	
	Button btOk = new Button("确定");
	Button btBack = new Button("返回");
	
	GridPane gridPane = new GridPane();
	
	String[] info = new String[4];
	
	Account2 account = null;
	
	public void setUI() {
		gridPane.add(labelUserName, 0, 0);
		gridPane.add(textFieldUserName, 1, 0);
		
		gridPane.add(labelUserBalance, 0, 1);
		gridPane.add(textFieldUserBalance, 1, 1);
		
		gridPane.add(labelUserAnnualInterestRate, 0, 2);
		gridPane.add(textFieldUserAnnualInterestRate, 1, 2);
		
		gridPane.add(labelUserPassword, 0, 3);
		gridPane.add(pswdF, 1, 3);
		
		gridPane.add(btBack, 0, 4);
		gridPane.add(btOk, 1, 4);
		
		gridPane.setPadding(new Insets(20, 20, 15, 30));
		
	}
	
	public RegisterWindow() {
		readObject("account.dat");
		if(accounts2 == null){
			accounts2 = new HashMap<Integer, Account2>();
		}

		if(notExistId2 == null){
			notExistId2 = new ArrayList<Integer>();
			for(int i = 100000; i <= 999999; i++){
				if(!accounts2.keySet().contains(i))
					notExistId2.add(i);
			}
		}
	}
	
	
	

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setUI();
		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("注册用户");
		primaryStage.show();
		
		btBack.setOnMouseClicked(e -> {
			this.atmMachine3.writeObject("account.dat");
			primaryStage.close();
		});
		btOk.setOnMouseClicked(e -> {
			try {
				account  = new Account2(textFieldUserName.getText(), Double.valueOf(textFieldUserBalance.getText()), 
						pswdF.getText(), Double.valueOf(textFieldUserAnnualInterestRate.getText()), notExistId2);
				
				f_alert_informationDialog("注册成功", "您的ID为:" + account.getId(), AlertType.INFORMATION);
				accounts2.put(account.getId(), account);
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		});
		
		 //监听窗口是否关闭,关闭则写入文件
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
        	@Override
            public void handle(WindowEvent event) {
                
                writeObject("account.dat");
            }
        });
		
	}

//	public static void main(String[] args) {
//		launch(args);
//	}
	
	//系统提示框
	public void f_alert_informationDialog(String p_header, String p_message, AlertType alertType){
        Alert _alert = new Alert(alertType);
        _alert.setTitle("系统提示");
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
        
        _alert.show();
    }
	
	
	//将文件中之前保留的用户映射读入内存
    @SuppressWarnings("unchecked")
    public void readObject(String filename){
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(filename));
            accounts2 = (HashMap<Integer, Account2>)in.readObject();
            in.close();
        } catch (Exception e) {
            accounts2  = new HashMap<>();
        }
    }

	//将ATM机里面的用户映射存入文件中	
    public void writeObject(String filename){
        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(accounts2);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
