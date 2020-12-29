package jaas.LoginModules;

import java.security.Principal;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.keycloak.adapters.jaas.DirectAccessGrantsLoginModule;

import jaas.RolenamePrincipal;

public class KeycloackLoginModule extends  DirectAccessGrantsLoginModule {

	public KeycloackLoginModule() {
		System.out.println("Using KeycloackLoginModule");
		// In security.policy is defined that keycloack.json contains client data
	}
	
    @Override
    public boolean commit() throws LoginException {
    	boolean result = super.commit();
    	
    	boolean has_role = false;
    	// In security.policy the class RolenamePrincipal is used, add RolenamePrincipal (is Principal is used, this isn't needed)
    	Iterator principals = subject.getPrincipals().iterator();
    	while (principals.hasNext()) {
			Principal p = (Principal) principals.next();
			if (p.getName().equalsIgnoreCase("example_role_read")) {
				System.out.println("Has Principal 'example_role_read' add class RolenamePrincipal");
				has_role = true;
			}
		}
    	if (has_role) subject.getPrincipals().add(new RolenamePrincipal("Rolename:example_role_read"));
    	return result;
    }
}
