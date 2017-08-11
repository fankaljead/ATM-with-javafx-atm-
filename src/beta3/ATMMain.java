package beta3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ATMMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        ATMUI atmui = new ATMUI();
        Scene scene = new Scene(atmui.borderPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ATM");//设置程序名
        primaryStage.getIcons().add(new Image("AtmIcon.jpg"));//设置图标, 图片文件应当放置在bin目录下
        primaryStage.setResizable(false);//将舞台大小设为不可变
        primaryStage.show();
    }
    
}
