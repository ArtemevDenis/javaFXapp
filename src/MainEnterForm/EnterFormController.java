package MainEnterForm;

import CompleteRegWindow.CompleteRegControler;
import EnterFormLoginWindow.LoginWindowController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import EnterFormOtherClass.*;
import EnterFormOtherClass.Object;
import EnterFormRegWindow.RegWindowController;

import ModelWindows.ModelWindows;
import OpenFile.OpenFile;
import TemporaryWindow.TempWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* Класс контроллер основного окна*/

public class EnterFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateFile;

    @FXML
    private Button btnCreateFolder;

    @FXML
    private Label labelUrl;

    @FXML
    private ListView listView;

    private MainEnterFormGUI mainEnterFormGUI;

    private ModelWindows modelWindows = new ModelWindows();

    Node tree;
    ObservableList<String> items = FXCollections.observableArrayList();

    //Окно логина
    public LoginWindowController loginWindowController;

    //Окно регистрации
    public RegWindowController regWindowController;

    //Главное окно
    public EnterFormController enterFormController;

    //Окно завершения регистрации
    public CompleteRegControler completeRegControler;

    public TempWindowController tempWindowController;

    public void setEnterFormGUI(MainEnterFormGUI mainEnterFormGUI) {
        this.mainEnterFormGUI = mainEnterFormGUI;
    }

    Stage stageEFWindow = new Stage();

    Stage stageLoginWindow = new Stage();

    public void ShowLoginWindow() {
        stageLoginWindow.show();
    }

    Stage stageRegWindow = new Stage();

    Stage stageCompleteReg = new Stage();

    Stage stageTemporaryWindow = new Stage();
    public void ShowRegWindow() {
        setRegWindowController();
        stageRegWindow.show();
    }

    public void showCompleteRegWindow(String login) {
        setCompleteRegWindowController(login);
        stageCompleteReg.show();
    }

    public void showTempWIndow(String login) {
        setTemporaryWindowController(login);
        stageTemporaryWindow.show();
    }

    /*Логин которй вводит пользователь, когда входит в программу
    * Он передаётся в качестве аргумента в функцию showEnterFormGUI
    * из окна логирования*/
    private String buffLog;




    /* *
     *
     *
     * Ниже идут функции логина и регистрации
     *
     *
     * */

    //Открывает главное окно
    public void showEnterFormGUI(String login) {

        //логин который вводит пользователь при входе в программу
        buffLog = login;
        System.out.println(buffLog + "_META_BUF_LOG");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EnterFormController.class.getResource("/MainEnterForm/EnterForm.fxml"));
        try {
            Pane pane = (Pane) loader.load();
            Scene scene = new Scene(pane);
            stageEFWindow.setScene(scene);
            stageEFWindow.initModality(Modality.APPLICATION_MODAL);
            stageEFWindow.setTitle("PROGRAM_NAME");
            enterFormController = loader.getController();
            enterFormController.Init(buffLog);
            stageEFWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLoginWindowController() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EnterFormController.class.getResource("/EnterFormLoginWindow/LoginWindow.fxml"));
        try {
            Pane pane = (Pane) loader.load();
            Scene scene = new Scene(pane);
            stageLoginWindow.setScene(scene);
            stageLoginWindow.setTitle("Login");
            loginWindowController = loader.getController();
            loginWindowController.Init();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRegWindowController() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EnterFormController.class.getResource("/EnterFormRegWindow/RegWindow.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            stageRegWindow.setScene(scene);
            stageRegWindow.initModality(Modality.APPLICATION_MODAL);
            stageRegWindow.setTitle("Registration");
            regWindowController = loader.getController();
            regWindowController.Init();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCompleteRegWindowController(String login){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CompleteRegControler.class.getResource("/CompleteRegWindow/CompleteRegWindow.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            stageCompleteReg.setScene(scene);
            stageCompleteReg.initModality(Modality.APPLICATION_MODAL);
            stageCompleteReg.setTitle("Complete Registration");
            completeRegControler = loader.getController();
            completeRegControler.Init(login);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setTemporaryWindowController(String login) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TempWindowController.class.getResource("/TemporaryWindow/TempWindow.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            stageTemporaryWindow.setScene(scene);
            stageTemporaryWindow.initModality(Modality.APPLICATION_MODAL);
            stageTemporaryWindow.setTitle("Choose what to do");
            tempWindowController = loader.getController();
            tempWindowController.Init(login);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Инициализирует главное окно
    public void Init(String root) {
        tree = new Node(new Object(root, "folder"));

        setTreeForCurrentUser(System.getProperty("user.dir") + "Work" + File.separator + root);

        items.add(tree.getKey().getName());
        listView.setItems(items);
        CurrentListFiles();

        setMenuItemsEnent();
        setBtnBack();
        setBtnCreateFile();
        setBtnCreateFolder();
        setListViewOnMouseClicked();
    }

    /* *
    *
    *
    * Ниже элементы FileManager
    *
    *
    * */

    //кнопка создания папки
    public void setBtnCreateFolder() {
        btnCreateFolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelWindows.newWindowCreateFolder(stageEFWindow);
                if (modelWindows.getFlagFolder()) tree.Add(modelWindows.getNameFolder(), "folder");
                CurrentListFiles();
            }
        });
    }

    //кнопка создания файла
    public void setBtnCreateFile() {
        btnCreateFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modelWindows.newWindowCreateFile(stageEFWindow);
                if (modelWindows.getFlagFile()) tree.Add(modelWindows.getNameFile(), modelWindows.getTypeFile());
                CurrentListFiles();
            }
        });
    }

    //кнопка возврата в директорию на шаг назад
    public void setBtnBack() {
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tree = tree.Back();
                CurrentListFiles();
            }
        });
    }

    // двойной щелчок для открытия файла папки(пока что только папки)
    public void setListViewOnMouseClicked() {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    String cur = listView.getSelectionModel().getSelectedItem().toString();
                    if (isFile(cur)) {
                        OpenFile openFile = new OpenFile();
                        openFile.openFileWindow(stageEFWindow, cur);
                    } else {
                        tree = tree.Push(cur);
                        CurrentListFiles();
                    }

                }
            }
        });
    }

    //Метод контекстного меню
    public void setMenuItemsEnent() {
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String selectedItem = listView.getSelectionModel().getSelectedItem().toString();
                if (isFile(selectedItem)) {
                    OpenFile openFile = new OpenFile();
                    openFile.openFileWindow(stageEFWindow, selectedItem);
                    System.out.println("it is file you open file");
                } else {
                    tree = tree.Push(selectedItem);
                    CurrentListFiles();
                }

            }
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String selectedItem = listView.getSelectionModel().getSelectedItem().toString();
                System.out.println("Delete_item: " + selectedItem);
                if (isFile(selectedItem)) {
                    tree.Remove(getNameObject(selectedItem), getTypeObject(selectedItem));
                    System.out.println(getNameObject(selectedItem) + " " + getTypeObject(selectedItem));
                } else {
                    tree.Remove(selectedItem, "folder");
                    CurrentListFiles();
                }
            }
        });
        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String selectedItem = listView.getSelectionModel().getSelectedItem().toString();
                System.out.println("Rename_item: " + selectedItem);
            }
        });
        contextMenu.getItems().addAll(openItem, renameItem, deleteItem);
        listView.setContextMenu(contextMenu);
        listView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(listView, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
    }

    /* *
    *
    *
    * Вспомогательные функции и методы
    *
    *
    * */

    //Метод проверяющий файл это или нет
    public boolean isFile(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '.')
                return true;
        }
        return false;
    }

    // текущий набор файлов в этой дериктори
    public void CurrentListFiles() {
        labelUrl.setText(tree.getKey().getPathUrl() + File.separator + tree.getKey().getName());
        ArrayList<Node> nodeList = tree.getNext();
        items.clear();
        for (int i = 0; i < tree.getNext().size(); i++)
            if (nodeList.get(i).getKey().getType() != "folder")
                items.add(nodeList.get(i).getKey().getName() + "." + nodeList.get(i).getKey().getType());
            else
                items.add(nodeList.get(i).getKey().getName());
        listView.refresh();
    }

    private String getNameObject(String name) {
        int i;
        for (i = 0; i < name.length(); i++)
            if (name.charAt(i) == '.')
                break;
        return name.substring(0, i);
    }

    private String getTypeObject(String name) {
        int i;
        for (i = 0; i < name.length(); i++)
            if (name.charAt(i) == '.')
                break;
        return name.substring(i + 1, name.length());
    }

    // Небольшая проверка на существоване директории
    public void setTreeForCurrentUser(String userName) {
        File f = new File(userName);
        if (!f.exists()) {
            System.out.println("\nNot found: " + userName);
            return;
        }

        if (!f.isDirectory()) {
            System.out.println("\nNot directory: " + userName);
            return;
        }
        list(userName);
    }

    //Функция берет все файлы по дериктории
    void list(String szDir) {
        File f = new File(szDir);
        String[] sDirList = f.list();
        int i;
        for (i = 0; i < sDirList.length; i++) {
            File f1 = new File(szDir + File.separator + sDirList[i]);
            if (f1.isFile()) {

                String name = new String();
                String type = new String();

                for (int j = 0; j < sDirList[i].length(); j++)
                    if (sDirList[i].charAt(j) == '.' && j != sDirList[i].length() - 1) {
                        name = sDirList[i].substring(0, j);
                        type = sDirList[i].substring(j + 1, sDirList[i].length());

                    }

                tree.Add(name, type);
                System.out.println(szDir + File.separator + sDirList[i]);
            } else {
                this.tree.Add(sDirList[i].toString(), "folder");
                tree = tree.Push(sDirList[i]);
                list(szDir + File.separator + sDirList[i]);
                tree = tree.Back();
            }
        }
    }
}

/*
    //кнопка создания
    public void setBtnCreateFile() {
        modelWindows.newWindowCreateFile();
        if (modelWindows.isFlag()) {
            tree.Add(modelWindows.getNameFile(), modelWindows.getTypeFile());
        }
        btnCreateFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {
                if (!textName.getText().isEmpty() && !textType.getText().isEmpty()) {
                    tree.Add(textName.getText(), textType.getText());
                    CurrentListFiles();
                }
                if (!textName.getText().isEmpty() && textType.getText().isEmpty()) {
//                    System.out.println((tree.getNext().get(0).getKey().getNameFile() + " " + tree.getNext().get(0).getKey().getTypeFile()));
                    tree.Add(textName.getText(), "folder");
                    System.out.println((tree.getNext().get(0).getKey().getNameFile() + " " + tree.getNext().get(0).getKey().getTypeFile()));

                    CurrentListFiles();
                }

            }
        });
    }

    //кнопка создания папки
    public void setBtnCreateFolder() {
        modelWindows.newWindowCreateFile();
        if (modelWindows.isFlag()) {
            tree.Add(modelWindows.getNameFile(), "folder");
        }
        btnCreateFolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent event) {
                if (!textName.getText().isEmpty() && !textType.getText().isEmpty()) {
                    tree.Add(textName.getText(), textType.getText());
                    CurrentListFiles();
                }
                if (!textName.getText().isEmpty() && textType.getText().isEmpty()) {
//                    System.out.println((tree.getNext().get(0).getKey().getNameFile() + " " + tree.getNext().get(0).getKey().getTypeFile()));
                    tree.Add(textName.getText(), "folder");
                    System.out.println((tree.getNext().get(0).getKey().getNameFile() + " " + tree.getNext().get(0).getKey().getTypeFile()));

                    CurrentListFiles();
                }

            }
        });
    }
//кнопка открытия
    public void setBtnOpen() {
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textName.getText().isEmpty() && !textType.getText().isEmpty()) {
                    //tree = tree.Push(textName.getText());
                }
                if (!textName.getText().isEmpty() && textType.getText().isEmpty()) {
                    tree = tree.Push(textName.getText());
                    System.out.println((tree.getKey().getName() + " " + tree.getKey().getType()));
                    CurrentListFiles();
                }
            }
        });
    }

        public void setBtnRemove() {
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textName.getText().isEmpty() && !textType.getText().isEmpty()) {
                    tree.Remove(textName.getText(), textType.getText());
                    CurrentListFiles();
                }
                if (!textName.getText().isEmpty() && textType.getText().isEmpty()) {
                    tree.Remove(textName.getText(), "folder");
                    CurrentListFiles();
                }
            }
        });
    }

*/

