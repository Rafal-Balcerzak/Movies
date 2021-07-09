package pl.balcerzak.nowekolory.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        AppUser appUserRafal = new AppUser();
        appUserRafal.setId(1l);
        appUserRafal.setUsername("admin");
        appUserRafal.setPassword(passwordEncoder.encode("admin123"));
        appUserRafal.setRole("ROLE_ADMIN");
        appUserRepository.save(appUserRafal);

        AppUser appUserKuba = new AppUser();
        appUserKuba.setId(2l);
        appUserKuba.setUsername("user");
        appUserKuba.setPassword(passwordEncoder.encode("user123"));
        appUserKuba.setRole("ROLE_ADMIN");
        appUserRepository.save(appUserKuba);

        AppUser appUserMati = new AppUser();
        appUserMati.setId(3l);
        appUserMati.setUsername("test");
        appUserMati.setPassword(passwordEncoder.encode("test123"));
        appUserMati.setRole("ROLE_ADMIN");
        appUserRepository.save(appUserMati);
    }
}
