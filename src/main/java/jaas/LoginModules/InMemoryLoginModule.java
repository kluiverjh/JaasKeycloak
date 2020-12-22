package jaas.LoginModules;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import jaas.LoginCallbackHandler;
import jaas.RolenamePrincipal;

// Simple LoginModule that validates login against one user (for testing)

public class InMemoryLoginModule implements LoginModule {

	public final static String DefaultUserName = LoginCallbackHandler.DefaultUserName; 
	public final static String DefaultPassword = LoginCallbackHandler.DefaultPassword; 
	
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private String username;
    private boolean loginSucceeded = false;
    private Principal userPrincipal;


    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
                           Map<String, ?> options) {
    	System.out.println("Using InMemoryLoginModule");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }


    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("username: ");
        PasswordCallback passwordCallback = new PasswordCallback("password: ", false);
        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());
            if (DefaultUserName.equals(username) && DefaultPassword.equals(password)) {
                loginSucceeded = true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Can't login");
        }
        return loginSucceeded;
    }


    public boolean commit() throws LoginException {
        if (!loginSucceeded) {
            return false;
        }
        
        userPrincipal = new RolenamePrincipal(username);
        if (!subject.getPrincipals().contains(userPrincipal))
            subject.getPrincipals().add(userPrincipal);
        

        return true;
    }


    public boolean abort() throws LoginException {
        logout();
        return true;
    }


    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        return false;
    }
}
