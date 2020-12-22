package jaas;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;



public class LoginService {
	
	// This name is referenced in jaas.login.config
	public final String REALM_IN_MEMORY = "InMemoryLoginModuleRealm";
	public final String REALM_IN_DIRECT_ACCESS_GRANTS_LOGIN = "DirectAccessGrantsLoginRealm";
	
	public final String REALM;
	
	public enum ELoginModule {
		ModuleInMemory,
		ModuleKeyclock
	}
	
	public LoginService(ELoginModule pModule) {
		switch (pModule) {
		case ModuleInMemory: 
			REALM = REALM_IN_MEMORY;
			break;
		case ModuleKeyclock: 
			REALM = REALM_IN_DIRECT_ACCESS_GRANTS_LOGIN;
			break;
		default:
			REALM = "";
	     	break;
		}
	}
	
	public Subject login() throws LoginException {
		LoginContext loginContext = new LoginContext(REALM, new LoginCallbackHandler() /* simulate user entering username and password */);
		loginContext.login();
		return loginContext.getSubject();
	}
}
