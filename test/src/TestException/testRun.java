package TestException;

public class testRun {
    int a = 1;

    public static void main(String[] args) {
        testRun tr = new testRun();
        if (tr.a > 0) throw new RuntimeException("a的字母不能等于0");
            int b = 99 / 0;

    }
}
