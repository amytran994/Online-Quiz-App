package com.project.util;

import java.io.*;

public class SerializeUtil {
    public static void serialization(Object object){
        try {
            FileOutputStream file = new FileOutputStream("file.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
            System.out.println("Object has been serialized");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Object deserialization(){
        Object object = null;
        try {
            FileInputStream file = new FileInputStream("file.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            object = in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized ");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return object;

    }
}
