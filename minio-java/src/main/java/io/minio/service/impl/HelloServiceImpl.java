package io.minio.service.impl;

import io.minio.dao.HelloDao;
import io.minio.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * HelloServiceImpl.
 *
 * @author andy
 * @date 2021/11/14 7:48
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloDao helloDao;

    @Override
    public String hello(String name) {
        return "Hello, " + name;
    }

    public String sayHello(String name) {
        return helloDao.sayHello(name);
    }
}
