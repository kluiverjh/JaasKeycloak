package jaas;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.Principal;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import code.CreateManager;
import jaas.LoginService.ELoginModule;

public class Main {

	public static void main(String[] args) {

		// Command line also possible:
		// -Djava.security.auth.login.config=jaas.login.config
		System.setProperty("java.security.auth.login.config", "jaas.login.config");
		System.setProperty("java.security.policy", "security.policy");
		// System.setProperty("java.security.debug", "access,jar,policy,scl"); //
		// https://docs.oracle.com/javase/7/docs/technotes/guides/security/troubleshooting-security.html

		System.setSecurityManager(new SecurityManager()); // Enable JAVA security

		// Login
		LoginService loginService = new LoginService(ELoginModule.ModuleKeyclock);
		try {
			Subject subject = loginService.login();

			// Print principals
			Iterator principalIterator = subject.getPrincipals().iterator();
			System.out.println("Authenticated user has the following principals (roles):");
			while (principalIterator.hasNext()) {
				Principal p = (Principal) principalIterator.next();
				System.out.println("\t * '" + p.toString() + "'");
			}

            // Execute code with credentials of subject
			CreateManager manager = new CreateManager();
			manager.executePrivilegedAction(subject);
			
			System.out.println("Completed as expected");
		} catch (LoginException e) {
			System.out.println("Failed to login: " + e.getMessage());
		} catch (Exception ex) {
			System.out.println("Generic failure: " + ex.getMessage());
		}

	}

}
