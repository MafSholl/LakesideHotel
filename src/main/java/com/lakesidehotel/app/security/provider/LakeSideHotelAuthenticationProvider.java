package com.lakesidehotel.app.security.provider;

import com.lakesidehotel.app.utils.LakeSideHotelDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class LakeSideHotelAuthenticationProvider implements AuthenticationProvider {
    private final LakeSideHotelDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails != null){
            if (isMatches(authentication, userDetails)){
                log.info("details->{}", userDetails.getAuthorities());
                return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(),
                        userDetails.getAuthorities());
            }
            throw new BadCredentialsException("Incorrect password!!!");
        }
        throw new UsernameNotFoundException("Email not found!!!");
    }

    private boolean isMatches(Authentication authentication, UserDetails userDetails) {
        return passwordEncoder.matches((CharSequence) authentication.getCredentials(), userDetails.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        Class<UsernamePasswordAuthenticationToken> appAuthType = UsernamePasswordAuthenticationToken.class;
        return authentication.equals(appAuthType);
    }
}
