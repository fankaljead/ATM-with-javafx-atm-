package beta3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class NumberKeyboard extends GridPane{//���ּ���
	
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
	
	private TextInputControl control;//�������
	
	//������������
	public void setControl(TextInputControl c){
		this.control = c;
	}
	

	//����ÿ����������״
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
	
	
	//����ÿ������������
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
		
		
		this.setButtonShape(new Ellipse(30, 50));//����Ĭ�ϵİ�����״
		this.setButtonStyle("-fx-font: 22 arial;-fx-base: #b6e7c9; ");//����Ĭ�ϵİ�������
		
		
		//���ð����Ĺ���
		bt0.setOnAction(e -> {//���0
			control.setText(control.getText() + "0");
		});
		bt1.setOnAction(e -> {//���1
			control.setText(control.getText() + "1");
		});
		bt2.setOnAction(e -> {//���2
			control.setText(control.getText() + "2");
		});
		bt3.setOnAction(e -> {//���3
			control.setText(control.getText() + "3");
		});
		bt4.setOnAction(e -> {//���4
			control.setText(control.getText() + "4");
		});
		bt5.setOnAction(e -> {//���5
			control.setText(control.getText() + "5");
		});
		bt6.setOnAction(e -> {//���6
			control.setText(control.getText() + "6");
		});
		bt7.setOnAction(e -> {//���7
			control.setText(control.getText() + "7");
		});
		bt8.setOnAction(e -> {//���8
			control.setText(control.getText() + "8");
		});
		bt9.setOnAction(e -> {//���9
			control.setText(control.getText() + "9");
		});
		btDot.setOnAction(e -> {//���.
			control.setText(control.getText() + ".");
		});		
		btDelete.setOnAction(e -> {//�˸��
			
			if(control.getText().length() > 1)
				control.setText(control.getText().substring(0, control.getText().length() - 1));
			else
				control.setText("");
		});
		
		
		//����С���̵�����
		this.setAlignment(Pos.CENTER);//�������ּ��̾���		
		this.setPadding(new Insets(20,25,25,20));//�����������ڵ�ľ���
		this.setHgap(20);//�������ּ������ҵļ��
		this.setVgap(10);//�������ּ������µļ��
		
		
		//�������ּ�������
		this.add(bt1, 0, 0);//��bt1���ڵ�0�У���0��
		this.add(bt2, 1, 0);//��bt2���ڵ�0�У���1��
		this.add(bt3, 2, 0);//��bt3���ڵ�0�У���2��
		this.add(bt4, 0, 1);//��bt4���ڵ�1�У���0��
		this.add(bt5, 1, 1);//��bt5���ڵ�1�У���1��
		this.add(bt6, 2, 1);//��bt6���ڵ�1�У���2��		
		this.add(bt7, 0, 2);//��bt7���ڵ�2�У���0��
		this.add(bt8, 1, 2);//��bt8���ڵ�2�У���1��
		this.add(bt9, 2, 2);//��bt9���ڵ�2�У���2��
		this.add(btDot, 0, 3);//��btDot���ڵ�3�У���0��
		this.add(bt0, 1, 3);//��bt0���ڵ�3�У���1��
		this.add(btDelete, 2, 3);//��btDelete���ڵ�3�У���2��
		
		
	}
}