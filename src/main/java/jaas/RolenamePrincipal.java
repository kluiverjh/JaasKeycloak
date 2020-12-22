package jaas;

import java.security.Principal;

public class RolenamePrincipal implements Principal, java.io.Serializable {

    /**
     * @serial
     */
    private String name;

    public RolenamePrincipal(String name) {
        if (name == null) throw new NullPointerException("illegal null input");
        this.name = name;
    }

    public String getName() { return name; }
    public String toString() { return(name); }
    public int hashCode() { return name.hashCode();}

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof RolenamePrincipal)) return false;
        RolenamePrincipal that = (RolenamePrincipal)o;
        if (this.getName().equals(that.getName())) return true;
        return false;
    }

    
}