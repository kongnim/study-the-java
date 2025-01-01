package me.study.the_java.section3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 기본값은 CLASS
@Target({ElementType.TYPE, ElementType.FIELD}) // 붙일 수 있는 위치
public @interface MyAnnotation2 {

    String nickname(); // 기본값을 주지 않으면 어노테이션 사용시 필수로 값을 정해줘야 한다. @MyAnnotation2(nickname = "mjmj")

//    String value(); // value로 쓰면 단독으로 쓸 경우 명시하지 않고 쓸 수 있다. @MyAnnotation2("mjmj")

    String name() default "kmj";

    int number() default 100;


}
