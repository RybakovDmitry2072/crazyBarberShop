package org.example.crazybarbershop.map;

public interface Mapper <T, F>{
    T mapFrom(F f);
}
