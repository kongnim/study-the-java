package me.study.the_java.section3;

public class Note {
    public static String A = "A";

    private String B = "B";

    public Note() {
    }

    public Note(String b) {
        B = b;
    }

    public void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }

}
