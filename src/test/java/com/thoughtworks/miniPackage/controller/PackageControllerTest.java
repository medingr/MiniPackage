package com.thoughtworks.miniPackage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.miniPackage.model.MiniPackage;
import com.thoughtworks.miniPackage.service.MiniPackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Package.class)
@ActiveProfiles(profiles = "test")
public class PackageControllerTest {

    @MockBean
    private MiniPackageService packageService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_package_when_create_package () throws Exception {
        //given
        MiniPackage packOne = new MiniPackage();
        packOne.setClientName("Gray");
        packOne.setTelephone(123);

        when(packageService.addPackage(packOne)).thenReturn(packOne);
        //when
        ResultActions result = mvc.perform(post("/miniPackage")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(packOne)));

        //then
        result.andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    void should_return_list_of_mini_packages_by_page() throws Exception {
        //given
        MiniPackage miniPackage = new MiniPackage();
        when(packageService.findAll(new PageRequest(0,5))).
                thenReturn(Collections.singleton(miniPackage));

        //when
        ResultActions result = mvc.perform(get("/miniPackage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(miniPackage)));
        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }
}
