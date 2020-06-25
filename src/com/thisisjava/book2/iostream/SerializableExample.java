package com.thisisjava.book2.iostream;

/*
interface Serializable
- 이 인터페이스를 구현한 클래스만 직렬화할 수 있다.
- static, transient 외의 기본 필드만 바이트로 변환된다.
 */

import java.io.*;

class ClassA implements Serializable {
    // 직렬화 대상
    int field1;
    ClassB field2 = new ClassB();

    // 직렬화 대상 아님
    static int field3;
    transient int field4;
}

class ClassB implements Serializable {
    int field1;
}

public class SerializableExample {

    public static void main(String[] args) throws Exception{

        // 직렬화해서 저장
//        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        ClassA classA = new ClassA();
//        classA.field1 = 1;
//        classA.field2.field1 =2;
//        classA.field3 =3;
//        classA.field4 =4;
//        oos.writeObject(classA);
//        oos.flush();
//        oos.close();
//        fos.close();

        //역질렬화해서 복원
        FileInputStream fis= new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ClassA v = (ClassA) ois.readObject();
        System.out.println("field1: " + v.field1);
        System.out.println("field2.field1: " + v.field2.field1);
        System.out.println("field3: " + v.field3); // 뭐지? 책에서는 static은 직렬화 안됀다고 했는데 잘 나옴. => 같이써서 그래. static 이자나. 따로따로 돌리면 책대로 나옴.
        System.out.println("field4: " + v.field4);

    }
}
