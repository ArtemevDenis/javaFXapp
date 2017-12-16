package EnterFormOtherClass;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*В этом классе содержаться методы, которые
 *позволяют записывать личные дынные пользователей
 *из ArrayList в текстовые файлы с именем,
 *которое передаётся в качестве параметра типа String.
 *ArrayList из которого необходимо взять логин и пароль,
 *так же передаются в метод с помощью параметров*/


public class FileApp {

    /* Функция принимает два ArrayList и имя фпйла
    * и записывает данные в текстовый файл.
    * Файл создаётся в папке TextFile*/
    public void fileWriter(ArrayList<String> arrayListLog, ArrayList<String> arrayListPass, String nameFile) {
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + File.separator + "src" + File.separator + "TextFile" + File.separator + nameFile, true)) {
            for (int i = 0; i < arrayListLog.size(); i++) {
                writer.write(arrayListLog.get(i) + " " + arrayListPass.get(i));
                writer.write("\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*"C:\\Users\\User\\IdeaProjects\\EnterFormFXML\\src\\TextFile"*/
    /* Функция принимает строку и имя файла и
    * записывает в файл. Файл создаётся в папке TextFile*/
    public void setLogin(String login, String nameFile) {

        FileWriter writer = null;
        try {
            boolean created;
            File newFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "TextFile" + File.separator + nameFile);
            try {
                created = newFile.createNewFile();
                if (created)
                    System.out.println("File is make");
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer = new FileWriter(System.getProperty("user.dir") + File.separator + "src" + File.separator + "TextFile" + File.separator + nameFile);
            writer.write(login);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Функция принимает один ArrayList и имя фпйла
    * и записывает данные в текстовый файл.
    * Файл создаётся в папке TextFile*/
    public void fileWriter(ArrayList<String> arrayListLog, String nameFile) {
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + File.separator + "src" + File.separator + "TextFile" + File.separator + nameFile, true)) {
            for (int i = 0; i < arrayListLog.size(); i++) {
                writer.write(arrayListLog.get(i));
                writer.write("\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
