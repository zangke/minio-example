package io.minio.controller;

import io.minio.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HelloControllerTest2.
 *
 * @author andy
 * @date 2021/11/14 7:56
 */
@WebMvcTest(HelloController.class)
public class HelloControllerTest2 {
    @Autowired
    private HelloController helloController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    public void testNotNull() {
        Assertions.assertThat(helloController).isNotNull();
    }

    @Test
    public void testHello() throws Exception {
        Mockito.when(helloService.hello(Mockito.anyString())).thenReturn("Mock hello");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/springboot"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Mock hello"));
    }
}
