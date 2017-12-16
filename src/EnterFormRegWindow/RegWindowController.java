package EnterFormRegWindow;

import EnterFormOtherClass.FileApp;
import EnterFormOtherClass.User;
import MainEnterForm.EnterFormController;
import MainEnterForm.MainEnterFormGUI;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class RegWindowController {

    @FXML
    public ScrollPane scrollePane;
    public Label labelName;
    public Label labelSurname;
    public Label labelEmail;
    public Label labelMobPhone;
    public Label labelWorPh;
    public Label labelPass;
    public Label labelTolLvl;
    public Label labelOtherInf;
    public Label labelDatBirth;
    public Label labelDateReg;
    public Label labelId;


    @FXML
    private RadioMenuItem radioMenuItemAuditor;

    @FXML
    private DatePicker datePickerReg;

    @FXML
    private RadioMenuItem radioMenuItemUser;

    @FXML
    private Pane paneRegWindow;


    @FXML
    private TextField textFieldMail;

    @FXML
    private TextField textFieldTolLvl;

    @FXML
    private TextField textFieldName;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField passwordLengthField;
    @FXML
    private TextField textFieldId;

    @FXML
    private RadioMenuItem radioMenuItemAdmin;

    @FXML
    private DatePicker datePickerBirth;

    @FXML
    private Button buttonReg;

    @FXML
    private TextArea textAreaOthInf;

    @FXML
    private Button buttonCancel;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Label labelState;

    @FXML
    private TextField textFieldWorkPhone;

    @FXML
    private TextField textFieldMobPhone;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private Button buttonGenPass;

    public ArrayList<String> arrayListInfo = new ArrayList<String>(); //Лист с дополнительной информацией
    public ArrayList<String> arrayListLog = new ArrayList<String>();//Лист логинов
    public ArrayList<String> log = new ArrayList<String>();

    Stage stageRegWindow = new Stage();

    //Контроллер главного окна
    public EnterFormController enterFormController = new EnterFormController();
    public MainEnterFormGUI mainEnterFormGUI = new MainEnterFormGUI();

    public void Init() {

        //Кнопка регистрации
        buttonReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (CheckReg()) {

                    FileApp fileAppName = new FileApp();
                    FileApp fileAppLogins = new FileApp();
                    arrayListInfo.add(textFieldName.getText() + " " + textFieldSurname.getText());

                    fileAppName.fileWriter(arrayListInfo, "Info.txt");


                    //if(in.contains(textFieldMail.getText()))
                    fileAppLogins.fileWriter(arrayListLog, "LOGINS.TXT");


                    //Объект User хранит в себе поля, которые заполняются при регистрации
                    User user = new User(textFieldName.getText(),
                            textFieldSurname.getText(),
                            textFieldMail.getText(),
                            textFieldMobPhone.getText(),
                            textFieldWorkPhone.getText(),
                            passField.getText(),
                            textFieldTolLvl.getText(),
                            textAreaOthInf.getText(),
                            datePickerBirth.getValue().toString(),
                            datePickerReg.getValue().toString(),
                            textFieldId.getText());

                    // класс Gson позволяет хранить объекты в файлах с расширение .json
                    String useron = new Gson().toJson(user);

                    labelState.setText("Success!");

                    /** Sending an email to user */
                    CodeSender codeSender = new CodeSender();
                    Generator passwordGen = new Generator();
                    String code = passwordGen.generateCode();
                    try {
                        codeSender.SendCode(code,
                                textFieldMail.getText(),
                                passField.getText(),
                                textFieldSurname.getText(),
                                textFieldName.getText());
                    } catch (IOException | MessagingException | javax.mail.MessagingException e) {
                        e.printStackTrace();
                    }

                    log.add(textFieldMail.getText());
                    FileApp file = new FileApp();
                    file.fileWriter(log, "LOGINS.TXT");


                    // Запись в файл всей информации о пользователе
                    try {
                        FileWriter out = new FileWriter(code + ".json");
                        out.write(useron);
                        out.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }


                    //Закрывает окно логирования
                    stageRegWindow = (Stage) buttonReg.getScene().getWindow();
                    stageRegWindow.close();

                    //Открывает окно логина программы
                    Stage loginStage = new Stage();
                    try {
                        mainEnterFormGUI.start(loginStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else labelState.setText("Input error!");

            }
        });

        //Кнопка отмены
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        //Password generator
        buttonGenPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Generator passwordGen = new Generator();
                String password;
                if(passwordLengthField.getText().isEmpty()) password = passwordGen.generatePassword(8);
                else password = passwordGen.generatePassword(Integer.valueOf(passwordLengthField.getText()));
                passField.setText(password);
            }
        });


    }

    //Проверка полей на заполнение
    private boolean CheckReg() {
        /*Дописать все оставшиеся проверки полей*/
        boolean f = false;

        if (!textFieldName.getText().isEmpty() && !textFieldSurname.getText().isEmpty() &&
                !textFieldMail.getText().isEmpty() && !textFieldMobPhone.getText().isEmpty() &&
                !textFieldWorkPhone.getText().isEmpty() && !passField.getText().isEmpty() &&
                !textFieldTolLvl.getText().isEmpty() &&
                !textFieldId.getText().isEmpty()) f = true;
        else {
            cleanLabelState();
            if (textFieldName.getText().isEmpty()) labelName.setText("Field is empty!");
            if (textFieldSurname.getText().isEmpty()) labelSurname.setText("Field is empty!");
            if (textFieldMail.getText().isEmpty()) labelEmail.setText("Field is empty!");
            if (textFieldMobPhone.getText().isEmpty()) labelMobPhone.setText("Field is empty!");
            if (textFieldWorkPhone.getText().isEmpty()) labelWorPh.setText("Field is empty!");
            if (passField.getText().isEmpty()) labelPass.setText("Field is empty!");
            if (textFieldTolLvl.getText().isEmpty()) labelTolLvl.setText("Field is empty!");
            if (textFieldId.getText().isEmpty()) labelId.setText("Field is empty!");

        }
        return f;
    }

    private void cleanLabelState() {
        labelName.setText("");
        labelSurname.setText("");
        labelEmail.setText("");
        labelMobPhone.setText("");
        labelMobPhone.setText("");
        labelPass.setText("");
        labelTolLvl.setText("");
        labelId.setText("");
    }

}