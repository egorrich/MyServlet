package services;

import model.User;

import java.util.List;

/**
 * Created by egor on 9.2.17.
 */
public interface IUserOperations {
    void create(User user);
    void update(User user);
    void delete(long id);
    List<User> findAll(int number);
    List<User> findAll();
    User findByName(String name);

    User findById(int id);
}
