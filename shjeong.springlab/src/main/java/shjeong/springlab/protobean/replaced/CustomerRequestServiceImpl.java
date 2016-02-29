package shjeong.springlab.protobean.replaced;

import java.beans.ConstructorProperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import shjeong.springlab.protobean.cmmn.CustomerRequestDao;
import shjeong.springlab.protobean.cmmn.CustomerRequestDetails;

public class CustomerRequestServiceImpl implements CustomerRequestService {
    
    private CustomerRequestDao customerRequestDao;
    
    @ConstructorProperties({"customerRequestDao"})
    public CustomerRequestServiceImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
        System.out.println("CustomerRequestServiceContextAwareImpl Constructor");
    }
    
    public Object getByBean(String beanName) {
        return null;
    }
        
    @Override
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails customerRequestDetails = (CustomerRequestDetails)getByBean("customerRequestDetails");
        customerRequestDetails.setRequestType(requestType);
        customerRequestDetails.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(customerRequestDetails);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-replaced.xml");
        CustomerRequestService customerRequestService = context.getBean("customerRequestService", CustomerRequestService.class);
        customerRequestService.submitRequest("test1", "test2");
    }
    
}
