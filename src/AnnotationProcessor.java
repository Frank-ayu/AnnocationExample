import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 注解处理器，通过动态代理实现对@LogExecutionTime注解的处理
 */
public class AnnotationProcessor implements InvocationHandler {
    // 目标对象
    private final Object target;

    public AnnotationProcessor(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     */
    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AnnotationProcessor(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取目标对象的实际方法
        Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        // 检查目标方法是否有@LogExecutionTime注解
        if (targetMethod.isAnnotationPresent(LogExecutionTime.class)) {
            LogExecutionTime annotation = targetMethod.getAnnotation(LogExecutionTime.class);
            String methodDesc = annotation.value().isEmpty() ? method.getName() : annotation.value();

            // 记录开始时间
            long startTime = System.currentTimeMillis();

            // 执行目标方法
            Object result = method.invoke(target, args);

            // 计算并输出执行时间
            long endTime = System.currentTimeMillis();
            System.out.printf("[%s] 执行时间: %d ms%n", methodDesc, (endTime - startTime));

            return result;
        }

        // 如果没有注解，直接执行方法
        return method.invoke(target, args);
    }
}
