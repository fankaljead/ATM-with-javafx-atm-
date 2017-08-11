package beta2;

import java.util.ArrayList;
import java.util.Date;
import beta1.Account1;

public class Account2 extends Account1  {

	private String password;
	private String name;
	
	private ArrayList<Transaction> transactions; // 事务记录
	
	//随机生成的id号
	private static int randomId(ArrayList<Integer> notExistId) throws Exception {
		int len = notExistId.size();
		if (len == 0) {
			throw new Exception("Full of users");
		}

		return notExistId.remove((int) (Math.random() * len));
	}
	
	//查看用户密码
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	//返回用户名字
	public String getName() {
		return name;
	}

	//设置用户名字
	public void setName(String name) {
		this.name = name;
	}

	public Account2(){
		
	}
	
	
	
	
	public Account2(double balance, ArrayList<Integer> notExistId){
		
		try{
			
			if(balance < 0)//当balance输入不合法时，抛出异常
				throw new IllegalArgumentException();
			this.setBalance(balance);
			this.setId(randomId(notExistId));
		}
		catch(IllegalArgumentException ex){
			System.out.println("Balance should be > 0");
		}
		catch(Exception ex){
			return;
		}
	}
	
	
	
	
	public Account2(String name, double balance, String password, double annualInterestRate,
			ArrayList<Integer> notExistId) throws Exception {
		this.name = name;
		setAnnualInterestRate(annualInterestRate);
		if (!checkPasswordIllegal(password)) {
			throw new Exception("Illegal password");
		}
		this.password = password;

		if (balance < 0)
			throw new Exception("Illegal balance");
		setBalance(getBalance() + balance);

		setId(randomId(notExistId));

		transactions = new ArrayList<Transaction>();
	}


	@Override
	public boolean withDraw(double drawMoney) {
		if (isOverWithDraw(drawMoney)) {
			System.out.println("Warning over 5000");
			return false;
		}
		if(drawMoney % 100 != 0){
			System.out.println("Please enter money as 100 times");
			return false;
		}
		super.withDraw(drawMoney);
		transactions.add(new Transaction('W', drawMoney, getBalance()));//向线性表里添加一笔取钱记录
		return true;
	}


	//重写deposit方法，并向Transaction加入一笔存钱交易记录
	@Override
	public boolean deposit(double depositMoney){
		
		super.deposit(depositMoney);//调用父类deposit方法，存钱
		transactions.add(new Transaction('D', depositMoney, super.getBalance()));//向线性表里添加一笔存钱记录

		return true;
	}
	
	//修改用户密码,需要验证用户两次输入的密码是否相同,并且不能和原密码相同
	public boolean changePassword(String password){
		if(checkPasswordIllegal(password)//检查password是否合法
			&&	 !password.equals(this.password)
				){//检查password是否与原密码相同

			this.password = password;
			return true;
		}
		return false;
	}
	
	//检查password是否只含数字和字母，长度是否大于6并且小于10
	public boolean checkPasswordIllegal(String password){
		
		password = password.toLowerCase();
		
		//判断password的长度是否大于6并且小于10
		if(password.length() < 6 || password.length() > 10)
			return false;
		
		//判断password是否只有数字与字母构成
		else if(password.matches("[a-z0-9]*"))//正则表达式
			return true;//password格式正确
		
		return true;

	}
	
	//判断当日取钱是否超过5000
	private boolean isOverWithDraw(double money) {
		double amount = 0;
		Date newDate = new Date();
		for (int i = transactions.size() - 1; i >= 0; i--) {
			Transaction temp = transactions.get(i);

			if (!temp.equalDate(newDate))
				break;

			if (temp.getType() == 'W') {
				amount += temp.getAmount();
				if (amount >= 5000) {
					return true;
				}
			}
		}

		return money + amount > 5000;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

}
