package shjeong.springlab.beaninit.complex;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FixedDepositDao implements InitializingBean, DisposableBean {

    public FixedDepositDao() {
        System.out.println("Construct InitMethodTest");
    }
    
    @PostConstruct
    public void postContruct() {
        System.out.println("postContruct FixedDepositDao");
    }
    
   // InitializingBean Interface
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet InitMethodTest");
    }
    
    public void initMethod() {
        System.out.println("initMethod FixedDepositDao");
    }
    
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("postContruct preDestroy");
    }
    
    
    // DisposableBean Interface
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy InitMethodTest");
    }

    
    public void destroyMethod() {
        System.out.println("initMethod FixedDepositDao");
    }
    
    
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beaninit/context-complex.xml");
        FixedDepositDao fixedDepositDao = context.getBean("fixedDepositDao", FixedDepositDao.class);
        
        context.registerShutdownHook();
    }
}
