package com.spring_backend.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_backend.domain.User;
import com.spring_backend.repository.UserRepository;
import com.spring_backend.security.xauth.Token;
import com.spring_backend.security.xauth.TokenProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider provider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private HttpHeaders adminHeaders;
    private HttpHeaders userHeaders;

    @Before()
    public void initDefaultUser() {;

        UserDetails details = this.userDetailsService.loadUserByUsername("admin");
        Token token = provider.createToken(details);
        adminHeaders = new HttpHeaders();
        adminHeaders.add("x-auth-token", token.getToken());

        details = this.userDetailsService.loadUserByUsername("user");
        token = provider.createToken(details);
        userHeaders = new HttpHeaders();
        userHeaders.add("x-auth-token", token.getToken());
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/users/3").headers(adminHeaders)).andDo(print()).andExpect(status().isNotFound()).andReturn();

        MvcResult result = this.mockMvc.perform(get("/api/users/1").headers(adminHeaders)).andDo(print()).andExpect(status().isOk()).andReturn();

        User user  = mapper.readValue(result.getResponse().getContentAsByteArray(), User.class);

        Assert.assertEquals("admin", user.getName());

        //Try to delete user with user rights. Should not work.
        this.mockMvc.perform(delete("/api/users/2").headers(userHeaders)).andDo(print()).andExpect(status().is4xxClientError());

        //Try to delete user with admin rights. Should work
        this.mockMvc.perform(delete("/api/users/2").headers(adminHeaders)).andDo(print()).andExpect(status().isOk());

    }

}
