package cn.it.shop.action;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//将BaseAction中的内容归归类了  
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,
        SessionAware, ApplicationAware, ModelDriven<T> {

    // service对象
    @Resource
    protected CategoryService categoryService;
    @Resource
    protected AccountService accountService;

    // 域对象
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;

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

    // ModelDriven
    protected T model;

    @Override
    public T getModel() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class clazz = (Class) type.getActualTypeArguments()[0];
        try {
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }
}