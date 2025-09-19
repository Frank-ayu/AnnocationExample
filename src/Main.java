/**
 * 主类，测试@LogExecutionTime注解功能
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建目标对象
        DemoService demoService = new DemoServiceImpl();

        // 创建代理对象，实现注解功能
        DemoService proxyService = (DemoService) AnnotationProcessor.createProxy(demoService);

        // 调用方法，测试注解效果
        proxyService.fastMethod();
        proxyService.slowMethod();
        String result = proxyService.processData("测试数据");
        System.out.println(result);
    }
}
