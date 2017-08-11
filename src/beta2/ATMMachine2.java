package beta2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMMachine2 {
	
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Integer> notExistId = null;
	private static HashMap<Integer, Account2> accounts  = null;//�Ѿ����������û�ӳ��(id ----> account)
	
	public ATMMachine2(){
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
	
	
	
	public void start() throws Exception{
		

		System.out.println("Welcome to Bank");

		while (true){
            System.out.println("Enter 1 for Login ");
            System.out.println("Enter 2 for register");
            System.out.print("\nEnter your choice:");
            String choiceStr = input.next();
            int choice;
            try{
                choice = Integer.valueOf(choiceStr);
            }catch (Exception e){
                System.out.println("Please enter correctly!");
                continue;
            }
            if(choice == 1)
                break;
            else if(choice == 2)
                createAnAccount();
            else
                System.out.println("Please enter correctly!");
		}

		System.out.print("Enter id:");
		while(true){
		    String tempIdStr = input.next();
			int tempId = Integer.valueOf(tempIdStr);

        Account2 temp = accounts.get(tempId);

		if(temp != null){//�ж��˻��Ƿ����
//			Account2 temp = new Account2("Tom", 100000, "000000", 10, notExistId);

             temp = accounts.get(tempId);
//              System.out.println(temp.getPassword());
			System.out.print("Enter password:");
	//178532
				enterPswd(temp);

				System.out.println("Welcome");
				//�˵�����ѭ��
				while(true){
					try {
						menu(temp);
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("Error");
					}
				}
			}
			else{//�˻�������
				System.out.println("Your id does not exist");
				System.out.print("Please enter your id again:");
			}
		}
	}

	

	//�˵�
	public static void menu(Account2 temp) throws FileNotFoundException{//�û���¼��Ľ���



        if(accounts == null){
            accounts = new HashMap<Integer, Account2>();
        }
		//���˵�
		System.out.println("\nMain menu");
		System.out.println("0:create an account");
		System.out.println("1:check balance");		
		System.out.println("2:withdraw");	
		System.out.println("3:deposit");	
		System.out.println("4:details of transaction");	
		System.out.println("5:change password");
		System.out.println("6:exit");	
		
		
		System.out.print("Enter choice: ");
		
		int choice = input.nextInt();
		
		
		switch(choice){
		
			case 0:       //�������˻�
				createAnAccount();
                    System.out.println("Create an account succeeded");



				menu(temp);
				break;
			
			case 1:       //��ѯbalance
				if(!checkBalance(temp))
					System.out.println("Check balance failed");
				menu(temp);
				break;
				
			case 2:     //ȡǮ
				mWithDraw(temp);
				break;
				
			case 3:	    //��Ǯ
				mDeposit(temp);
				break;
			case 4:	    //���׼�¼
				detailsOfTransaction(temp);
				break;
				
			case 5:	    //������
				mChangePassword(temp);
				break;				
				
			case 6:		//�˳�		
				writeObject("account.dat");

				System.exit(0);
			
		}		
	}

	//������ѡ��
	private static void mChangePassword(Account2 temp) {
		System.out.print("Please enter the former password:");
		enterPswd(temp);//�ж����������Ƿ���ȷ
		
		System.out.print("Please enter the new password:");
		String newPswd1 = input.next();

		if(!newPswd1.equals(temp.getPassword())){
			
			
			System.out.print("Please enter the new password again:");


			String newPswd2 = input.next();
			
			
			if(temp.changePassword(newPswd2) && newPswd1.equals(newPswd2)){
				System.out.println("Change password succeeded");
			}
		}
		
	}

	//�鿴������Ϣ
	private static void detailsOfTransaction(Account2 temp) {
		for(Transaction t : temp.getTransactions()){
			System.out.println(t.getDescription());
		}
		
	}

	
	//��Ǯ
	private static void mDeposit(Account2 temp) {
		System.out.println("Please enter deposit money:");
		double money = input.nextDouble();
		temp.deposit(money);
		System.out.println("Your balance is " + temp.getBalance());
		
	}

	//ȡǮ�˵�
	private static void mWithDraw(Account2 temp) {
		System.out.print("Please enter withdraw money:");
		int drawMoney = input.nextInt();
		if(temp.withDraw(drawMoney))
			System.out.println("Draw money succeeded.\nYour balance is " + temp.getBalance());
		else
			System.out.println("Draw money failed");
		
	}

	//��ѯ���
	private static boolean checkBalance(Account2 temp) {
		System.out.println("Balance:" + temp.getBalance());
		return true;
	}

	//�������˻�
	private static void createAnAccount() {
		System.out.println("Please enter the password");
		String pswd = new String(input.next());
		
		System.out.println("Please enter the balance");
		double balance = input.nextDouble();
		
		System.out.println("Please enter the annualInterestRate");
		double annualInterestRate = input.nextDouble();
		
		System.out.println("Please enter your name");
		String name = new String(input.next());
		
		Account2 account = null;
		try {
			account  = new Account2(name, balance, pswd, annualInterestRate, notExistId);
            accounts.put(account.getId(), account);
			System.out.printf("Please remember your id number %06d\n", account.getId());

		} catch (Exception e) {
			e.printStackTrace();

		}


	}
	
	//��������
	public static void enterPswd(Account2 temp){
//        input.next();
		while(true){
//			Console con = System.console();//��������
//	        String tempPassword = new String(con.readPassword());
			String tempPassword = new String(input.next());
//			System.out.println(tempPassword);
//			System.out.println(temp.getId());
			if(temp.getPassword().equals(tempPassword)){//�ж������Ƿ�������ȷ
//				System.out.println(temp.getPassword().equals(tempPassword));
				break;
			}
			
			System.out.print("Your password is wrong\n"
					+ "Please enter the password again:");//����������󣬲���������
			
		}
	}
	
		//���ļ���֮ǰ�������û�ӳ������ڴ�
        @SuppressWarnings("unchecked")
        public static void readObject(String filename){
            try {
                ObjectInputStream in =
                        new ObjectInputStream(new FileInputStream(filename));
                accounts = (HashMap<Integer, Account2>)in.readObject();
                in.close();
            } catch (Exception e) {
                accounts  = new HashMap<>();
            }
        }
	
		//��ATM��������û�ӳ������ļ���	
        public static void writeObject(String filename){
            try {

                ObjectOutputStream out =
                        new ObjectOutputStream(new FileOutputStream(filename));
                out.writeObject(accounts);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		public static void main(String[] args) throws Exception{


		new ATMMachine2().start();

	}
}
