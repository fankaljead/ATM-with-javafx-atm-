package beta1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMMachine1{	

	public static boolean idExist(Account1 temp, Account1[] a){
		for(int i = 0; i < a.length; i++){
			
			if(temp.getId() == a[i].getId()){//�ж��Ƿ�������id
				
				return true;
			}
		}
		return false;
	}
	
	//��ѯbalance
	public static boolean checkBalance(Account1 temp, Account1[] a){
		
		for(int i = 0; i < a.length; i++){
			if(temp.getId() == a[i].getId()){//�ж��Ƿ�������id
				System.out.println("Balance:" + a[i].getBalance());
				return true;
			}
		}
		return false;
	}
	
	//ȡǮ
	public static boolean mWithDraw(Account1 temp, Account1[] a, Scanner input) throws FileNotFoundException{
		
		System.out.print("Enter draw money:");
		try{//�ж������Ƿ�ƥ��
			int drawMoney;
			try{
				String drawMoneyString = input.nextLine();//�����ȡǮ��
				drawMoney = Integer.valueOf(drawMoneyString);
			}catch(NumberFormatException e){
				return false;
			}
			
			temp.setBalance(a[temp.getId()].getBalance());//��id��balance ��ʱ�����temp��
			
			if(temp.withDraw(drawMoney)){//�ж�ȡǮ�Ƿ�ɹ�
				System.out.println("Draw money succeeded\nYour balance is " + temp.getBalance());
				a[temp.getId()].setBalance(temp.getBalance());//��ȡǮ���balance���´�����id��
				
				//��ȡǮ�����id ��Ϣ���´�ŵ��ļ�
				File idList = new File("idList1.txt");
				PrintWriter output = new PrintWriter(idList);
				for(int i = 0; i < 100; i++){
					output.print(a[i].getId());
					output.print(" ");
					output.println(a[i].getBalance());
				}
				output.close();
				
				return true;
				
			}
			else
				System.out.println("Draw money failed");
			
		}
		catch(InputMismatchException ex){//������벻ƥ�䣬����ʾ�û��������
			System.out.println("Your input is wrong!");
			input.next();
			return false;
		}
		

		return false;
	}
	
	
	
	//��Ǯ
	public static boolean mDeposit(Account1 temp, Account1[] a, Scanner input) throws FileNotFoundException{
		System.out.print("Enter deposit money:");
		try{
			
			int depositMoney;
			try{
				String depositMoneyString = input.nextLine();//�����ȡǮ��
				depositMoney = Integer.valueOf(depositMoneyString);
			}catch(NumberFormatException e){
				return false;
			}
			
			temp.setBalance(a[temp.getId()].getBalance());//��id��balance ��ʱ�����temp��
			
			if(temp.deposit(depositMoney)){//�ж��Ƿ��Ǯ�ɹ�
				
				System.out.println("Deposit money succeeded\nYour balance is " + temp.getBalance());
				a[temp.getId()].setBalance(temp.getBalance());
				
				//����Ǯ��id����Ϣ���´�ŵ��ļ�
				File idList = new File("idList1.txt");
				PrintWriter output = new PrintWriter(idList);
				for(int i = 0; i < 100; i++){
					output.print(a[i].getId());
					output.print(" ");
					output.println(a[i].getBalance());
				}
				output.close();
				
				return true;
			}
			else
				System.out.println("Draw money failed");
		}
		catch(InputMismatchException ex){//�ж������Ƿ�ƥ�䣬������ʾ�û��������
			
			System.out.println("Your input is wrong!");
			input.next();
			return false;
		}
		return false;
	}
	
	
	
	public  void start() throws IOException{
		
		
		//���ļ��ж�ȡ�û�id��balance���������a������
		Account1[] a = new Account1[100];
		File idList = new File("idList1.txt");
		
		Scanner fInput = new Scanner(idList);		
		for(int i = 0; fInput.hasNext(); i++){
			a[i] = new Account1(fInput.nextInt(), fInput.nextDouble());
			
		}
		fInput.close();		
		
		
		Scanner input = new Scanner(System.in);
		
		//��ʱ��¼id
		Account1 temp = new Account1();		
		
		System.out.println("Welcome to Bank");
		System.out.print("Enter id:");
		while(true){
			while(true){
				try{
					temp.setId(input.nextInt());
					break;
				}catch(InputMismatchException e){
					System.out.println("Your input is invalid");
					input.nextLine();
					System.out.print("Enter id:");
				}
			}
			if(idExist(temp, a)){
				input.nextLine();
				//�˵�����ѭ��
				while(true){
					menu(temp, a, input);
					
					}
			}
				
			else{
				System.out.println("Your id does not exist");
				System.out.print("Please enter your id again:");
			}
		}
			
		
		
	}
	
	//�˵�
	public static void menu(Account1 temp,Account1[] a, Scanner input) throws FileNotFoundException{
		
		
		//���˵�
		
		System.out.println("\nMain menu");
		System.out.println("1:check balance\t\t2:withDraw");
		System.out.println("3:deposit\t\t4:exit");		
		
		
		System.out.println("Enter choice:");
		
		char choice = input.nextLine().charAt(0);
		
		
		switch(choice){
		
			case '1':       //��ѯbalance
				if(checkBalance(temp,a) == false)
					System.out.println("Check balance failed");
				menu(temp, a, input);
				break;
				
			case '2':     //ȡǮ
				mWithDraw(temp, a, input);
				break;
				
			case '3':	    //��Ǯ
				mDeposit(temp, a, input);
				break;
				
			case '4':		//�˳�		
				System.exit(0);
//				default:
//					menu(temp, a, input);
			
		}		
	}
}
