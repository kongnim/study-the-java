package me.study.the_java.section3;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BookMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        // Class<T>에 접근하는 방법 =========================================================

        // 방법 1. 모든 클래스를 로딩한 다음 Class<T>의 인스턴스가 생긴다. 타입.class로 접근
        Class<Book> bookClass = Book.class;

        // 방법 2. 모든 인스턴스는 getClass() 메소드를 가지고 있다. 인스턴스.getClass()로 접근
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 방법 3. 문자열로 읽어온다. (FQCN : Full Qualified Class Name)
        Class<?> aClass1 = Class.forName("me.study.the_java.section3.Book");

        // Class<T>에 접근 후 참조할 수 있는 것들 ===============================================

        System.out.println("============================================= 필드");

        Field[] fields = bookClass.getFields(); // 필드 목록

        // .getFields() 메서드는 public한 것만 가져온다.
        Arrays.stream(fields).forEach(System.out::println);  // public java.lang.String me.study.the_java.section3.Book.d 출력

        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); // 접근제한자 상관없이 전부 가져온다.
        // 출력 결과
//        public java.lang.String me.study.the_java.section3.Book.d
//        private java.lang.String me.study.the_java.section3.Book.a
//        private static java.lang.String me.study.the_java.section3.Book.b
//        private static final java.lang.String me.study.the_java.section3.Book.c
//        public java.lang.String me.study.the_java.section3.Book.d
//        protected java.lang.String me.study.the_java.section3.Book.e

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf(">>> %s, %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        // 출력 결과
//        >>> private java.lang.String me.study.the_java.section3.Book.a, a
//        >>> private static java.lang.String me.study.the_java.section3.Book.b, BOOK
//        >>> private static final java.lang.String me.study.the_java.section3.Book.c, BOOK
//        >>> public java.lang.String me.study.the_java.section3.Book.d, d
//        >>> protected java.lang.String me.study.the_java.section3.Book.e, e

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });
        // 출력 결과
//        private java.lang.String me.study.the_java.section3.Book.a
//        true
//        false
//        private static java.lang.String me.study.the_java.section3.Book.b
//        true
//        true
//        private static final java.lang.String me.study.the_java.section3.Book.c
//        true
//        true
//        public java.lang.String me.study.the_java.section3.Book.d
//        false
//        false
//        protected java.lang.String me.study.the_java.section3.Book.e
//        false
//        false


        System.out.println("============================================= 메서드");
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        // 직접 정의한 메서드 말고 Object에서 상속받은 메서드도 함께 볼 수 있다.
        // 출력 결과
//        public int me.study.the_java.section3.Book.h()
//        public void me.study.the_java.section3.Book.g()
//        public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
//        public final void java.lang.Object.wait() throws java.lang.InterruptedException
//        public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
//        public boolean java.lang.Object.equals(java.lang.Object)
//        public java.lang.String java.lang.Object.toString()
//        public native int java.lang.Object.hashCode()
//        public final native java.lang.Class java.lang.Object.getClass()
//        public final native void java.lang.Object.notify()
//        public final native void java.lang.Object.notifyAll()

        Arrays.stream(bookClass.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
            System.out.println(m.getReturnType());
        });
        // 출력 결과
//        public int me.study.the_java.section3.Book.h()
//        false
//        false
//        int
//        public void me.study.the_java.section3.Book.g()
//        false
//        false
//        void
//        public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
//        false
//        false
//        void
//        public final void java.lang.Object.wait() throws java.lang.InterruptedException
//        false
//        false
//        void
//        public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
//        false
//        false
//        void
//        public boolean java.lang.Object.equals(java.lang.Object)
//        false
//        false
//        boolean
//        public java.lang.String java.lang.Object.toString()
//        false
//        false
//        class java.lang.String
//        public native int java.lang.Object.hashCode()
//        false
//        false
//        int
//        public final native java.lang.Class java.lang.Object.getClass()
//        false
//        false
//        class java.lang.Class
//        public final native void java.lang.Object.notify()
//        false
//        false
//        void
//        public final native void java.lang.Object.notifyAll()
//        false
//        false
//        void

        System.out.println("============================================= 생성자");

        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);
        // 출력 결과
//        public me.study.the_java.section3.Book()
//        public me.study.the_java.section3.Book(java.lang.String,java.lang.String,java.lang.String)

        System.out.println("============================================= 상위 클래스");
        System.out.println(MyBook.class.getSuperclass());
        // 출력 결과
//        class me.study.the_java.section3.Book

        System.out.println("============================================= 인터페이스");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
        // 출력 결과
//        interface me.study.the_java.section3.MyInterface
    }
}
