package MainEnterForm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainEnterFormGUI extends Application {

    private Stage primaryStage;
    private Pane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }


    public void initRootLayout() {
        try {

            /* В начале программа запускает окно входа,
            * в котором заригестрированные пользователь
            * может ввести свой логин и пароль
            * или перейти в окно регистрации*/

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainEnterFormGUI.class.getResource("EnterForm.fxml"));
            rootLayout = (Pane) loader.load();

            EnterFormController controller = loader.getController();//Контролеер окна логирования

            controller.setEnterFormGUI(this);
            controller.ShowLoginWindow();

            controller.setLoginWindowController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Enter_Form");

        initRootLayout();
    }

}
