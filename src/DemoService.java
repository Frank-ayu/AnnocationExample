/**
 * 示例服务类，演示@LogExecutionTime注解的使用
 */
public interface DemoService {
    void fastMethod();
    void slowMethod();
    String processData(String input);
}

class DemoServiceImpl implements DemoService {
    @Override
    @LogExecutionTime("快速方法")
    public void fastMethod() {
        // 模拟快速执行的方法
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    @LogExecutionTime("慢速方法")
    public void slowMethod() {
        // 模拟慢速执行的方法
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    @LogExecutionTime
    public String processData(String input) {
        // 模拟数据处理
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "处理结果: " + input;
    }
}
