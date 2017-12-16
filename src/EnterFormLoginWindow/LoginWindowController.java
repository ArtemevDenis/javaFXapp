package EnterFormLoginWindow;

import EnterFormRegWindow.RegWindowController;
import MainEnterForm.EnterFormController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import EnterFormLoginWindow.LoginWindowController;
import javafx.stage.Stage;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginWindowController {

    @FXML
    public Button buttonLWLog;

    @FXML
    public Button buttonLWReg;

    @FXML
    private Pane paneLoginWindow;

    @FXML
    private Button buttonLWCancel;

    @FXML
    private TextField textFieldLog;

    @FXML
    private PasswordField passField;

    @FXML
    private Label labelState;

    public ArrayList<String> arrayListPass = new ArrayList<String>();//Лист паролей
    public ArrayList<String> arrayListLog = new ArrayList<String>();//Лист логинов

    //Контроллер окна регистрации
    public RegWindowController regWindowController = new RegWindowController();

    //Контроллер главного окна
    public EnterFormController enterFormController = new EnterFormController();

    Stage stageLoginWindow = new Stage();


    //флаг обновления ArrayList логина и пароля
    private boolean update = true;

    public void Init() {

        //Кнопка Логина
        buttonLWLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    getLoginPassword();
                    if (CheckLog(textFieldLog.getText(), passField.getText(), "TextFilePass_log.txt")) {

                        //Закрывает окно логирования
                        stageLoginWindow = (Stage) buttonLWLog.getScene().getWindow();
                        stageLoginWindow.close();

                        //Открывает главное окно программы
                        enterFormController.showTempWIndow(textFieldLog.getText());
                        stageLoginWindow.close();
                    } else {
                        labelState.setText("Login is not found!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //Кнопка регистрации
        buttonLWReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                //Закрывает окно логирования
                stageLoginWindow = (Stage) buttonLWLog.getScene().getWindow();
                stageLoginWindow.close();

                //Открывает окно регистрации
                enterFormController.ShowRegWindow();
            }
        });

        //Кнопка отмены
        buttonLWCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    //Проверяет соответствие логина и пароля
    public boolean CheckLog(String log, String pass, String nameFile) throws IOException {
        for (int i = 0; i < arrayListPass.size(); i++) {
            if (arrayListLog.get(i).equals(log) && arrayListPass.get(i).equals(pass)) {
                System.out.println("Login is found!");
                return true;
            } else System.out.println("Login is not found!");
        }
        return false;
    }


    //Записывает логин и пароль из txt в ArrayList
    public void getLoginPassword() throws IOException {
        if (update) {
            arrayListLog.clear();
            arrayListPass.clear();
            try {//+File.separator +"src"+File.separator+"TextFile"+File.separator
                FileInputStream fstream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "TextFile" + File.separator + "Pass_Log.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String s;

                while ((s = br.readLine()) != null) {
                    // s = this.getLoginFile();
                    int i = 0;
                    int isLogin = 0;
                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == ' ') {
                            if (j > i) {
                                if (isLogin % 2 == 0) {
                                    this.arrayListLog.add(s.substring(i, j));
                                    System.out.println(isLogin + " " + s.substring(i, j));

                                } else {
                                    this.arrayListPass.add(s.substring(i, j));
                                    System.out.println(isLogin + " " + s.substring(i, j));
                                }
                            }
                            isLogin += 1;
                            i = j + 1;
                        }
                    }
                    if (i < s.length()) {
                        this.arrayListPass.add(s.substring(i));
                    }

                    update = false;

                }
            } catch (IOException e) {
                System.out.println("Error!");
            }

        }
    }


}