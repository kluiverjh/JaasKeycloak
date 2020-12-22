package code;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;

import jaas.CustomPermission;


public class CreateManager {

	public static final String OPERATION = "my-operation";

	// Wrapper for Action to check privilege
	private class CreateAction implements PrivilegedExceptionAction<ExecuteWorker>  {
		private CreateAction() {
			
		}
		
		public ExecuteWorker run() {
			// Check if user has rights in context of subject (throws exception when invalid rights)
	       AccessController.checkPermission(new CustomPermission("ExamplePermissionRead"));
	       System.out.println("Can execute ExecuteWorker ");
	       // Execute code (action)
	       return new ExecuteWorker();
		}
	}
    
    public void executePrivilegedAction(Subject subject) {
    	try {
    	    PrivilegedExceptionAction<ExecuteWorker> action = new CreateAction();
    	    ExecuteWorker worker = (ExecuteWorker)Subject.doAsPrivileged(subject, action, null);
            System.out.println("Executed worker code");
    	} catch (PrivilegedActionException excp) {
    	    System.out.println("Privileged Action Exception: "  +  excp.getMessage());
    	} catch (SecurityException excp) {
    		System.out.println("Security exception: " + excp.getMessage()); 
    	} catch (Throwable excp) {
    		System.out.println(excp.getMessage());
    	}
    }
}
