package dao;

public interface DAO<E> {

    void save(E e);
    void update(E e);
    void delete(E e);
}