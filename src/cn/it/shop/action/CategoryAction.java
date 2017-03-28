package cn.it.shop.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.it.shop.service.CategoryService;

public class CategoryAction extends ActionSupport {  
    private CategoryService categoryService; //设置categoryService是为了很直观的看出与Spring整合前后的不同  
      
    public void setCategoryService(CategoryService categoryService) {  
        this.categoryService = categoryService;  
    }  
      
    public String update() {  
        System.out.println("----update----");  
        System.out.println(categoryService); //整合前后输出不同  
        return "index";  
    }  
      
    public String save() {  
        System.out.println("----save----");  
        System.out.println(categoryService);//整合前后输出不同  
        return "index";  
    }  
}  