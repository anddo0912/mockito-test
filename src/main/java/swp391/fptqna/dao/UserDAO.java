package swp391.fptqna.dao;

public interface UserDAO {
    
    //tao ham ko can code ben trong
    boolean checkLogin(String email, String password);
    
    boolean createUser(String email, String fullName, String password);
    
}
