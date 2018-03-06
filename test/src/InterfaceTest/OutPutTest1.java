package InterfaceTest;

public class OutPutTest1 implements output1 {

    private String name;    //定义一个成员变量

    public static void main(String[] args) {
        String name;        //如果在方法里定义变量，例如这里，就是局部变量
        OutPutTest1 opt = new OutPutTest1();
        opt.setName("wang wu");
        System.out.println((String) opt.getName());
    }


    @Override
    public <String>String getName() {
        return (String) this.name;
    }

    @Override
    public <String>void setName(String name) {
        this.name = (java.lang.String) name;
    }


    }

