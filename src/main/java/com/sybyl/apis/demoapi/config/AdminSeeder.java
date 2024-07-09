package com.sybyl.apis.demoapi.config;

import com.sybyl.apis.demoapi.model.ExpenseUser;
import com.sybyl.apis.demoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Lazy
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }
        var userDetails = ExpenseUser.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .build();
        System.out.println("Creating super administrator: " + userDetails);

        userRepository.save(userDetails);
    }
}
