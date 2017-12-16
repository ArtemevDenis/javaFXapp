package CompleteRegWindow;

import EnterFormOtherClass.FileApp;
import EnterFormOtherClass.User;
import MainEnterForm.EnterFormController;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompleteRegControler {

    @FXML
    private Label fileNotFound;

    @FXML
    private Button buttonComplete;

    @FXML
    private Button buttonToFiles;

    @FXML
    private TextField enterCodeField;

    private ArrayList<String> pass = new ArrayList<String>();

    private Stage stageCompleteRegistration = new Stage();

    private EnterFormController enterFormController = new EnterFormController();

    public void Init(String login) {
            buttonComplete.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                try {
                    Gson gson = new Gson();
                    FileReader fr = new FileReader(enterCodeField.getText() + ".json");
                    BufferedReader bf = new BufferedReader(fr);
                    User user = gson.fromJson(bf, User.class);
                    pass.add(user.getEmail() + " " + user.getPassword());
                    FileApp filew = new FileApp();

                    filew.fileWriter(pass, "Pass_log.txt");
                    //TODO Create a method, that sends from object user to database
                    //TODO Make a method, that @return true if user was created in db and false if not

                    System.out.println(user.getName());
                    fileNotFound.setText(enterCodeField.getText() + " is activated!");
                    bf.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                 File file =  new File(enterCodeField.getText() + ".json");
                 if(file.delete()){
                     System.out.println("File " + enterCodeField.getText() + " was deleted!");
                 } else{
                     System.out.println("File " +enterCodeField.getText() + " was not deleted! Delete it manually");
                 }

                 }




             });


            buttonToFiles.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stageCompleteRegistration = (Stage) buttonComplete.getScene().getWindow();
                    stageCompleteRegistration.close();

                    //Открывает главное окно программы
                    enterFormController.showEnterFormGUI(login);
                }
            });
    }
}
