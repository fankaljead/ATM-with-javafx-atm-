package beta3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class NumberKeyboard extends GridPane{//数字键盘
	
	protected Button bt0 = new Button(" 0 ");
	protected Button bt1 = new Button(" 1 ");
	protected Button bt2 = new Button(" 2 ");
	protected Button bt3 = new Button(" 3 ");
	protected Button bt4 = new Button(" 4 ");
	protected Button bt5 = new Button(" 5 ");
	protected Button bt6 = new Button(" 6 ");
	protected Button bt7 = new Button(" 7 ");
	protected Button bt8 = new Button(" 8 ");
	protected Button bt9 = new Button(" 9 ");	
	protected Button btDot = new Button("  . ");
	protected Button btDelete = new Button("Del");
	
	private TextInputControl control;//输入对象
	
	//设置输入区域
	public void setControl(TextInputControl c){
		this.control = c;
	}
	

	//设置每个按键的形状
	public void setButtonShape(Shape s){
		bt0.setShape(s);
		bt1.setShape(s);
		bt2.setShape(s);
		bt3.setShape(s);
		bt4.setShape(s);
		bt5.setShape(s);
		bt6.setShape(s);
		bt7.setShape(s);
		bt8.setShape(s);
		bt9.setShape(s);
		bt0.setShape(s);
		btDot.setShape(s);
		btDelete.setShape(s);
	}
	
	
	//设置每个按键的属性
	public void setButtonStyle(String s){
		bt0.setStyle(s);
		bt1.setStyle(s);
		bt2.setStyle(s);
		bt3.setStyle(s);
		bt4.setStyle(s);
		bt5.setStyle(s);
		bt6.setStyle(s);
		bt7.setStyle(s);
		bt8.setStyle(s);
		bt9.setStyle(s);
		bt0.setStyle(s);
		btDot.setStyle(s);
		btDelete.setStyle(s);
	}
	
	public NumberKeyboard(){
		
		
		this.setButtonShape(new Ellipse(30, 50));//设置默认的按键形状
		this.setButtonStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");//设置默认的按键属性
		
		
		//设置按键的功能
		bt0.setOnAction(e -> {//输出0
			control.setText(control.getText() + "0");
		});
		bt1.setOnAction(e -> {//输出1
			control.setText(control.getText() + "1");
		});
		bt2.setOnAction(e -> {//输出2
			control.setText(control.getText() + "2");
		});
		bt3.setOnAction(e -> {//输出3
			control.setText(control.getText() + "3");
		});
		bt4.setOnAction(e -> {//输出4
			control.setText(control.getText() + "4");
		});
		bt5.setOnAction(e -> {//输出5
			control.setText(control.getText() + "5");
		});
		bt6.setOnAction(e -> {//输出6
			control.setText(control.getText() + "6");
		});
		bt7.setOnAction(e -> {//输出7
			control.setText(control.getText() + "7");
		});
		bt8.setOnAction(e -> {//输出8
			control.setText(control.getText() + "8");
		});
		bt9.setOnAction(e -> {//输出9
			control.setText(control.getText() + "9");
		});
		btDot.setOnAction(e -> {//输出.
			control.setText(control.getText() + ".");
		});		
		btDelete.setOnAction(e -> {//退格键
			
			if(control.getText().length() > 1)
				control.setText(control.getText().substring(0, control.getText().length() - 1));
			else
				control.setText("");
		});
		
		
		//设置小键盘的属性
		this.setAlignment(Pos.CENTER);//设置数字键盘居中		
		this.setPadding(new Insets(20,25,25,20));//设置与其他节点的距离
		this.setHgap(20);//设置数字键盘左右的间距
		this.setVgap(10);//设置数字键盘上下的间距
		
		
		//设置数字键盘排列
		this.add(bt1, 0, 0);//将bt1放在第0行，第0列
		this.add(bt2, 1, 0);//将bt2放在第0行，第1列
		this.add(bt3, 2, 0);//将bt3放在第0行，第2列
		this.add(bt4, 0, 1);//将bt4放在第1行，第0列
		this.add(bt5, 1, 1);//将bt5放在第1行，第1列
		this.add(bt6, 2, 1);//将bt6放在第1行，第2列		
		this.add(bt7, 0, 2);//将bt7放在第2行，第0列
		this.add(bt8, 1, 2);//将bt8放在第2行，第1列
		this.add(bt9, 2, 2);//将bt9放在第2行，第2列
		this.add(btDot, 0, 3);//将btDot放在第3行，第0列
		this.add(bt0, 1, 3);//将bt0放在第3行，第1列
		this.add(btDelete, 2, 3);//将btDelete放在第3行，第2列
		
		
	}
}