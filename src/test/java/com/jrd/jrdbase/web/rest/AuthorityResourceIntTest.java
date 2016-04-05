package com.jrd.jrdbase.web.rest;

import com.jrd.jrdbase.Application;
import com.jrd.jrdbase.repository.AuthorityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jakub on 05.04.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AuthorityResourceIntTest {

    @Inject
    private AuthorityRepository authorityRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        AuthorityResource authorityResource = new AuthorityResource();
        ReflectionTestUtils.setField(authorityResource, "authorityRepository", authorityRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorityResource)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    public void testGetAllAuthorities() throws Exception {
        mockMvc.perform(get("/api/authorities")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
    }
}
