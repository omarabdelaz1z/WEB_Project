package Entities.CRUD;

import java.util.List;

public interface ICRUD<T> {
    T create(T object);
    T read(String ID);
    List<T> read();
    T update(String ID, T object);
    void delete(String ID);
    List<T> query(String query);
}
