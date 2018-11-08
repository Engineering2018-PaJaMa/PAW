package Representation;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
    private final String name;
	private final String password;

    private final Set<String> roles;

    public User(String name, String password) {
        this.name = name;
        this.roles = null;
        this.password = password;
    }

    public User(String name, Set<String> roles, String password) {
        this.name = name;
        this.roles = roles;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }

    public Set<String> getRoles() {
        return roles;
    }

	public String getPassword()
	{
		return password;
	}
}
