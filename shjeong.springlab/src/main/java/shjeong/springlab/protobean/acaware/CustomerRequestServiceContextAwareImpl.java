package shjeong.springlab.protobean.acaware;

import java.beans.ConstructorProperties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 * ApplicationContextAware 인터페이스는 setApplicationContext(ApplicationContext applicationContext) 메소드를 제공하다.
 * 이를 통해 ApplicationContext를 주입 받아 필요한 시점에 prototype bean을 getBean하여 사용한다.
 * 
 *  == 개정이력(Modification Information) ==
 * 
 *    수정일			수정자				수정내용
 *  ---------------------------------------------------------------------------------
 *   2016. 2. 29	정상헌, (주)한국정보공학D&S		최초생성 
 * </pre>
 *
 * @author 정상헌, (주)한국정보공학D&S
 * @version 0.0.1
 * @since 2016. 2. 29
 */
public class CustomerRequestServiceContextAwareImpl implements CustomerRequestService, ApplicationContextAware {
    
    /** The customer request dao. */
    private CustomerRequestDao customerRequestDao;
    
    /** The application context. */
    private ApplicationContext applicationContext;
    
    /**
     * Instantiates a new customer request service context aware impl.
     *
     * @param customerRequestDao the customer request dao
     */
    @ConstructorProperties({"customerRequestDao"})
    public CustomerRequestServiceContextAwareImpl(CustomerRequestDao customerRequestDao) {
        this.customerRequestDao = customerRequestDao;
        System.out.println("CustomerRequestServiceContextAwareImpl Constructor");
    }
    
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext");
        this.applicationContext = applicationContext;
    }
    
    /* (non-Javadoc)
     * @see shjeong.springlab.protobean.acaware.CustomerRequestService#submitRequest(java.lang.String, java.lang.String)
     */
    public void submitRequest(String requestType, String requestDescription) {
        CustomerRequestDetails customerRequestDetails = applicationContext.getBean("customerRequestDetails", CustomerRequestDetails.class);
        customerRequestDetails.setRequestType(requestType);
        customerRequestDetails.setRequestDescription(requestDescription);
        customerRequestDao.submitRequest(customerRequestDetails);
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-acaware.xml");
        CustomerRequestService customerRequestService = context.getBean("customerRequestService", CustomerRequestService.class);
        customerRequestService.submitRequest("test1", "test2");
    }
}
