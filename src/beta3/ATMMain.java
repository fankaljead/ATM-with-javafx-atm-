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
        primaryStage.setTitle("ATM");//���ó�����
        primaryStage.getIcons().add(new Image("AtmIcon.jpg"));//����ͼ��, ͼƬ�ļ�Ӧ��������binĿ¼��
        primaryStage.setResizable(false);//����̨��С��Ϊ���ɱ�
        primaryStage.show();
    }
    
}
