package com.railwayTicketManager.config;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @Author: meizx
 * @ClassName: MongoDBClient
 * @Date: 2019/11/21 13:48
 * @E-mail： meizxbj@gmail.com
 * @Description: MongoDBClient
 * @Version: V1.0.0
 */
@Component
public class MongoDBClient<T> implements MongoRepository {

    @Override
    public List saveAll(Iterable iterable) {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void deleteAll(Iterable iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List findAll(Sort sort) {
        return null;
    }

    @Override
    public Page findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List findAll(Example example, Sort sort) {
        return null;
    }

    @Override
    public List findAll(Example example) {
        return null;
    }

    @Override
    public List insert(Iterable iterable) {
        return null;
    }

    @Override
    public Object insert(Object o) {
        return null;
    }

    @Override
    public Optional findOne(Example example) {
        return Optional.empty();
    }

    @Override
    public Page findAll(Example example, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Example example) {
        return 0;
    }


    @Override
    public boolean exists(Example example) {
        return false;
    }
}
