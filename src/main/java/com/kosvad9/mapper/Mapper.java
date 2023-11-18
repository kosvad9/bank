package com.kosvad9.mapper;

public interface Mapper<F, T> {
    T map(F value);
}
