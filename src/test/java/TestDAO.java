import com.example.UserApp.dao.UserDao;
import com.example.UserApp.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    UserDao userDao = new UserDao();
    User user = new User("Иван", "Иванов", 55);

    @Test
    void addUserTest() {
//        userDao.create(user);
    }

    @Test
    void getUserTest() {
//        userDao.getByID(10);
    }

    @Test
    void getAllTest() {
        List<User> users = userDao.getAll();
        for (User e :
                users) {
            System.out.println(e.toString());
        }
    }

    @Test
    void updateUserTest() {
        User temp = userDao.getByID(3);
        temp.setName("Neivan");
        temp.setAge(65);
        System.out.println(temp.toString());
        userDao.update(temp);
    }

    @Test
    void deleteUserTest() {
//        userDao.delete(9);
    }
}
