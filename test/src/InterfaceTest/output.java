package InterfaceTest;

public interface  output<T> {

    /**
     * 接口中必须都是抽象的方法，或者是具体的默认方法和类方法（子类可以直接继承这两种方法，不用实现)
     * 成员变量也必须是常量
     * @return
     */

    public T getName();

    public void setName(T name);
}
