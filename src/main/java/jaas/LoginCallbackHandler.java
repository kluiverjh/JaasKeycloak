package jaas;

import java.io.IOException;

// Fake login with username and password 

// Normally the username and password from login dialog would be entered here

import javax.security.auth.callback.*;

public class LoginCallbackHandler implements javax.security.auth.callback.CallbackHandler {

    public static final String DefaultUserName = "testuser";
    public static final String DefaultPassword = "testpassword";
    
    public void handle(javax.security.auth.callback.Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName(DefaultUserName);
                System.out.println("Username requested in callback, return '" + DefaultUserName + "'");
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword(DefaultPassword.toCharArray());
                System.out.println("Password requested in callback, return '" + DefaultPassword+ "'");
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
