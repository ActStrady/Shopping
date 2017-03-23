import org.hibernate.Session;

import cn.it.shop.model.Category;
import cn.it.shop.utils.HibernateSessionFactory;


public class CategoryServiceImpl implements CategoryService {

    @Override //没有和Spring整合的情况
    public void save(Category category) {
        //通过SessionFactory获取Session
        Session session = HibernateSessionFactory.getSession();
        try {
            // 手动开启一个事务
            session.getTransaction().begin();
            // 执行业务逻辑
            session.save(category);
            // 手动提交事务
            session.getTransaction().commit();
        } catch (Exception e) {
            // 回滚事务
            session.getTransaction().rollback();
            throw new RuntimeException();
        } finally{
            HibernateSessionFactory.closeSession();
        }
    }

}
