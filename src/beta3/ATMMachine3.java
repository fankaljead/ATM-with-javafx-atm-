package beta3;

import beta2.Account2;
import beta2.Transaction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMMachine3{

    protected Scanner input = new Scanner(System.in);
    protected ArrayList<Integer> notExistId = null;
    protected HashMap<Integer, Account2> accounts  = null;//已经被创建的用户映射(id ----> account)

    public ATMMachine3() {
    	readObject("account.dat");
		
		if(accounts == null){
			accounts = new HashMap<Integer, Account2>();
		}

		if(notExistId == null){
			notExistId = new ArrayList<Integer>();
			for(int i = 100000; i <= 999999; i++){
				if(!accounts.keySet().contains(i))
					notExistId.add(i);
			}
		}
        
    }

    //重写修改密码的方法,返回成功或者失败
    
    public boolean mChangePassword(Account2 temp, String newPswd){
        if(temp.getPassword().equals(newPswd))
            return true;
        else
            return false;
    }

    //重写查看交易信息的方法，返回交易信息的字符串
    public String detailsOfTransaction(Account2 temp) {

        String transactionString = new String();
        for(Transaction t : temp.getTransactions()){
            transactionString += t.getDescription();
        }
        return transactionString;
    }

    //重写存钱的方法,返回成功或者失败
    public boolean mDeposit(Account2 temp, double depositMoney) {
    	try{
    		temp.deposit(depositMoney);
    	}catch(Exception e){
    		return false;
    	}
        return true;
    }

    //查询余额
    public double checkBalance(Account2 temp) {
        return temp.getBalance();
    }

    //建立新账户
    public boolean createAnAccount(String name, double balance, String password, double annualInterestRate) {

        Account2 account = null;
        try {
            account  = new Account2(name, balance, password, annualInterestRate, notExistId);
            accounts.put(account.getId(), account);

        } catch (Exception e) {
           return false;

        }
        return true;
    }
  //将文件中之前保留的用户映射读入内存
    @SuppressWarnings("unchecked")
    public void readObject(String filename){
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(filename));
            accounts = (HashMap<Integer, Account2>)in.readObject();
            in.close();
        } catch (Exception e) {
            accounts  = new HashMap<>();
        }
    }

	//将ATM机里面的用户映射存入文件中	
    public void writeObject(String filename){
        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(accounts);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
