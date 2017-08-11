package beta1;

import java.io.Serializable;
import java.util.Date;

public class Account1 implements Serializable {

	private int id;//�˺ź���
	private double balance;//�˺Ž��
	private double annualInterestRate;//��ǰ����
	Date dateCreated;//��������
	private int currentDayWithdrawMoney;
	
	public Account1(){
		this.id = 0;
		this.balance = 0.00;
		this.annualInterestRate = 0.00;
		this.dateCreated = new Date();
	}

	public Account1(int id, double balance) {
		try{
			if(id < 0 || balance < 0)//��id��balance���벻�Ϸ�ʱ���׳��쳣
				throw new IllegalArgumentException();
			this.id = id;
			this.balance = balance;
			
		}
		catch(IllegalArgumentException ex){
			System.out.println("Wrong input!Id created failed.");
		}
	}

	
	//����������
	public double getMonthlyInterestRate(){
		return this.annualInterestRate * 12;
	}
	
	
	//��ȡ�������
	public boolean withDraw(double drawMoney){
		if(drawMoney <= 0 || drawMoney > this.balance)
			return false;
		else
			this.balance = this.balance - drawMoney;
		return true;
	}
	
	
	//����������
	public boolean deposit(double depositMoney){
		if(depositMoney <= 0 || depositMoney > 10000000)
			return false;
		else
			this.balance = this.balance + depositMoney;
		return true;
	}
	//id�ķ�����
	public int getId() {
		return id;
	}

	//id�Ĺ�����
	public void setId(int id) {
		this.id = id;
	}

	
	//balance�ķ�����
	public double getBalance() {
		return balance;
	}
	
	//balance�ķ�����
	public void setBalance(double balance) {
		this.balance = balance * 1.00;
	}

	
	//annualInterestRate�ķ�����
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	//annualInterestRate�Ĺ�����
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate * 1.00;
	}

	
	//dateCreated�ķ�����
	public Date getDateCreated() {
		return dateCreated;
	}

	public int getCurrentDayWithdrawMoney() {
		return currentDayWithdrawMoney;
	}

	public void setCurrentDayWithdrawMoney(int currentDayWithdrawMoney) {
		this.currentDayWithdrawMoney = currentDayWithdrawMoney;
	}
	

	
	
	
	
}
