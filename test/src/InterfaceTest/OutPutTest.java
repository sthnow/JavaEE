package InterfaceTest;

public class OutPutTest implements output<String> {

    private String name;    //定义一个成员变量

    public static void main(String[] args) {
        String name;        //如果在方法里定义变量，例如这里，就是局部变量
        OutPutTest opt = new OutPutTest();
        opt.setName("zhangsan");
        System.out.println(opt.getName());
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {

    }
}
