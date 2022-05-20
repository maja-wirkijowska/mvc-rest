package mvcrest.bootstrap;

import mvcrest.domain.Category;
import mvcrest.repos.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepo categoryRepo;

    public Bootstrap(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {

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
