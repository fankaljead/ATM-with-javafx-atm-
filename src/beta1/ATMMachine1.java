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
			
			if(temp.getId() == a[i].getId()){//判断是否存在这个id
				
				return true;
			}
		}
		return false;
	}
	
	//查询balance
	public static boolean checkBalance(Account1 temp, Account1[] a){
		
		for(int i = 0; i < a.length; i++){
			if(temp.getId() == a[i].getId()){//判断是否存在这个id
				System.out.println("Balance:" + a[i].getBalance());
				return true;
			}
		}
		return false;
	}
	
	//取钱
	public static boolean mWithDraw(Account1 temp, Account1[] a, Scanner input) throws FileNotFoundException{
		
		System.out.print("Enter draw money:");
		try{//判断输入是否匹配
			int drawMoney;
			try{
				String drawMoneyString = input.nextLine();//输入的取钱数
				drawMoney = Integer.valueOf(drawMoneyString);
			}catch(NumberFormatException e){
				return false;
			}
			
			temp.setBalance(a[temp.getId()].getBalance());//将id的balance 临时存放在temp中
			
			if(temp.withDraw(drawMoney)){//判断取钱是否成功
				System.out.println("Draw money succeeded\nYour balance is " + temp.getBalance());
				a[temp.getId()].setBalance(temp.getBalance());//将取钱后的balance重新存回这个id中
				
				//将取钱后这个id 信息重新存放到文件
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
		catch(InputMismatchException ex){//如果输入不匹配，则提示用户输入错误
			System.out.println("Your input is wrong!");
			input.next();
			return false;
		}
		

		return false;
	}
	
	
	
	//存钱
	public static boolean mDeposit(Account1 temp, Account1[] a, Scanner input) throws FileNotFoundException{
		System.out.print("Enter deposit money:");
		try{
			
			int depositMoney;
			try{
				String depositMoneyString = input.nextLine();//输入的取钱数
				depositMoney = Integer.valueOf(depositMoneyString);
			}catch(NumberFormatException e){
				return false;
			}
			
			temp.setBalance(a[temp.getId()].getBalance());//将id的balance 临时存放在temp中
			
			if(temp.deposit(depositMoney)){//判断是否存钱成功
				
				System.out.println("Deposit money succeeded\nYour balance is " + temp.getBalance());
				a[temp.getId()].setBalance(temp.getBalance());
				
				//将存钱后id的信息重新存放到文件
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
		catch(InputMismatchException ex){//判断输入是否匹配，否则提示用户输入错误
			
			System.out.println("Your input is wrong!");
			input.next();
			return false;
		}
		return false;
	}
	
	
	
	public  void start() throws IOException{
		
		
		//从文件中读取用户id和balance，并存放在a数组中
		Account1[] a = new Account1[100];
		File idList = new File("idList1.txt");
		
		Scanner fInput = new Scanner(idList);		
		for(int i = 0; fInput.hasNext(); i++){
			a[i] = new Account1(fInput.nextInt(), fInput.nextDouble());
			
		}
		fInput.close();		
		
		
		Scanner input = new Scanner(System.in);
		
		//临时登录id
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
				//菜单界面循环
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
	
	//菜单
	public static void menu(Account1 temp,Account1[] a, Scanner input) throws FileNotFoundException{
		
		
		//主菜单
		
		System.out.println("\nMain menu");
		System.out.println("1:check balance\t\t2:withDraw");
		System.out.println("3:deposit\t\t4:exit");		
		
		
		System.out.println("Enter choice:");
		
		char choice = input.nextLine().charAt(0);
		
		
		switch(choice){
		
			case '1':       //查询balance
				if(checkBalance(temp,a) == false)
					System.out.println("Check balance failed");
				menu(temp, a, input);
				break;
				
			case '2':     //取钱
				mWithDraw(temp, a, input);
				break;
				
			case '3':	    //存钱
				mDeposit(temp, a, input);
				break;
				
			case '4':		//退出		
				System.exit(0);
//				default:
//					menu(temp, a, input);
			
		}		
	}
}
