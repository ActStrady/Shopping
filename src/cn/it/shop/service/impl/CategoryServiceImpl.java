package cn.it.shop.service.impl;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    /*
     * 只需实现CategoryService接口中新增的方法即可，公共方法已经在BaseServiceImpl中实现了
     */
}