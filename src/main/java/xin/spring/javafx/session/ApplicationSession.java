package xin.spring.javafx.session;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xin.spring.javafx.component.jpa.config.JpaConfig;

import java.util.HashMap;

/**
 * 运用程序会话，缓存
 */
public final class ApplicationSession extends HashMap<Object, Object> {

    private ApplicationSession(){}

    private static final ApplicationSession INSTANCE = new ApplicationSession();

    public static ApplicationSession getInstance(){
        return INSTANCE;
    }

    @Override
    public ApplicationSession put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    @Override
    public ApplicationSession remove(Object key) {
        super.remove(key);
        return this;
    }

    public <T> T get(Class<T> clazz){
        Object o = super.get(clazz);
        return o != null ? (T)o : null;
    }

    public <T> T getComponent(Class<T> clazz){
        AnnotationConfigApplicationContext context = null;
        if(this.get(AnnotationConfigApplicationContext.class) == null){
            context = new AnnotationConfigApplicationContext(JpaConfig.class);
            this.put(AnnotationConfigApplicationContext.class, context);
        }
        context = this.get(AnnotationConfigApplicationContext.class);
        return context.getBean(clazz);
    }

}
