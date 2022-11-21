package com.lakesidehotel.app.security.config;


import com.lakesidehotel.app.security.filter.LakeSideHotelAuthenticationFilter;
import com.lakesidehotel.app.security.filter.LakeSideHotelAuthorizationFilter;
import com.lakesidehotel.app.security.jwt.JwtUtil;
import com.lakesidehotel.app.security.manager.LakeSideHotelAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final LakeSideHotelAuthenticationManager lakeSideHotelAuthenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        UsernamePasswordAuthenticationFilter filter = new LakeSideHotelAuthenticationFilter(lakeSideHotelAuthenticationManager, jwtUtil);
        filter.setFilterProcessesUrl("/api/v1/lakeSideHotel/staff/login");

        return http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/lakeSideHotel/getAllAvailableRooms/")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/lakeSideHotel/staff/register/")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/lakeSideHotel/admin/addARoom/")
                .hasAnyAuthority("ADMIN", "MANAGER")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/lakeSideHotel/getGuestDetails/{roomNumber}/")
                .hasAnyAuthority("ADMIN", "RECEPTIONIST", "MANAGER")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/lakeSideHotel/guest/bookARoom")
                .hasAnyAuthority("GUEST", "RECEPTIONIST")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/lakeSideHotel/isRoomAvailable/{roomNumber}/", "/api/v1/lakeSideHotel/checkIn/{email}", "/api/v1/lakeSideHotel/checkOut/{email}")
                .hasAnyAuthority("GUEST", "RECEPTIONIST")
                .and()
                .addFilter(filter)
                .addFilterBefore(new LakeSideHotelAuthorizationFilter(), LakeSideHotelAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .build();
    }
}
