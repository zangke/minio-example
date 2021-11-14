package io.minio.dao;

import org.springframework.stereotype.Repository;

/**
 * HelloDao.
 *
 * @author andy
 * @date 2021/11/14 8:15
 */
@Repository
public class HelloDao {
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
