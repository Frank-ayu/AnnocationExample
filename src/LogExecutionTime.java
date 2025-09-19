import java.lang.annotation.*;

/**
 * 自定义注解，用于标记需要记录执行时间的方法
 */
@Target(ElementType.METHOD) // 注解仅用于方法
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时可见，可通过反射获取
@Documented // 生成文档时包含该注解
public @interface LogExecutionTime {
    // 注解的属性，默认为空字符串
    String value() default "";
}
