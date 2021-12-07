//package Manual.repositories;
//
//import Manual.daos.Issue;
//import Manual.daos.Repository;
//import org.junit.jupiter.api.*;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class IssueRepoTest extends IssueRepo {
//    private static IssueRepo repo = new IssueRepo();
//    private static List<Issue> issueList = new ArrayList<>();
//    @BeforeAll
//    static void setUp() {
//        issueList.add(new Issue(1,"GnomeAIRepositoryIssue1","No sabiamos que hacer, el almacen estaba en llamas",
//                LocalDateTime.parse("2021-02-09 10:42:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "solved",	1,	1));
//        issueList.add(new Issue(2,"GnomeAIRepositoryIssue2","Necesitamos un generador de gnomos",
//                LocalDateTime.parse("2021-03-09 14:41:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "open",	1,	1));
//        issueList.add(new Issue(3,"GnomeUIRepositoryIssue1","Necesitamos pinceles digitales",
//                LocalDateTime.parse("2019-12-11 10:03:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "solved",	2,	2));
//        issueList.add(new Issue(4,"GnomeUIRepositoryIssue2","No sabemos pintar",
//                LocalDateTime.parse("2020-01-07 08:01:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "open",	2,	2));
//        issueList.add(new Issue(5,"GnomeTesterRepositoryIssue1","Null pointer exception está causando caos",
//                LocalDateTime.parse("2021-02-09 12:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "solved",	3,	3));
//        issueList.add(new Issue(6,"GnomeTesterRepositoryIssue2","Necesitamos un framework de modelos 5D",
//                LocalDateTime.parse("2021-05-19 09:05:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "open",	3,	3));
//        issueList.add(new Issue(7,"GnomeMakerRepositoryIssue1","Necesitamos crackear una ps5",
//                LocalDateTime.parse("2021-02-09 20:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "solved",	4,	4));
//        issueList.add(new Issue(8,"GnomeMakerRepositoryIssue2","Se nos ha jodido la ps5 por crackearla",
//                LocalDateTime.parse("2021-02-11 08:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "open",	4,	4));
//    }
//
//    @Test
//    @Order(1)
//    void testfindAll() {
//        try{
//            Optional<List<Issue>> issueListToFindAll = repo.findAll();
//            assertArrayEquals(issueListToFindAll.orElse(null).toArray(), issueList.toArray());
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testgetById() {
//        try{
//            Optional<Issue> issueToGetById = repo.getById(1L);
//            Assertions.assertEquals(issueToGetById.orElse(null), issueList.get(0));
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testinsert() {
//        try{
//            Issue issueToInsert =  new Issue(9,"GnomeTestIssue","test",
//                    LocalDateTime.parse("2021-02-11 08:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    "open",	4,	4);
//            Optional<Issue> issueOptional = repo.insert(issueToInsert);
//            Assertions.assertEquals(issueOptional.orElse(null), issueToInsert);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(4)
//    void testupdate() {
//        try{
//            Issue issueToUpdate =  new  Issue(9,"GnomeTestIssue1","test1",
//                            LocalDateTime.parse("2021-02-11 09:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                            "open",	4,	4);
//            Optional<Issue> issueOptional = repo.update(issueToUpdate);
//            Assertions.assertEquals(issueOptional.orElse(null), issueToUpdate);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(5)
//    void testdelete() {
//        try{
//        Issue issueToDelete =  new  Issue(9,"GnomeTestIssue1","test1",
//                LocalDateTime.parse("2021-02-11 09:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                "open",	4,	4);
//        Optional<Issue> issueOptional = repo.delete(issueToDelete);
//        Assertions.assertEquals(issueOptional.orElse(null), issueToDelete);
//    }catch(SQLException e) {
//        Assertions.fail();
//    }
//    }
//}