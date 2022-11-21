package com.lakesidehotel.app.utils;

import com.lakesidehotel.app.security.provider.SecureUser;
import com.lakesidehotel.app.users.model.User;
import com.lakesidehotel.app.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LakeSideHotelDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userService.getUserByUsername(username);
        return new SecureUser(user);
    }
}
