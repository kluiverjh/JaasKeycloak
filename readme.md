#  **JAAS (Java Authentication and Authorization Services)**

Simple example how to setup the JAAS KEYCLOAK adapter. The adapter uses Direct Access Grants method to gain access token from keycloak.

The application will validate (hardcoded) username and password against keycloak  (or local debug LoginModule). And check if role exists.

![](doc/images/application_output.png)

In this example user and password are send in http to keycloak, a https url should be used to make it secure.

## Keycloak docker

For testing, the easiest way is to setup a keycloak docker container 

```bash
docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:12.0.1
```



## Setup keycloak for demo application

* Open 'http://localhost:8080'

* Login on administration console with username 'admin' and password 'admin'

  <img src="doc\images\login.png" alt="Login" />

* Add realm 'realm_demo'

  ![add_realm](doc/images/add_realm.png)

* Add client 'keycloak-demo-client'

  ![add_client](doc/images/add_client.png)

* Configure client with access type confidential and direct access grants enabled.

  ![](doc/images/settings_client.png)

* The client secret can now be found on tab credentials

  ![](doc/images/client_secret.png)

* Add the role 'example_role_read' to the client

  ![add_client](doc/images/client_add_role.png)

* Add user 'testuser'

  ![add_user](doc\images\add_user.png)

* Set password "testpassword' (as defined in LoginCallbackHandler.java)

  ![](doc/images/set_user_password.png)

  **Important** Temporary password must be off.

* Add role ''example_role_read' to client

  ![client_add_role](doc/images/client_add_role.png)

  ![client_add_role_to_user](doc/images/client_add_role_to_user.png)

  

* Make sure 'keycloak.json' matches the values as set above

  ![](doc/images/keyclock.png)





https://docs.oracle.com/javase/7/docs/technotes/guides/security/jaas/JAASRefGuide.html

https://github.com/keycloak/keycloak-documentation/blob/master/securing_apps/topics/oidc/java/jaas.adoc

https://github.com/keycloak/keycloak-documentation/blob/master/securing_apps/topics/oidc/java/java-adapter-config.adoc
