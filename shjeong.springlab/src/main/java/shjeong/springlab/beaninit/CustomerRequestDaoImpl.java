package shjeong.springlab.beaninit;

public class CustomerRequestDaoImpl implements CustomerRequestDao {

    public CustomerRequestDaoImpl() {
        System.out.println("CustomerRequestDaoImpl Constructor");
    }
    
    public void submitRequest(CustomerRequestDetails customerRequestDetails) {
        System.out.println("CustomerRequestDaoImpl.submitRequest");
    }
    
}
