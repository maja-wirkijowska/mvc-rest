package mvcrest.bootstrap;

import mvcrest.domain.Category;
import mvcrest.domain.Customer;
import mvcrest.repos.CategoryRepo;
import mvcrest.repos.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepo categoryRepo;
    private final CustomerRepo customerRepo;

    public Bootstrap(CategoryRepo categoryRepo, CustomerRepo customerRepo) {
        this.categoryRepo = categoryRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstname("Michael");
        c1.setLastname("Weston");
        customerRepo.save(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstname("Sam");
        c2.setLastname("Axe");
        customerRepo.save(c2);

        System.out.println("Customers Loaded: " + customerRepo.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepo.save(fruits);
        categoryRepo.save(dried);
        categoryRepo.save(fresh);
        categoryRepo.save(exotic);
        categoryRepo.save(nuts);

        System.out.println("Data Loaded: " + categoryRepo.count());
    }
}
