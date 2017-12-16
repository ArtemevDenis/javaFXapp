
package EnterFormOtherClass;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.text.*;
/*
*
* Класс дла создания файлов и папок
*
* */

public class Object {
    private String pathUrl = System.getProperty("user.dir") + "Work";
    private String type;
    private String name;

    public Object(String name, String type) {
        this.type = type;
        this.name = name;
        createObject();
    }

    public Object(String name, String type, String Url) {
        this.name = name;
        this.type = type;
        //System.out.println(this.pathUrl);
        this.pathUrl = Url;
        createObject();
        //System.out.println(this.pathUrl);
    }

    public String getPathUrl() {
        return this.pathUrl;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return this.name;
    }


    // физически создаает элемент
    public void createObject() {
        boolean created;

        correction();
        System.out.println(pathUrl);
        if (this.type == "folder") {
            File folder = new File(this.pathUrl + File.separator + this.name);
            created = folder.mkdir();
            if (created)
                System.out.println("Dir is make");
        } else {
            File folder = new File(this.pathUrl);
            created = folder.mkdir();
            if (created)
                System.out.println("Dir is make");
            File newFile = new File(this.pathUrl, this.name + "." + this.type);
            try {
                created = newFile.createNewFile();
                if (created)
                    System.out.println("File is make");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    *
    * функция физ.удаление файлов и папок в корзину
    *
    * */

    public void remove(String NameCurFolder) {
        String TrashFolder =System.getProperty("user.dir") + "Trash";
        File TrashDir = new File(TrashFolder);
        TrashDir.mkdir();

        String NameFile = null;
        if (this.type != "folder") NameFile = this.name + "." + this.type;
        else NameFile = this.name;
        File CurFile = new File(this.getPathUrl() + NameFile);

        String NameFolder = FormFolder(NameCurFolder);
        String PuthFolder = TrashFolder + "\\" + NameFolder;

        File NewDir = new File(PuthFolder);
        NewDir.mkdir();
        CurFile.renameTo(new File(PuthFolder, NameFile));
        correction();
    }

    /*
    * функция для формирования имён папок в корзине с временем и датой
    *
    * */
    public String FormFolder(String Str) {
        Date dt = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd_MM_yyyy__hh_mm_ss__a_zzz");
        return Str + "_" + formatForDateNow.format(dt);
    }

    // редоктирует путь
    public String correction() {
        //System.out.println(pathUrl + "_META_");
        for (int i = 0; i < pathUrl.length(); i++) {
            if (pathUrl.charAt(i) == File.separator.toCharArray()[0]) {
            }
        }
        return null;
    }
}

