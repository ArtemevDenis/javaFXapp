package EnterFormOtherClass;

import java.io.File;
import java.util.ArrayList;

/*
*
* Тут сделана структура дерева списка для свободного траверса внутри директории
*
* */


public class Node {
    private Node parent;
    private ArrayList<Node> next = new ArrayList<>();
    private Object key;

    public Node(Object key) {
        this.parent = null;
        this.key = key;
    }

    public Node(Node parent, Object key) {
        this.parent = parent;
        this.key = key;
    }

    public Node() {
        this.parent = null;
        this.key = null;
    }

    /*
    * функция удаления файла в корзину
    * */
    public void Remove(String removeName, String removeType) {

        System.out.println(removeName + "and  " + removeType);
        for (int i = 0; i < next.size(); i++) {
            if (next.get(i).getKey().getName().equals(removeName)
                    && next.get(i).getKey().getType().equals(removeType)) {
                next.get(i).getKey().remove(this.getKey().getName());
                next.remove(i);
            }
        }
    }


    //функция добавления файлов в дерикторию
    public void Add(String key, String type) {
        Object newObject = new Object(key, type, this.getKey().getPathUrl() + File.separator + this.getKey().getName());
        Node newNode = new Node(this, newObject);
        for (int i = 0; i < this.next.size(); i++) {
            if (newNode.getKey().getName().equals(next.get(i).getKey().getName())
                    && newNode.getKey().getType().equals(next.get(i).getKey().getType())) {
                return;
            }
        }
        this.next.add(newNode);
    }

    // вход в дерикторию
    public Node Push(String key) {
        System.out.println(next.size());
        for (int i = 0; i < next.size(); i++) {
            System.out.println(next.get(i).key.getName() + " " + next.get(i).key.getType());
            if (next.get(i).key.getName().equals(key) && next.get(i).getKey().getType().equals("folder")) {
                System.out.println("_____________Push");
                return next.get(i);
            }
            System.out.println("______NOT_PUSH");
        }
        return this;
    }

    // выход из текущей директории и вход в родителя
    public Node Back() {

        if (this.parent != null)
            return this.parent;
        return this;
    }

    //
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getNext() {
        return this.next;
    }

    public Object getKey() {
        return this.key;
    }
}