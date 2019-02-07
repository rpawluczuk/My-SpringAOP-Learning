package aop;

import aop.dao.AccountDAO;
import aop.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get membership bean from spring container
        MembershipDAO membershipDao =
                context.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        Account account = new Account();
        account.setName("Adam");
        account.setLevel("Platinum");

        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        // call the accountdao getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();
        String code = accountDAO.getServiceCode();

        // call the membership business method
        membershipDao.addSillyMember();
        membershipDao.goToSleep();

        // close the context
        context.close();
    }
}
