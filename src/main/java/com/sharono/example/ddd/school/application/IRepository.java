package com.sharono.example.ddd.school.application;

public interface IRepository<T> {
    void store(T item);
    T load(int id);
}
