package shjeong.springlab.protobean.acaware;

import java.beans.ConstructorProperties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerRequestServiceContextAwareImpl implements CustomerRequestService, ApplicationContextAware {
    
    private CustomerRequestDao customerRequestDao;
    
    private ApplicationContext applicationContext;
    
    @ConstructorProperties({"customerRequestDao"})
    public CustomerRequestServiceContextAwareImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
        System.out.println("CustomerRequestServiceContextAwareImpl Constructor");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext");
        this.applicationContext = applicationContext;
    }
    
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails customerRequestDetails = applicationContext.getBean("customerRequestDetails", CustomerRequestDetails.class);
        customerRequestDetails.setRequestType(requestType);
        customerRequestDetails.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(customerRequestDetails);
    }
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-common.xml");
        CustomerRequestService customerRequestService = context.getBean("customerRequestService", CustomerRequestService.class);
        customerRequestService.submitRequest("test1", "test2");
    }
}
