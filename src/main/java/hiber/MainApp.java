package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User alex = new User("Алексадр","Козлов","alex@tut.by" );
      alex.setCar(new Car("Mercedes",342004));
      userService.add(alex);

      User petr = new User("Петр","Соболевский","petr@mail.ru" );
      petr.setCar(new Car("Ferrari",4005));
      userService.add(petr);

      User den = new User("Денис","Клюев","den@list.com" );
      den.setCar(new Car("Lada",2310));
      userService.add(den);

      User olga = new User("Ольга","Тишкевич","ol@gmail.com");
      olga.setCar(new Car("Reno",56009));
      userService.add(olga);

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      User user1 = userService.getUser("Ferrari", 4005);
      System.out.printf("User: \n name - %s, \n surname - %s \n", user1.getFirstName(),user1.getLastName());

      User user2 = userService.getUser("Lada", 2310);
      System.out.printf("User: \n name - %s, \n surname - %s\n", user2.getFirstName(),user2.getLastName());

      context.close();
   }
}
