package s.annotationtest.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Administrator on 2015/6/11.
 */
public class ClickInvokeHandler implements InvocationHandler {
    Method m;
    Object obj;

    public ClickInvokeHandler(Method m, Object obj) {
        this.m = m;
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        m.setAccessible(true);
        m.invoke(obj, args);
        return null;
    }
}
