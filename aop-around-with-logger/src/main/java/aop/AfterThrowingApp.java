package aop;

import aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> accounts = null;

        try{
            // add a boolean flag to simulate exception
            boolean tripWire = true;
            accountDAO.findAccounts(tripWire);
        } catch (Exception exc){
            System.out.println("\n\nMain Program ... caught exception: " + exc);
        }



        // display the accounts
        System.out.println("\n\nMain Program: AfterReturningApp");
        System.out.println("----");

        System.out.println(accounts);
        System.out.println("\n");

        // close the context
        context.close();
    }
}
