package cn.it.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Category;
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {
    public String update() {
        System.out.println("----update----");
        System.out.println(categoryService); // 由于已经和Spring整合，所以可以拿到这个categoryService了，打印出来就不是null了
        categoryService.update(model); // 新加一条语句，来更新数据库
        return "index";
    }

    public String save() {
        System.out.println("----save----");
        System.out.println(categoryService); // 由于已经和Spring整合，所以可以拿到这个categoryService了，打印出来就不是null了
        categoryService.save(model);
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
}