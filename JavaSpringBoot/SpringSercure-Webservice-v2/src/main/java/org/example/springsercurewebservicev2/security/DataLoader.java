package org.example.springsercurewebservicev2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader {
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               RoleRepository roleRepository,
                                               PasswordEncoder passwordEncoder)
    {
        return args -> {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role employeeRole = new Role();
            employeeRole.setName("EMPLOYEE");
            roleRepository.save(employeeRole);

            Role publicRole = new Role();
            publicRole.setName("PUBLIC");
            roleRepository.save(publicRole);

            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            AppUser employee = new AppUser();
            employee.setUsername("employee");
            employee.setPassword(passwordEncoder.encode("employeepass"));
            employee.setRoles(Set.of(employeeRole));
            userRepository.save(employee);

            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.setRoles(Set.of(publicRole));
            userRepository.save(user);
        };
    }
}
