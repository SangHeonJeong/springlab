package shjeong.springlab.protobean.acaware;

public class CustomerRequestDetails {
    
    private String requestType;
    
    private String requestDescription;
    
    public CustomerRequestDetails() {
        System.out.println("CustomerRequestDetails Constructor");
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
    
}
