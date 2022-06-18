/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.fptqna.dao;

import swp391.fptqna.dto.UserDTO;

/**
 *
 * @author LamHung
 */
public class UserService {

    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public String checkLogin (String email, String password) {
        boolean check = userDao.checkLogin(email, password);
        if (check) { 
            return "SUCCESS";
        } else {
            return "Invalid email or password.";
        }
    }
    
    public boolean insertUser(String email, String fullName, String password){
        boolean confirm = userDao.createUser(email,fullName, password);
        if (confirm) {
            return true;
        }else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

}
