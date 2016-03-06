package shjeong.springlab.beaninit.initdispbean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FixedDepositDao implements InitializingBean, DisposableBean {

    public FixedDepositDao() {
        System.out.println("Construct InitMethodTest");
    }
    
    // InitializingBean Interface
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet InitMethodTest");
    }
    
    // DisposableBean Interface
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy InitMethodTest");
    }

    
    
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beaninit/context-initdispbean.xml");
        FixedDepositDao fixedDepositDao = context.getBean("fixedDepositDao", FixedDepositDao.class);
        
        context.registerShutdownHook();
    }
}
