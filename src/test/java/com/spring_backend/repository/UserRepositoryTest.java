package com.spring_backend.repository;

import com.spring_backend.domain.Role;
import com.spring_backend.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User defaultUser;

    @Before()
    public void initDefaultUser() {
        defaultUser= new User();
        defaultUser.setEmailid("test@test");
        defaultUser.setName("Name");
        defaultUser.setPassword("fsdgsfgkjsdngösdfgn");
    }

    @Test
    public void createUser() throws Exception {
        Assert.assertTrue(userRepository.createUser(defaultUser));
    }

    @Test
    public void getUser() throws Exception {
        userRepository.createUser(defaultUser);

        Optional<User> userOptional = userRepository.getUser(2L);

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(2L,userOptional.get().getId());
        Assert.assertEquals(Role.USER,userOptional.get().getRole());
    }

    @Test
    public void updateUser() throws Exception {
        userRepository.createUser(defaultUser);

        Optional<User> userOptional = userRepository.getUser(2L);
        User updatedUser = userOptional.get();
        updatedUser.setName("NEW_NAME");
        updatedUser.setEmailid("new@mail");
        updatedUser.setPassword("newPassword");

        Assert.assertTrue(userRepository.updateUser(updatedUser));

        userOptional = userRepository.getUser(2L);

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(updatedUser.getName(),userOptional.get().getName());
        Assert.assertEquals(updatedUser.getPassword(),userOptional.get().getPassword());
        Assert.assertEquals(updatedUser.getEmailid(),userOptional.get().getEmailid());

    }

    @Test
    public void deleteUser() throws Exception {
        userRepository.createUser(defaultUser);


        Assert.assertTrue(userRepository.deleteUser(1L));

        Assert.assertFalse(userRepository.deleteUser(1L));

        Assert.assertFalse(userRepository.deleteUser(20L));


    }
}
