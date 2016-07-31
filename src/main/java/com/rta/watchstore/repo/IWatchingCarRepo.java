package com.rta.watchstore.repo;

import java.util.List;

public interface IWatchingCarRepo {

    void save(String name, String carId);

    List<String> findAllForPerson(String name);

    void delete(String name, String carId);
}
