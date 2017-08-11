package beta2;

import java.util.ArrayList;
import java.util.Date;
import beta1.Account1;

public class Account2 extends Account1  {

	private String password;
	private String name;
	
	private ArrayList<Transaction> transactions; // �����¼
	
	//������ɵ�id��
	private static int randomId(ArrayList<Integer> notExistId) throws Exception {
		int len = notExistId.size();
		if (len == 0) {
			throw new Exception("Full of users");
		}

		return notExistId.remove((int) (Math.random() * len));
	}
	
	//�鿴�û�����
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	//�����û�����
	public String getName() {
		return name;
	}

	//�����û�����
	public void setName(String name) {
		this.name = name;
	}

	public Account2(){
		
	}
	
	
	
	
	public Account2(double balance, ArrayList<Integer> notExistId){
		
		try{
			
			if(balance < 0)//��balance���벻�Ϸ�ʱ���׳��쳣
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
		transactions.add(new Transaction('W', drawMoney, getBalance()));//�����Ա������һ��ȡǮ��¼
		return true;
	}


	//��дdeposit����������Transaction����һ�ʴ�Ǯ���׼�¼
	@Override
	public boolean deposit(double depositMoney){
		
		super.deposit(depositMoney);//���ø���deposit��������Ǯ
		transactions.add(new Transaction('D', depositMoney, super.getBalance()));//�����Ա������һ�ʴ�Ǯ��¼

		return true;
	}
	
	//�޸��û�����,��Ҫ��֤�û���������������Ƿ���ͬ,���Ҳ��ܺ�ԭ������ͬ
	public boolean changePassword(String password){
		if(checkPasswordIllegal(password)//���password�Ƿ�Ϸ�
			&&	 !password.equals(this.password)
				){//���password�Ƿ���ԭ������ͬ

			this.password = password;
			return true;
		}
		return false;
	}
	
	//���password�Ƿ�ֻ�����ֺ���ĸ�������Ƿ����6����С��10
	public boolean checkPasswordIllegal(String password){
		
		password = password.toLowerCase();
		
		//�ж�password�ĳ����Ƿ����6����С��10
		if(password.length() < 6 || password.length() > 10)
			return false;
		
		//�ж�password�Ƿ�ֻ����������ĸ����
		else if(password.matches("[a-z0-9]*"))//������ʽ
			return true;//password��ʽ��ȷ
		
		return true;

	}
	
	//�жϵ���ȡǮ�Ƿ񳬹�5000
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
