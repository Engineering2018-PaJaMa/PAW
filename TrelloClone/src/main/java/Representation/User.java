package Representation;

import java.security.Principal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Principal
{
	private String name;
	private String password;
	private Set<String> roles;
}
