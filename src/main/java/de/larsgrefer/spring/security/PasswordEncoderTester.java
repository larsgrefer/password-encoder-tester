package de.larsgrefer.spring.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.time.Duration;

public class PasswordEncoderTester {

    public static void main(String[] args) {
        System.out.println("Warmup");
        testPasswordEncoder(new BCryptPasswordEncoder(), 10);
        testPasswordEncoder(new SCryptPasswordEncoder(), 10);
        testPasswordEncoder(new Argon2PasswordEncoder(), 10);
        testPasswordEncoder(new Pbkdf2PasswordEncoder(), 10);

        System.out.println("Actual Test");
        testPasswordEncoder(new BCryptPasswordEncoder(), 100);
        testPasswordEncoder(new SCryptPasswordEncoder(), 100);
        testPasswordEncoder(new Argon2PasswordEncoder(), 100);
        testPasswordEncoder(new Pbkdf2PasswordEncoder(), 100);
    }

    public static void testPasswordEncoder(PasswordEncoder passwordEncoder, int iterations) {

        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            passwordEncoder.encode("password" + i);
        }
        Duration duration = Duration.ofNanos((System.nanoTime() - start) / iterations);

        System.out.println(passwordEncoder.getClass().getSimpleName() + " : " + duration.toMillis() + "ms");
    }
}
