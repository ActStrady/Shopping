package cn.it.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

public class CategoryAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
    private Category category;// 设置一个私有成员变量接收url带过来的参数，注意下面要写好get和set方法
    private CategoryService categoryService; // 设置categoryService是为了很直观的看出与Spring整合前后的不同

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String update() {
        System.out.println("----update----");
        System.out.println(categoryService); // 由于已经和Spring整合，所以可以拿到这个categoryService了，打印出来就不是null了
        categoryService.update(category); // 新加一条语句，来更新数据库
        return "index";
    }

    public String save() {
        System.out.println("----save----");
        System.out.println(categoryService); // 由于已经和Spring整合，所以可以拿到这个categoryService了，打印出来就不是null了
        return "index";
    }

    public String query() {
        // 解决方案一，采用相应的map取代原来的内置对象，这样与jsp没有依赖，但是代码量比较大
        // ActionContext.getContext().put("categoryList",
        // categoryService.query()); //放到request域中
        // ActionContext.getContext().getSession().put("categoryList",
        // categoryService.query()); //放到session域中
        // ActionContext.getContext().getApplication().put("categoryList",
        // categoryService.query()); //放到application域中

        // 解决方案二，实现相应的接口(RequestAware,SessionAware,ApplicationAware)，让相应的map注入
        request.put("categoryList", categoryService.query());
        session.put("categoryList", categoryService.query());
        application.put("categoryList", categoryService.query());
        return "index";
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Map<String, Object> request;
    private Map<String, Object> session;
    private Map<String, Object> application;

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
}