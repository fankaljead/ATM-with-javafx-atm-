package beta3;

import beta2.ATMMachine2;
import beta2.Account2;
import beta2.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMMachine3 extends ATMMachine2{

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Integer> notExistId = null;
    private static HashMap<Integer, Account2> accounts  = null;//已经被创建的用户映射(id ----> account)

    public ATMMachine3() {
        super();
    }

    //重写修改密码的方法,返回成功或者失败
    private static boolean mChangePassword(Account2 temp, String newPswd){
        if(temp.getPassword().equals(newPswd))
            return true;
        else
            return false;
    }

    //重写查看交易信息的方法，返回交易信息的字符串
    private static String detailsOfTransaction(Account2 temp) {

        String transactionString = new String();
        for(Transaction t : temp.getTransactions()){
            transactionString += t.getDescription();
        }
        return transactionString;
    }

    //重写存钱的方法,返回成功或者失败
    private static boolean mDeposit(Account2 temp, double depositMoney) {
        return true;
    }

    //查询余额
    private static double checkBalance(Account2 temp) {
        return temp.getBalance();
    }

    //建立新账户
    private static boolean createAnAccount(String name, double balance, String password, double annualInterestRate) {

        Account2 account = null;
        try {
            account  = new Account2(name, balance, password, annualInterestRate, notExistId);
            accounts.put(account.getId(), account);

        } catch (Exception e) {
           return false;

        }
        return true;
    }

}
