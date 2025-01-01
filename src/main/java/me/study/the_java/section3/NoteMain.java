package me.study.the_java.section3;

import java.lang.reflect.*;
import java.util.Arrays;

public class NoteMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> noteClass = Class.forName("me.study.the_java.section3.Note");
//        noteClass.newInstance(); // 이 방법은 deprecated 되었다.
        Constructor<?> constructor = noteClass.getConstructor(null); // 파라미터가 없는 기본 생성자
        Note note = (Note) constructor.newInstance(); // Note의 Instance가 새로 만들어진다.
        System.out.println(note);

        Constructor<?> constructor2 = noteClass.getConstructor(String.class); // 파라미터가 있는 생성자를 가져오고 싶을 때 파라미터를 입력해줌
        Note note2 = (Note) constructor2.newInstance("param~~");
        System.out.println(note2);

        Field a = noteClass.getDeclaredField("A"); // A는 static한 Field
        System.out.println(a.get(null)); // static한 필드로 넘겨줄 특정 object가 없음. null 넘겨주면 A 출력
        a.set(null, "BBB");
        System.out.println(a.get(null)); // BBB 출력

        Field b = noteClass.getDeclaredField("B"); // B는 static 필드가 아님. 특정 인스턴스를 거쳐서 가져와야한다.
        b.setAccessible(true); // B가 private이므로 true로 설정
        System.out.println(b.get(note)); // note의 B 필드를 가져온다. B 출력
        b.set(note, "CC");
        System.out.println(b.get(note)); // CC 출력

        Method nm = noteClass.getDeclaredMethod("c");
        nm.setAccessible(true);
        nm.invoke(note); // C 출력

        Method nm2 = noteClass.getDeclaredMethod("sum", int.class, int.class); // sum이라는 메서드를 가져오면서 파라미터가 int, int인 것
        int invoke = (int) nm2.invoke(note, 1, 2); // 1, 2가 method sum의 파라미터로 넘어간다.
        System.out.println(invoke); // 메서드 sum의 return값이 int이므로 캐스팅해서 3 출력
    }
}
