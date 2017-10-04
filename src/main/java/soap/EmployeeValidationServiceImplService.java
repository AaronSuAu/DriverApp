package soap;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2017-09-30T15:21:38.166+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebServiceClient(name = "EmployeeValidationServiceImplService", 
                  wsdlLocation = "http://192.168.99.101:8888/assignment1/EmployeeValidation?wsdl",
                  targetNamespace = "http://assignment1.comp9322/") 
public class EmployeeValidationServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://assignment1.comp9322/", "EmployeeValidationServiceImplService");
    public final static QName EmployeeValidationServiceImplPort = new QName("http://assignment1.comp9322/", "EmployeeValidationServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://192.168.99.101:8888/assignment1/EmployeeValidation?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(EmployeeValidationServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://192.168.99.101:8888/assignment1/EmployeeValidation?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmployeeValidationServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EmployeeValidationServiceImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EmployeeValidationServiceImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public EmployeeValidationServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns EmployeeValidationService
     */
    @WebEndpoint(name = "EmployeeValidationServiceImplPort")
    public EmployeeValidationService getEmployeeValidationServiceImplPort() {
        return super.getPort(EmployeeValidationServiceImplPort, EmployeeValidationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmployeeValidationService
     */
    @WebEndpoint(name = "EmployeeValidationServiceImplPort")
    public EmployeeValidationService getEmployeeValidationServiceImplPort(WebServiceFeature... features) {
        return super.getPort(EmployeeValidationServiceImplPort, EmployeeValidationService.class, features);
    }

}
