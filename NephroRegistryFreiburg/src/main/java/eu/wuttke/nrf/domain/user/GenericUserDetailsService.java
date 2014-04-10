package eu.wuttke.nrf.domain.user;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GenericUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserInfo> uis = UserInfo.findUserInfoesByUserNameEquals(username).getResultList();
		if (uis == null || uis.size() == 0)
			throw new UsernameNotFoundException(username);
		UserInfo ui = uis.get(0);
		
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(ui.getUserName(), ui.getPassword(), authorities);
	}
	
}
