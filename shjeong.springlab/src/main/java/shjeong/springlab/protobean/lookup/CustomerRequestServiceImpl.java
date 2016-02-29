package shjeong.springlab.protobean.lookup;

import java.beans.ConstructorProperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import shjeong.springlab.protobean.acaware.CustomerRequestDao;
import shjeong.springlab.protobean.acaware.CustomerRequestDetails;
import shjeong.springlab.protobean.acaware.CustomerRequestService;

public abstract class CustomerRequestServiceImpl implements CustomerRequestService {
    
    private CustomerRequestDao customerRequestDao;
    
    @ConstructorProperties({"customerRequestDao"})
    public CustomerRequestServiceImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
        System.out.println("CustomerRequestServiceContextAwareImpl Constructor");
    }
    
    /**
     * prototype bean을 가져올 메소드를 abstract로 선언한다 (abstract로 하지 않아도 된다).
     *
     * @return the customer request details
     */
    public abstract CustomerRequestDetails getCustomerRequestDetails();
    
    @Override
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails customerRequestDetails = getCustomerRequestDetails();
        customerRequestDetails.setRequestType(requestType);
        customerRequestDetails.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(customerRequestDetails);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-acaware.xml");
        CustomerRequestService customerRequestService = context.getBean("customerRequestService", CustomerRequestService.class);
        customerRequestService.submitRequest("test1", "test2");
    }
    
}
