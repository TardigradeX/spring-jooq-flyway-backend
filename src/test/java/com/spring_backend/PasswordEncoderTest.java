package com.spring_backend;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder encoder;

    @Test
    public void generatePasswords() {
        String encoded = encoder.encode("password");
        System.out.println("ENCODED: " + encoded);
        Assert.assertTrue(encoder.matches("password", encoded));
    }
}
