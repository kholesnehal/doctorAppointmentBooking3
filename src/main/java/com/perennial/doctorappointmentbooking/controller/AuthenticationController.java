package com.perennial.doctorappointmentbooking.controller;
import com.perennial.doctorappointmentbooking.entity.AuthenticationRequest;
import com.perennial.doctorappointmentbooking.entity.AuthenticationResponse;
import com.perennial.doctorappointmentbooking.userService.MyUserDetailsService;
import com.perennial.doctorappointmentbooking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @RequestMapping(value = "/authenticate",method= RequestMethod.POST)
    public ResponseEntity<?>createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails=myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt=jwtTokenUtil.generateToken(userDetails);

   return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
