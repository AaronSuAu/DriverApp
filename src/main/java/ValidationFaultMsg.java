
import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 3.0.4 2017-09-30T15:21:38.155+10:00
 * Generated source version: 3.0.4
 */

@WebFault(name = "ValidationFault", targetNamespace = "http://assignment1.comp9322")
public class ValidationFaultMsg extends Exception {

	private ServiceFaultType validationFault;

	public ValidationFaultMsg() {
		super();
	}

	public ValidationFaultMsg(String message) {
		super(message);
	}

	public ValidationFaultMsg(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationFaultMsg(String message, ServiceFaultType validationFault) {
		super(message);
		this.validationFault = validationFault;
	}

	public ValidationFaultMsg(String message, ServiceFaultType validationFault, Throwable cause) {
		super(message, cause);
		this.validationFault = validationFault;
	}

	public ServiceFaultType getFaultInfo() {
		return this.validationFault;
	}
}
