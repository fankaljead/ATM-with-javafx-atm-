package beta2;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	  private java.util.Date date;//交易时间
	  private char type;//交易类型    W---->withDraw; D---->deposit
	  private double amount;//交易金额
	  private double balance;//交易后余额
	  private String description;//操作描述
	  
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
	  
	  //根据date到现在的天数判断是否为当天
	  public boolean equalDate(Date newDate){
		  
		  //this.date到现在的天数
		  return this.date.getTime() / 86400000 == newDate.getTime() / 86400000;
		  
	  }
	
	  @Override//返回交易信息		
	  public String toString(){
		 return description;
	  } 	
}


