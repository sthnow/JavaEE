package aop.test;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void save() {
        System.out.println("保存客户");
    }

    @Override
    public void update() {
        System.out.println("更新信息");

    }

    @Override
    public void delete() {
        System.out.println("删除客户");
    }

    @Override
    public void find() {
        System.out.println("查找客户");
    }
}
