package com.thisisjava.book1;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface PrintAnnotation{
    String value() default "-";
    int number() default 20;
}

class Service{
    @PrintAnnotation
    public void method1(){
        System.out.println("함수 1입니다.");
    }

    @PrintAnnotation("*")
    public void method2(){
        System.out.println("어노테이션 기본값 value() 를 사용 하는 예제 입니다.");
    }

    @PrintAnnotation(value = "#", number = 25)
    public void method3(){
        System.out.println("value=# 이고 내가 만든 number()는 25입니다.");
    }
}

public class AnnotationTest {
    public static void main(String[] args){
        //Service 클래스로부터 메소드 정보르 얻음 (어노테이션의 리플렉션 기능)
        Method[] delcaredMethods = Service.class.getDeclaredMethods();

        for (Method method: delcaredMethods){
            if(method.isAnnotationPresent(PrintAnnotation.class)){
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);

                System.out.println("[" +method.getName()+ "]");

                for (int i=0; i<printAnnotation.number(); i++){
                    System.out.print(printAnnotation.value());
                }

                System.out.println();

                try{
                    method.invoke(new Service());
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println();
                }
            }
        }
    }
}
