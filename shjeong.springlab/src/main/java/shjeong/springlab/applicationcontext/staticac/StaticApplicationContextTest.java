package shjeong.springlab.applicationcontext.staticac;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class StaticApplicationContextTest {
    
    @Test
    public void test() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);
        
        Hello hello1 = ac.getBean("hello1", Hello.class);
        assertNotNull(hello1);
        
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        ac.registerBeanDefinition("hello2", helloDef);
        
        Hello hello2 = ac.getBean("hello2", Hello.class);
        assertEquals(hello2.sayHello(), "Hello Spring");
        
        assertNotEquals(hello1, hello2);
        assertEquals(ac.getBeanFactory().getBeanDefinitionCount(), 2);
    }
    
    @Test
    public void registerBeanWithDependency() {
        StaticApplicationContext ac = new StaticApplicationContext();
        
        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
        
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
        
        ac.registerBeanDefinition("hello", helloDef);
        
        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();
        
        assertEquals(ac.getBean("printer").toString(), "Hello Spring");
    }
    
}
