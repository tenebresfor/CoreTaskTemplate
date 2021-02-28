package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userService = new UserDaoHibernateImpl();
        userService.createUsersTable();
        System.out.println("Таблица создана");
        for (int i = 0; i < 4 ; i++) {
            userService.saveUser("M", "K", (byte) 30);
        }
        System.out.println("Пользователи внесены");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println("Таблица очищена");
        userService.dropUsersTable();
        System.out.println("Таблица удалена");
    }
}
