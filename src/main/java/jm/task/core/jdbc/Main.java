package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = null;

        try {
            userService = new UserServiceImpl();

            userService.createUsersTable();

            userService.saveUser("Вася","Васильев",(byte) 23);
            userService.saveUser("Петя","Петров",(byte) 24);
            userService.saveUser("Ваня","Иванов",(byte) 25);
            userService.saveUser("Дмитрий","Дмитров",(byte) 26);

            List<User> userList = userService.getAllUsers();
            userList.forEach(p -> System.out.println(p));

            userService.cleanUsersTable();
            userService.dropUsersTable();

            userService.close();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
