package pl.balcerzak.nowekolory.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private UserDetailsServiceImpl userDetailsService;

    public AppUserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{login}")
    public UserDetails getUser(@PathVariable String login){
      return userDetailsService.loadUserByUsername(login);
    }
}
