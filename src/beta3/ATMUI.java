package beta3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ATMUI{


    BorderPane borderPane = new BorderPane();

    VBox vbox = new VBox();
    NumberKeyboard key = new NumberKeyboard();
    MenuButton menuButton = new MenuButton();

    GridPane gridPane = new GridPane();




    TextField textField = new TextField();
    PasswordField pswdF = new PasswordField();


	public ATMUI(){
		
        this.setUi();//����ATM��UI
		
	}

	//����ATM��UI
    public void setUi(){


        textField.setPromptText("�����뿨��");
        pswdF.setPromptText("����������");

        gridPane.addRow(0, textField);
        gridPane.addRow(1,  pswdF);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        vbox.getChildren().addAll(gridPane, key);
        pswdF.setAlignment(Pos.BASELINE_CENTER);

        textField.setAlignment(Pos.BASELINE_CENTER);

        borderPane.setLeft(menuButton.leftButtonVbox);
        borderPane.setCenter(vbox);
        borderPane.setRight(menuButton.rightButtonVbox);

        menuButton.setLoginMenu();//Ĭ����Ϊ��¼����



        borderPane.setPadding(new Insets(20, 20, 0, 20));
        borderPane.setStyle("-fx-background-image: url('AtmBack.jpg')");
    }

}



