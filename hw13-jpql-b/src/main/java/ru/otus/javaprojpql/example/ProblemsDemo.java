package ru.otus.javaprojpql.example;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.otus.javaprojpql.entity.Category;

import java.util.List;

public class ProblemsDemo {

    public static void main(String[] args) {
//        lazyInitializationExceptionDemo();
//        nPlusOneProblemDemo();
//        fetchSolutionDemo();
        entityGraphSolutionDemo();
    }

    public static void lazyInitializationExceptionDemo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

//        Найти все категории (N) - курсы сразу не загружаются
        List<Category> categoryList = entityManager
                .createQuery("SELECT C FROM Category C", Category.class)
                .getResultList();

        entityManager.close();

//        При обращении к курсам за рамками сеанса получаем LazyInitializationException
//        Можно решить установкой fetch = FetchType.EAGER в @OneToMany
        for (Category category : categoryList) {
            System.out.println(category.getCourseSet().size());
        }
    }

    public static void nPlusOneProblemDemo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

//        Найти все категории (N) - курсы сразу не загружаются
        List<Category> categoryList = entityManager
                .createQuery("SELECT C FROM Category C", Category.class)
                .getResultList();

//        При обращении к курсам в течениее сеанса происходит "ленивая" загрузка курсов по каждой категории отдельно
//        Можно решить проблему с использованием @Fetch(FetchMode.SUBSELECT) рядом с @OneToMany - не решает LazyInitializationException
        for (Category category : categoryList) {
            System.out.println(category.getCourseSet().size());
        }

        entityManager.close();
    }

    public static void fetchSolutionDemo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

//        Найти все категории (N) - курсы загружаются сразу
        List<Category> categoryList = entityManager
                .createQuery("SELECT C FROM Category C LEFT JOIN FETCH C.courseSet", Category.class)
                .getResultList();

        entityManager.close();

//        При обращении к курсам не требуется дополнительная загрузка
        for (Category category : categoryList) {
            System.out.println(category.getCourseSet().size());
        }
    }

    public static void entityGraphSolutionDemo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

//        Создаем указание на необходимость загружат именнованный ентити-граф - решает проблему LazyInitializationException
        EntityGraph entityGraph = entityManager.getEntityGraph("category-entity-graph");

//        Найти все категории (N) - курсы загружаются сразу
        List<Category> categoryList = entityManager
                .createQuery("SELECT C FROM Category C", Category.class)
                .setHint("jakarta.persistence.fetchgraph", entityGraph)
                .getResultList();

        entityManager.close();

//        При обращении к курсам не требуется дополнительная загрузка
        for (Category category : categoryList) {
            System.out.println(category.getCourseSet().size());
        }
    }
}
