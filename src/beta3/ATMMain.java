package beta3;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ATMMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        ATMUI atmui = new ATMUI();
        ATMMachine3 atmMachine = new ATMMachine3();
       // Account2 account2 = new Account2();
        SolveEvent sovleEvent = new SolveEvent(atmui, atmMachine);
        Scene scene = new Scene(atmui.borderPane, 500, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ATM");//���ó�����
        primaryStage.getIcons().add(new Image("AtmIcon.jpg"));//����ͼ��, ͼƬ�ļ�Ӧ��������binĿ¼��
        primaryStage.setResizable(false);//����̨��С��Ϊ���ɱ�
        primaryStage.show();
        
        //��¼�¼�
        atmui.menuButton.btLogin.setOnMouseClicked(e -> {
        	sovleEvent.loginEvent();
        });
        
        //��ѯ���
        atmui.menuButton.btCheckBalance.setOnMouseClicked(e -> {
        	sovleEvent.checkBalanceEvent();
        });
        
        //ȡǮ�¼�
        atmui.menuButton.btDrawMoney.setOnMouseClicked(e -> {
        	sovleEvent.drawMoneyEvent();
        });
        
        //��Ǯ�¼�
        atmui.menuButton.btDepositMoney.setOnMouseClicked(EventHandler -> {
        	sovleEvent.depositMoneyEvent();
        });
        
        //�����¼�
        atmui.menuButton.btBack.setOnMouseClicked(e ->{
        	sovleEvent.btBackEvent();
        });
        
        //��ѯ���׼�¼
        atmui.menuButton.btCheckTransaction.setOnMouseClicked(EventHandler ->{
        	sovleEvent.transactionEvent();
        });
        
        //�˳��¼�
        atmui.menuButton.btExit.setOnMouseClicked(e -> {
        	
        	sovleEvent.btExitEvent(primaryStage);
        });
        
        //ע��
        atmui.menuButton.btRegister.setOnMouseClicked(e -> {
        	sovleEvent.registerEvent();       	
        	
        	
        });
        
        //��������
        atmui.menuButton.btChangePswd.setOnMouseClicked(e -> {
        	sovleEvent.changePasswordEvent();
        	
        });
        //���������Ƿ�ر�,�ر���д���ļ�
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
        	@Override
            public void handle(WindowEvent event) {
                
                atmMachine.writeObject("account.dat");
            }
        });
    }
    public static void main(String[] args) {
		launch(args);
	}
}
