//package Manual.repositories;
//
//import Manual.daos.Repository;
//import Manual.dtos.RepositoryDTO;
//import org.junit.jupiter.api.*;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class RepositoryRepoTest extends RepositoryDTO {
//    private static RepositoryRepo repository = new RepositoryRepo();
//    private static List<Repository> repositories = new ArrayList<Repository>();
//    @BeforeAll
//    static void setUp() {
//        repositories.add(new Repository(1,
//                "GnomeAIRepository",
//                LocalDateTime.parse("2020-12-11 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                1));
//        repositories.add(new Repository(2,
//                      "GnomeUIRepository",
//                LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                2));
//        repositories.add(new Repository(3,
//                "GnomeTesterRepository",
//                LocalDateTime.parse("2021-02-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                3));
//        repositories.add(new Repository(4,
//                "GnomeMakerRepository",
//                LocalDateTime.parse("2021-02-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                4));
//    }
//
//    @Test
//    @Order(1)
//    void testFindAll() {
//        try{
//            Optional<List<Repository>> repositoryList = repository.findAll();
//            assertArrayEquals(repositoryList.orElse(null).toArray(), repositories.toArray());
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testGetById() {
//        try{
//            Optional<Repository> repositoryToTest = repository.getById(1L);
//            Assertions.assertEquals(repositoryToTest.orElse(null), repositories.get(0));
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testInsert() {
//        try{
//            Repository repositoryToInsert =  new Repository(5,
//            "GnomeTest",
//                    LocalDateTime.parse("2021-02-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    4);
//            Optional<Repository> repositoryOptional = repository.insert(repositoryToInsert);
//            Assertions.assertEquals(repositoryOptional.orElse(null), repositoryToInsert);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(4)
//    void testUpdate() {
//            try{
//                Repository repositoryToUpdate =  new  Repository(5,
//                        "GnomeTest1",
//                        LocalDateTime.parse("2021-02-09 10:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                        4);
//                Optional<Repository> optionalRepository = repository.update(repositoryToUpdate);
//                Assertions.assertEquals(optionalRepository.orElse(null), repositoryToUpdate);
//            }catch(SQLException e) {
//                Assertions.fail();
//            }
//
//    }
//
//    @Test
//    @Order(5)
//    void delete() {
//        try{
//            Repository repositoryToDelete =  new Repository(5,
//                    "GnomeTest1",
//                    LocalDateTime.parse("2021-02-09 10:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    4);
//            Optional<Repository> repositoryOptional = repository.delete(repositoryToDelete);
//            Assertions.assertEquals(repositoryOptional.orElse(null), repositoryToDelete);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//}