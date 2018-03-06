package TestException;

public class testEx {
    int a = 0;

    public static void main(String[] args) {
        testEx testEx = new testEx();

        try {
            testEx.divide();
        } catch (testException e) {
            e.printStackTrace();
        }


    }

    public void divide() throws testException {
        if(a == 0) throw new testException("除数不能为0");
        int c = 5 / a;

    }
}
