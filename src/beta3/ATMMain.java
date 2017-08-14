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
        primaryStage.setTitle("ATM");//设置程序名
        primaryStage.getIcons().add(new Image("AtmIcon.jpg"));//设置图标, 图片文件应当放置在bin目录下
        primaryStage.setResizable(false);//将舞台大小设为不可变
        primaryStage.show();
        
        //登录事件
        atmui.menuButton.btLogin.setOnMouseClicked(e -> {
        	sovleEvent.loginEvent();
        });
        
        //查询余额
        atmui.menuButton.btCheckBalance.setOnMouseClicked(e -> {
        	sovleEvent.checkBalanceEvent();
        });
        
        //取钱事件
        atmui.menuButton.btDrawMoney.setOnMouseClicked(e -> {
        	sovleEvent.drawMoneyEvent();
        });
        
        //存钱事件
        atmui.menuButton.btDepositMoney.setOnMouseClicked(EventHandler -> {
        	sovleEvent.depositMoneyEvent();
        });
        
        //返回事件
        atmui.menuButton.btBack.setOnMouseClicked(e ->{
        	sovleEvent.btBackEvent();
        });
        
        //查询交易记录
        atmui.menuButton.btCheckTransaction.setOnMouseClicked(EventHandler ->{
        	sovleEvent.transactionEvent();
        });
        
        //退出事件
        atmui.menuButton.btExit.setOnMouseClicked(e -> {
        	
        	sovleEvent.btExitEvent(primaryStage);
        });
        
        //注册
        atmui.menuButton.btRegister.setOnMouseClicked(e -> {
        	sovleEvent.registerEvent();       	
        	
        	
        });
        
        //更改密码
        atmui.menuButton.btChangePswd.setOnMouseClicked(e -> {
        	sovleEvent.changePasswordEvent();
        	
        });
        //监听窗口是否关闭,关闭则写入文件
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
