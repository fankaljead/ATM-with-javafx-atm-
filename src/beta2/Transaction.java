package beta2;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	  private java.util.Date date;//����ʱ��
	  private char type;//��������    W---->withDraw; D---->deposit
	  private double amount;//���׽��
	  private double balance;//���׺����
	  private String description;//��������
	  
	  public Transaction(char type, double amount, double balance) {
	    date = new java.util.Date();
	    this.type = type;
	    this.amount = amount;
	    this.balance = balance;
	    this.description = date.toString() + " " 
                + (type == 'W' ? "Withdraw:" : "Deosit:")
                + amount + " Your current balance :" + balance;
	  }

	  public java.util.Date getDate() {
	    return date;
	  }
	  
	  public char getType() {
	    return type;
	  }

	  public double getAmount() {
	    return amount;
	  }
	  
	  public double getBalance() {
	    return balance;
	  }

	  public String getDescription() {
			return description;
	 }
	  
	  //����date�����ڵ������ж��Ƿ�Ϊ����
	  public boolean equalDate(Date newDate){
		  
		  //this.date�����ڵ�����
		  return this.date.getTime() / 86400000 == newDate.getTime() / 86400000;
		  
	  }
	
	  @Override//���ؽ�����Ϣ		
	  public String toString(){
		 return description;
	  } 	
}


