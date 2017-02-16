import services.IUserOperations;
import services.IUserOperationsImpl;

/**
 * Created by egor on 13.2.17.
 */
public class Main {
    public static void main(String[] args) {
        IUserOperations iUserOperations = new IUserOperationsImpl();
        System.out.println(iUserOperations.findByName("Egor"));
       // iUserOperations.delete(3);
       // iUserOperations.create(new User("Egor", "Ivanov"));

    }
}
