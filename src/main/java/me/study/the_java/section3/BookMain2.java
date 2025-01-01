package me.study.the_java.section3;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BookMain2 {
    public static void main(String[] args) {

        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
        // 기본적으로 Annotation은 주석과 마찬가지다.
        // 정보가 Class까지는 남지만 바이트코드를 로딩했을 때 메모리상에는 남지 않는다.
        // 같이 읽고 싶으면 Retention을 설정해준다.

        // 출력 결과'
//        @me.study.the_java.section3.MyAnnotation2(name="kmj", number=100, nickname="mjmj")
//        @me.study.the_java.section3.MyAnnotation3("good")

        // % javap -c -v /Users/kong/dev/workspace/the-java/target/classes/me/study/the_java/section3/Book.class
//        RuntimeVisibleAnnotations:
//        0: #59(#60=s#61)
//        me.study.the_java.section3.MyAnnotation2(
//                nickname="mjmj"
//        )
//        1: #62()
//        me.study.the_java.section3.MyAnnotation3
//        RuntimeInvisibleAnnotations:
//        0: #64()
//        me.study.the_java.section3.MyAnnotation

        System.out.println("====================================================");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
        // 출력 결과
//        @me.study.the_java.section3.MyAnnotation3("good")

        System.out.println("====================================================");
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
        // 상속받은 것 제외하고 딱 MyBook에 선언된 Annotation만 가져오고 싶을 때

        System.out.println("====================================================");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(a -> {
            if (a instanceof MyAnnotation3) {
                MyAnnotation3 myAnnotation = (MyAnnotation3) a;
                System.out.println(myAnnotation.value()); // good 출력
            }
        });

    }
}
