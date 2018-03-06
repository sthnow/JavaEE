package AbstractTest;

public class SetGet extends AbstractClass{

    public static void main(String[] args) {
        SetGet sg = new SetGet();
        sg.setName("李四");
        System.out.println(sg.getName());

    }

    @Override
    String getName() {
        return this.name;
    }
}
