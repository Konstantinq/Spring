package org.example.app.services;

import java.util.List;

interface ProjectRepository<T> {
    List<T> retreiveAll();

    public void store(T book);

    boolean removeToItemById(Integer bookIdToRemove);

    void removeToItemByRegex(String regex);
}
