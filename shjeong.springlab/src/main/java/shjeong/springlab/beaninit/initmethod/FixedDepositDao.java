package shjeong.springlab.beaninit.initmethod;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FixedDepositDao {

    public FixedDepositDao() {
        System.out.println("Construct InitMethodTest");
    }
    
    public void init() {
        System.out.println("init InitMethodTest");
    }
    
    public void destroy() {
        System.out.println("destroy InitMethodTest");
    }
    
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beaninit/context-initmethod.xml");
        FixedDepositDao fixedDepositDao = context.getBean("fixedDepositDao", FixedDepositDao.class);
        
        // ClassPathXmlApplicationContext에서는 destroy-method를 호출하게 끔 해줘야 한다. WebApplicationContext에서는 해당 논리가 포함되어 있다
        // 단 프로토타입 빈은 destroy-method를 무시한다. 프로토타 빈은 필요한 destroy 작업을 직접 해야한다.
        context.registerShutdownHook();
    }
}
