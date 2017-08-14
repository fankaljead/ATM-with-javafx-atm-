package beta3;

import java.util.ArrayList;
import java.util.HashMap;

import beta2.Account2;
import beta2.Transaction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SolveEvent {
	protected ArrayList<Integer> notExistId2 = null;
	private ATMUI atmui;
	private ATMMachine3 atmMachine;
	private Account2 account;
    
	//980250
	public SolveEvent(ATMUI atmui, ATMMachine3 atmMachine){
		this.atmui = atmui;
		this.atmMachine = atmMachine;
		//this.account = account;
	}
	
	//登录事件
	public void loginEvent() {
		account = atmMachine.accounts.get(Integer.valueOf(atmui.textField.getText()));
		if(account == null){
			f_alert_informationDialog("错误", atmui.textField.getText() + "用户不存在!", AlertType.INFORMATION);
			
		}
		else if(!account.getPassword().equals(atmui.pswdF.getText())){
			f_alert_informationDialog("错误", "密码错误!", AlertType.INFORMATION);
			
		}
		else{
			f_alert_informationDialog("欢迎", "欢迎回到大地银行!", AlertType.INFORMATION);
			clearText();
			atmui.menuButton.setMenuButton();
			
		}
	}
	
	//更改密码事件
	public void changePasswordEvent() {

		atmui.setPasswordText();
		atmui.pField2.setPromptText("请输入密码");
		atmui.pswdF.setPromptText("请再次输入密码");
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd,
				atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btDepositMoney
				,atmui.menuButton.btCheckBalance);
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(!atmui.pField2.getText().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("错误", "密码前后不一致", AlertType.INFORMATION);
			}
			else if(atmui.pField2.equals(account.getPassword())){
				f_alert_informationDialog("错误", "密码不能与原密码相同", AlertType.INFORMATION);
			}
			else if(account.changePassword(atmui.pField2.getText())){
				f_alert_informationDialog("成功", "密码修改成功", AlertType.INFORMATION);
			}
			else {
				f_alert_informationDialog("错误", "密码输入不合法", AlertType.INFORMATION);
			}
		});
		
	}
	
	//注册事件
	public void registerEvent(){

//		RegisterWindow registerWindow = new RegisterWindow();
//		
//		try {
//			registerWindow.start(new Stage());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		atmui.setRegisterMenuT();
		atmui.menuButton.btRegisterEnter.setOnMouseClicked(e -> {
			try {
				account  = new Account2(atmui.textField.getText(), Double.valueOf(atmui.textFieldUserBalance.getText()), 
						atmui.pswdF.getText(), Double.valueOf(atmui.textFieldUserAnnualInterestRate.getText()), atmMachine.notExistId);
				
				f_alert_informationDialog("注册成功", "您的ID为:" + account.getId(), AlertType.INFORMATION);
				atmMachine.accounts.put(account.getId(), account);
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		});
		atmui.menuButton.btRegisterBack.setOnMouseClicked(e -> {
			atmui.setRegisterA();
		});
					
	}
	
	//查看账户余额事件
	public void checkBalanceEvent() {
		atmui.setNormalText();
		f_alert_informationDialog("查询余额", "您的余额为: " + account.getBalance(), AlertType.INFORMATION);
	}
	
	//取钱事件
	public void drawMoneyEvent() {
		atmui.setNormalText();
		atmui.textField.setPromptText("请输入取款金额");
		atmui.pswdF.setPromptText("请再次输入密码");
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd,
				atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btDepositMoney
				,atmui.menuButton.btCheckBalance);
		
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(account.withDraw(Integer.valueOf(atmui.textField.getText()))){
				f_alert_informationDialog("取款成功", "您的当前余额为: " + account.getBalance(), AlertType.INFORMATION);
			}
			else if(!account.getPassword().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("错误", atmui.pswdF.getText() + "密码错误!", AlertType.INFORMATION);
				
			}
			else{
				f_alert_informationDialog("错误", "你的输入有误, 请重新输入! ", AlertType.INFORMATION);
			}
		});
		
	}
	
	//存钱事件
	public void depositMoneyEvent() {
		atmui.setNormalText();	
		atmui.textField.setPromptText("请输入存款金额");
		atmui.pswdF.setPromptText("请再次输入密码");
		
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd, atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btCheckBalance
				, atmui.menuButton.btDepositMoney);
		
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(account.deposit(Integer.valueOf(atmui.textField.getText()))){
				f_alert_informationDialog("存款成功", "您的当前余额为: " + account.getBalance(), AlertType.INFORMATION);
			}
			else if(!account.getPassword().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("错误", atmui.pswdF.getText() + "密码错误!", AlertType.INFORMATION);
				
			}
			else{
				f_alert_informationDialog("错误", "你的输入有误, 请重新输入! ", AlertType.INFORMATION);
			}
			
		});
			
	}
	
	//查看交易记录事件
	public void transactionEvent() {
		atmui.setNormalText();
		String temp = new String();
		for(Transaction t : account.getTransactions()){
			temp += t.getDescription();
			temp += "\n\n";
		}
		f_alert_informationDialog("交易记录", temp, AlertType.INFORMATION);
	}
	
	//退出事件
	public void btExitEvent(Stage stage) {
		atmMachine.writeObject("account.dat");
		stage.close();
	}
	
	//返回主菜单事件
	public void btBackEvent() {
		atmui.setNormalText();
		atmui.menuButton.leftButtonVbox.getChildren().clear();
		atmui.menuButton.rightButtonVbox.getChildren().clear();
		atmui.menuButton.setMenuButton();
		clearText();
	}
	
	
	
	//清除输入框中的文字
	public void clearText() {
		atmui.textField.setText("");
		atmui.pswdF.setText("");
		atmui.textField.setPromptText("");
		atmui.pswdF.setPromptText("");
	}
	
	//系统提示框
	public void f_alert_informationDialog(String p_header, String p_message, AlertType alertType){
        Alert _alert = new Alert(alertType);
        _alert.setTitle("系统提示");
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
        
        _alert.show();
    }
	public void createId() {
		atmMachine.readObject("account.dat");
		if(atmMachine.accounts == null){
			atmMachine.accounts = new HashMap<Integer, Account2>();
		}

		if(notExistId2 == null){
			notExistId2 = new ArrayList<Integer>();
			for(int i = 100000; i <= 999999; i++){
				if(!atmMachine.accounts.keySet().contains(i))
					notExistId2.add(i);
			}
		}
	}
}