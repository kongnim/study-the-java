package me.study.the_java.section3;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 기본값은 CLASS
@Inherited // 이 어노테이션을 설정한 클래스를 상속받은 클래스도 적용되게 해준다.
public @interface MyAnnotation3 {
    String value() default "good";

}
