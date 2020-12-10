package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserDao userDao;

    public UserServiceImp() {

    }

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    @Transactional
    public List<User> showAll() {
        return userDao.showAll();
    }

    @Transactional
    @Override
    public void addAndSave(User user) {
        userDao.addAndSave(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    public void setUserRoles(User user) {
        user.setRoles(user.getRoles().stream().map(role -> userDao.getUserByRoleName(role.getRolename()))
                .collect(Collectors.toSet()));

    }

}
