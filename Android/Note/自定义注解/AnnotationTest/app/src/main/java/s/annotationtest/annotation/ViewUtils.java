package s.annotationtest.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2015/6/10.
 */
public class ViewUtils {
    public static void injectView(Activity activity) {
        injectFiled(activity);
        //injectClick(activity);
        injectOnclickWithProxy(activity);


    }

    private static void injectOnclickWithProxy(Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        if (methods == null) return;
        if (methods.length == 0) return;
        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                ClickInvokeHandler invokeHandler = new ClickInvokeHandler(method, activity);
                View.OnClickListener onClickListener = (View.OnClickListener) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{View.OnClickListener.class}, invokeHandler);
                int id = method.getAnnotation(OnClick.class).value();
                View view = activity.findViewById(id);
                view.setOnClickListener(onClickListener);
            }

        }
    }

    private static void injectClick(Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        if (methods == null) return;
        if (methods.length == 0) return;
        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                View.OnClickListener listener = new MyOnClickLister(method, activity);
                int id = method.getAnnotation(OnClick.class).value();
                View view = activity.findViewById(id);
                view.setOnClickListener(listener);
            }
        }
    }

    private static void injectFiled(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        if (fields == null) return;
        if (fields.length == 0) return;
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                try {
                    field.setAccessible(true);
                    field.set(activity, activity.findViewById(viewInject.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyOnClickLister implements View.OnClickListener {

        Method m;
        Activity activity;

        public MyOnClickLister(Method m, Activity activity) {
            this.activity = activity;
            this.m = m;
        }

        @Override
        public void onClick(View v) {
            try {
                m.setAccessible(true);
                m.invoke(activity, v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
