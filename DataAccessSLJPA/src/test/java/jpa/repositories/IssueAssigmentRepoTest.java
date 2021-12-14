//package Manual.repositories;
//
//import Manual.daos.Issue;
//import Manual.daos.IssueAssignment;
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
//class IssueAssigmentRepoTest extends IssueAssigmentRepo {
//    private static IssueAssigmentRepo repo = new IssueAssigmentRepo();
//    private static List<IssueAssignment> issueAssignmentList = new ArrayList<>();
//    @BeforeAll
//    static void setUp() {
//        issueAssignmentList.add(new IssueAssignment(1, 9,	1,
//                LocalDateTime.parse("2021-02-09 10:42:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(2, 13,	1,
//                LocalDateTime.parse("2021-02-09 10:42:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(3, 9,	2,
//                LocalDateTime.parse("2021-03-09 14:41:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(4, 13,	2,
//                LocalDateTime.parse("2021-03-09 14:41:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(5, 10,	3,
//                LocalDateTime.parse("2019-12-11 10:03:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(6, 14,	3,
//                LocalDateTime.parse("2019-12-11 10:03:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(7, 10,	4,
//                LocalDateTime.parse("2020-01-07 08:01:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(8, 14,	4,
//                LocalDateTime.parse("2020-01-07 08:01:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(9, 11,	5,
//                LocalDateTime.parse("2021-02-09 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(10, 15,	5,
//                LocalDateTime.parse("2021-02-09 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(11, 11,	6,
//                LocalDateTime.parse("2021-05-19 09:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(12, 15,	6,
//                LocalDateTime.parse("2021-05-19 09:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(13, 12,	7,
//                LocalDateTime.parse("2021-02-09 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(14, 16,	7,
//                LocalDateTime.parse("2021-02-09 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(15, 12,	8,
//                LocalDateTime.parse("2021-02-11 08:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        issueAssignmentList.add(new IssueAssignment(16, 16,	8,
//                LocalDateTime.parse("2021-02-11 08:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//    }
//
//    @Test
//    @Order(1)
//    void testFindAll() {
//        try{
//            Optional<List<IssueAssignment>> issueAssignmentListToFindAll = repo.findAll();
//            assertArrayEquals(issueAssignmentListToFindAll.orElse(null).toArray(), issueAssignmentList.toArray());
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testGetById() {
//        try{
//            Optional<IssueAssignment> issueAssignmentToGetById = repo.getById(1L);
//            Assertions.assertEquals(issueAssignmentToGetById.orElse(null), issueAssignmentList.get(0));
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testInsert() {
//        try{
//            IssueAssignment issueAssignmentToInsert =  new IssueAssignment(17, 16,	8,
//                    LocalDateTime.parse("2021-10-10 10:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            Optional<IssueAssignment> issueAssignmentOptional = repo.insert(issueAssignmentToInsert);
//            Assertions.assertEquals(issueAssignmentOptional.orElse(null), issueAssignmentToInsert);
//        }catch(SQLException e) {
//            e.printStackTrace();
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(4)
//    void testUpdate() {
//        try{
//            IssueAssignment issueAssignmentToUpdate =  new  IssueAssignment(17, 16,	8,
//                    LocalDateTime.parse("2021-10-10 10:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            Optional<IssueAssignment> issueAssignmentOptional = repo.update(issueAssignmentToUpdate);
//            Assertions.assertEquals(issueAssignmentOptional.orElse(null), issueAssignmentToUpdate);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(5)
//    void testDelete() {
//        try{
//            IssueAssignment issueAssignmentToDelete =  new  IssueAssignment(17, 16,	8,
//                    LocalDateTime.parse("2021-10-10 10:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        Optional<IssueAssignment> issueAssignmentOptional = repo.delete(issueAssignmentToDelete);
//        Assertions.assertEquals(issueAssignmentOptional.orElse(null), issueAssignmentToDelete);
//    }catch(SQLException e) {
//        Assertions.fail();
//    }
//    }
//}