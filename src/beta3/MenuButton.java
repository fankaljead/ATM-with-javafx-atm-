package beta3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuButton{
    protected Button btDrawMoney = new Button("取钱");
    protected Button btChangePswd = new Button("更改密码");
    protected Button btBack = new Button("返回");
    protected Button btExit = new Button("退出");

    protected Button btDepositMoney = new Button("存钱");
    protected Button btCheckBalance = new Button("余额");
    protected Button btCheckTransaction = new Button("查看记录");
    protected Button btEnter = new Button("确认");


    protected Button btLogin = new Button("登录");
    protected Button btRegister = new Button("注册");

    protected Button btRegisterEnter = new Button("确定");
    protected Button btRegisterBack = new Button("返回");

    protected VBox leftButtonVboxOut = new VBox();
    protected VBox rightButtonVboxOut = new VBox();
    protected VBox leftButtonVbox = new VBox();
    protected VBox rightButtonVbox = new VBox();


    public MenuButton(){

    }


    //设置每个按钮的长和高
    public void setButtonSize(double height, double width){

        this.btDrawMoney.setPrefSize(height, width);
        this.btChangePswd.setPrefSize(height, width);
        this.btBack.setPrefSize(height, width);
        this.btExit.setPrefSize(height, width);

        this.btDepositMoney.setPrefSize(height, width);
        this.btCheckBalance.setPrefSize(height, width);
        this.btCheckTransaction.setPrefSize(height, width);
        this.btEnter.setPrefSize(height, width);

        this.btLogin.setPrefSize(height, width);
        this.btRegister.setPrefSize(height, width);
        
        this.btRegisterEnter.setPrefSize(height, width);
        this.btRegisterBack.setPrefSize(height, width);
    }

    //设置主界面的样式
    public void setMenuButton(){

        this.leftButtonVbox.getChildren().clear();//清除之前的节点
        this.rightButtonVbox.getChildren().clear();

        this.leftButtonVbox.getChildren().addAll(btDrawMoney, btChangePswd, btBack, btExit);
        this.rightButtonVbox.getChildren().addAll(btDepositMoney, btCheckBalance, btCheckTransaction, btEnter);

        this.leftButtonVbox.setSpacing(40);
        this.rightButtonVbox.setSpacing(40);
        this.leftButtonVbox.setPadding(new Insets(2,2,2,2));
        this.rightButtonVbox.setPadding(new Insets(2,2,2,2));

        this.setButtonSize(100, 50);//设置按钮大小为100 * 50
    }

    //设置登录界面的样式
    public void setLoginMenu(){

        this.leftButtonVbox.getChildren().clear();//清除之前的节点
        this.rightButtonVbox.getChildren().clear();

        this.leftButtonVbox.getChildren().addAll(btLogin);
        this.rightButtonVbox.getChildren().addAll(btRegister, btExit);

        this.leftButtonVbox.setSpacing(80);
        this.leftButtonVbox.setPadding(new Insets(80,2,80,2));
        this.rightButtonVbox.setPadding(new Insets(80,2,80,2));
        this.rightButtonVbox.setSpacing(80);

        this.setButtonSize(100, 50);//设置按钮大小为100 * 50
    }
    
    public void setRegisterMenu() {    	
		
	    
	    this.leftButtonVbox.getChildren().clear();
	    this.rightButtonVbox.getChildren().remove(btRegister);
	    
	    this.leftButtonVbox.getChildren().add(btRegisterBack);
	    this.rightButtonVbox.getChildren().add(btRegisterEnter);
	    
	    this.leftButtonVbox.setSpacing(80);
        this.leftButtonVbox.setPadding(new Insets(80,2,80,2));
        this.rightButtonVbox.setPadding(new Insets(80,2,80,2));
        this.rightButtonVbox.setSpacing(80);

        this.setButtonSize(100, 50);//设置按钮大小为100 * 50
	}

    public void setRegisterBack() {
    	
	}

}