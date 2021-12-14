//package Manual.repositories;
//
//import Manual.daos.Project;
//import org.junit.jupiter.api.*;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ProjectRepoTest extends ProgrammerRepo {
//    private static ProjectRepo repo = new ProjectRepo();
//    private static List<Project> listProjects = new ArrayList<>();
//
//    @BeforeAll
//    static void setUp() {
//        listProjects.add(new Project(1,"GnomeAI",
//                LocalDateTime.parse("2020-12-11 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                null,"Python",150000,"active",5,1));
//        listProjects.add(new Project(2,"GnomeUI",
//                LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                LocalDateTime.parse("2020-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "Java", 90000,"ended",6,2));
//        listProjects.add(new Project(3,"GnomeTester",
//                LocalDateTime.parse("2021-02-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                null,"Java;CPlusPlus",100000,"active",7,3));
//        listProjects.add(new Project(4,"GnomeMaker",
//                LocalDateTime.parse("2021-02-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                null,"CPlusPlus",100000,"active",8,4));
//    }
//
//    @Test
//    @Order(1)
//    void testFindAll() {
//        try{
//            Optional<List<Project>> projectsToFindAll = repo.findAll();
//            Assertions.assertArrayEquals(projectsToFindAll.orElse(null).toArray(), listProjects.toArray());
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testGetById() {
//        try{
//            Optional<Project> projectsToGetById = repo.getById(1L);
//            Assertions.assertEquals(projectsToGetById.orElse(null), listProjects.get(0));
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testInsert() {
//        try{
//            Project projectToInsert =  new Project(5,"Gnometest",
//                    LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    LocalDateTime.parse("2020-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    "Java", 90000,"ended",6,2);
//            Optional<Project> projectOptional = repo.insert(projectToInsert);
//            Assertions.assertEquals(projectOptional.orElse(null), projectToInsert);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(4)
//    void testUpdate() {
//        try{
//            Project projectToUpdate =  new Project(5,"Gnometest1",
//                    LocalDateTime.parse("2019-12-11 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    LocalDateTime.parse("2020-12-11 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    "Java", 95000,"ended",6,2);
//            Optional<Project> projectOptional = repo.update(projectToUpdate);
//            Assertions.assertEquals(projectOptional.orElse(null), projectToUpdate);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(5)
//    void testDelete() {
//        try{
//            Project projectToDelete =  new Project(5,"Gnometest1",
//                    LocalDateTime.parse("2019-12-11 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    LocalDateTime.parse("2020-12-11 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    "Java", 95000,"ended",6,2);
//            Optional<Project> projectOptional = repo.delete(projectToDelete);
//            Assertions.assertEquals(projectOptional.orElse(null), projectToDelete);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//}