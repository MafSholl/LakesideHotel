package com.lakesidehotel.app.security.manager;

import com.lakesidehotel.app.security.provider.LakeSideHotelAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LakeSideHotelAuthenticationManager implements AuthenticationManager {
    private final LakeSideHotelAuthenticationProvider lakeSideHotelAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return lakeSideHotelAuthenticationProvider.authenticate(authentication);
    }
}
