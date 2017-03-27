package cn.it.shop.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;
import cn.it.shop.utils.HibernateSessionFactory;

public class CategoryServiceImpl implements CategoryService {

    @Override
    // 没有和Spring整合的情况
    public void save(Category category) {
        // 通过SessionFactory获取Session
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
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    /* Spring和Hibernate整个后 */
    private SessionFactory sessionFactory;

    protected Session getSession() {
        // 从当前线程获取session，如果没有则创建一个新的session
        return sessionFactory.getCurrentSession();
    }

    // 当需要使用sessoinFactory的时候，Spring会将sessionFactory注入进来
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    // Spring和Hibernate整合后的情况
    public void update(Category category) {
        getSession().update(category);
    }

}
