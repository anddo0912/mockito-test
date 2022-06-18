/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package swp391.fptqna;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import swp391.fptqna.dao.UserDAO;
import swp391.fptqna.dao.UserService;

/**
 *
 * @author LamHung
 */
//@RunWith(Parameterized.class)
//@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    
    @Mock
    private UserDAO userDAO;
    
    @Rule //bang voi (@RunWith(MockitoJUnitRunner.class))
    public MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks // bang voi (userService = new UserService(userDAO);)
    private UserService userService;
    
    @Before //chay truoc @test
    public void init() throws Exception {
        
        Mockito.when(userDAO.checkLogin("ha@fpt.edu.vn","123")).thenReturn(true);
//        Mockito.when(userDAO.checkLogin("thay@fpt.edu.vn","456")).thenReturn(true);
//        Mockito.when(userDAO.createUser("thay@fpt.edu.vn","Nguyen Hoang","789")).thenReturn(false);
        Mockito.when(userDAO.createUser("thay@fpt.edu.vn","Nguyen Hoang","7898")).thenThrow(IllegalArgumentException.class);
        
    }
    
    
    @Test
    public void testCheckLoginWithRightArgumentReturnTrue() {
        //gia ham userDAO checkLogin de su dung trong userService 
        String email = "ha@fpt.edu.vn";
        String password = "123";
        Assert.assertEquals("SUCCESS", userService.checkLogin(email, password));
    }
    
    
    @Test
    public void testCheckLoginWithRightArgumentReturnTrue2() {
        String email = "ha@fpt.edu.vn";
        String password = "123";
        String actual = userService.checkLogin(email, password);
        //xem thu thang userDAO co dc goi trong thang userService hay 
        //ko ( va neu dc goi thi goi bao nhieu lan
        verify(userDAO,times(1)).checkLogin(email, password);
        Assert.assertEquals("SUCCESS", actual);
        
    }
   
    @Test
    public void testCheckLoginWithWrongEmailReturnFailed(){
        String email = "thay@fpt.edu.vn";
        String password = "123";
        Assert.assertEquals("Invalid email or password.", userService.checkLogin(email, password));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInsertUserWithWrongEmailReturnException(){
        
        userService.insertUser("thay@fpt.edu.vn","Nguyen Hoang","7898");
    }
//    
    @Test
    public void testInsertUserButCatchTheException() throws Exception {
        
        try {
            userService.insertUser("thay@fpt.edu.vn","Nguyen Hoang","789t");
        } catch (Exception e) {
            Assert.assertEquals("Invalid email", e.getMessage());
        }
    }
//    
//    chua dung dc .-.
//    @Parameterized.Parameters
//    public static Object[][] data(){
//        return new Integer[][]{
//                {0,120},
//                {2,123},
//                {3,123},
//        };
//    }
//
//    @Parameterized.Parameter(value = 0)
//    public int input;
//    
//    
//    @Parameterized.Parameter(value = 1)
//    public int returnInput;
//    
////    @Parameterized.Parameter(value = 2)
////    public String expected;
//
//
//    @Test
//    public void testGetUserDDT() throws Exception {
//
//        
//        System.out.println(input);
////        Assert.assertEquals("SUCCESS", userService.createUser(input));
//    }
    
}
