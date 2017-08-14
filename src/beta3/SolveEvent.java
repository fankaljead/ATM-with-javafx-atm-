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
	
	//��¼�¼�
	public void loginEvent() {
		account = atmMachine.accounts.get(Integer.valueOf(atmui.textField.getText()));
		if(account == null){
			f_alert_informationDialog("����", atmui.textField.getText() + "�û�������!", AlertType.INFORMATION);
			
		}
		else if(!account.getPassword().equals(atmui.pswdF.getText())){
			f_alert_informationDialog("����", "�������!", AlertType.INFORMATION);
			
		}
		else{
			f_alert_informationDialog("��ӭ", "��ӭ�ص��������!", AlertType.INFORMATION);
			clearText();
			atmui.menuButton.setMenuButton();
			
		}
	}
	
	//���������¼�
	public void changePasswordEvent() {

		atmui.setPasswordText();
		atmui.pField2.setPromptText("����������");
		atmui.pswdF.setPromptText("���ٴ���������");
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd,
				atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btDepositMoney
				,atmui.menuButton.btCheckBalance);
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(!atmui.pField2.getText().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("����", "����ǰ��һ��", AlertType.INFORMATION);
			}
			else if(atmui.pField2.equals(account.getPassword())){
				f_alert_informationDialog("����", "���벻����ԭ������ͬ", AlertType.INFORMATION);
			}
			else if(account.changePassword(atmui.pField2.getText())){
				f_alert_informationDialog("�ɹ�", "�����޸ĳɹ�", AlertType.INFORMATION);
			}
			else {
				f_alert_informationDialog("����", "�������벻�Ϸ�", AlertType.INFORMATION);
			}
		});
		
	}
	
	//ע���¼�
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
				
				f_alert_informationDialog("ע��ɹ�", "����IDΪ:" + account.getId(), AlertType.INFORMATION);
				atmMachine.accounts.put(account.getId(), account);
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		});
		atmui.menuButton.btRegisterBack.setOnMouseClicked(e -> {
			atmui.setRegisterA();
		});
					
	}
	
	//�鿴�˻�����¼�
	public void checkBalanceEvent() {
		atmui.setNormalText();
		f_alert_informationDialog("��ѯ���", "�������Ϊ: " + account.getBalance(), AlertType.INFORMATION);
	}
	
	//ȡǮ�¼�
	public void drawMoneyEvent() {
		atmui.setNormalText();
		atmui.textField.setPromptText("������ȡ����");
		atmui.pswdF.setPromptText("���ٴ���������");
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd,
				atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btDepositMoney
				,atmui.menuButton.btCheckBalance);
		
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(account.withDraw(Integer.valueOf(atmui.textField.getText()))){
				f_alert_informationDialog("ȡ��ɹ�", "���ĵ�ǰ���Ϊ: " + account.getBalance(), AlertType.INFORMATION);
			}
			else if(!account.getPassword().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("����", atmui.pswdF.getText() + "�������!", AlertType.INFORMATION);
				
			}
			else{
				f_alert_informationDialog("����", "�����������, ����������! ", AlertType.INFORMATION);
			}
		});
		
	}
	
	//��Ǯ�¼�
	public void depositMoneyEvent() {
		atmui.setNormalText();	
		atmui.textField.setPromptText("����������");
		atmui.pswdF.setPromptText("���ٴ���������");
		
		atmui.menuButton.leftButtonVbox.getChildren().removeAll(atmui.menuButton.btChangePswd, atmui.menuButton.btDrawMoney);
		atmui.menuButton.rightButtonVbox.getChildren().removeAll(atmui.menuButton.btCheckTransaction, atmui.menuButton.btCheckBalance
				, atmui.menuButton.btDepositMoney);
		
		atmui.menuButton.btEnter.setOnAction(EventHandler -> {
			if(account.deposit(Integer.valueOf(atmui.textField.getText()))){
				f_alert_informationDialog("���ɹ�", "���ĵ�ǰ���Ϊ: " + account.getBalance(), AlertType.INFORMATION);
			}
			else if(!account.getPassword().equals(atmui.pswdF.getText())){
				f_alert_informationDialog("����", atmui.pswdF.getText() + "�������!", AlertType.INFORMATION);
				
			}
			else{
				f_alert_informationDialog("����", "�����������, ����������! ", AlertType.INFORMATION);
			}
			
		});
			
	}
	
	//�鿴���׼�¼�¼�
	public void transactionEvent() {
		atmui.setNormalText();
		String temp = new String();
		for(Transaction t : account.getTransactions()){
			temp += t.getDescription();
			temp += "\n\n";
		}
		f_alert_informationDialog("���׼�¼", temp, AlertType.INFORMATION);
	}
	
	//�˳��¼�
	public void btExitEvent(Stage stage) {
		atmMachine.writeObject("account.dat");
		stage.close();
	}
	
	//�������˵��¼�
	public void btBackEvent() {
		atmui.setNormalText();
		atmui.menuButton.leftButtonVbox.getChildren().clear();
		atmui.menuButton.rightButtonVbox.getChildren().clear();
		atmui.menuButton.setMenuButton();
		clearText();
	}
	
	
	
	//���������е�����
	public void clearText() {
		atmui.textField.setText("");
		atmui.pswdF.setText("");
		atmui.textField.setPromptText("");
		atmui.pswdF.setPromptText("");
	}
	
	//ϵͳ��ʾ��
	public void f_alert_informationDialog(String p_header, String p_message, AlertType alertType){
        Alert _alert = new Alert(alertType);
        _alert.setTitle("ϵͳ��ʾ");
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