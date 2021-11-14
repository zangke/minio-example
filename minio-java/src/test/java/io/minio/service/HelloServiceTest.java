package io.minio.service;

import io.minio.dao.HelloDao;
import io.minio.service.impl.HelloServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * HelloServiceTest.
 *
 * @author andy
 * @date 2021/11/14 8:27
 */
@ExtendWith(MockitoExtension.class)
class HelloServiceTest {
    @Mock
    private HelloDao helloDao;
    @Autowired
    @InjectMocks
    private HelloServiceImpl helloService;

    @Test
    void hello() {
        Assertions.assertThat(helloService != null);
        when(helloDao.sayHello(any())).thenReturn("Hello");
        helloService.sayHello("Andy");
        Assertions.assertThat(helloService.sayHello("Andy")).isEqualTo("Hello");
        verify(helloDao, times(2)).sayHello(any());
    }
}