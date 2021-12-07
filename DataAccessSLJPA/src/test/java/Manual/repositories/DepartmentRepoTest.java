//package Manual.repositories;
//
//
//import Manual.daos.Department;
//import Manual.daos.Issue;
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
//class DepartmentRepoTest extends DepartmentRepo {
//    private static DepartmentRepo repo = new DepartmentRepo();
//    private static List<Department> departmentList = new ArrayList<>();
//    @BeforeAll
//      static void setUp() {
//        departmentList.add(new Department(1,	"Inteligencia Artificial",	1,	200000));
//        departmentList.add(new Department(2,	"Interfaces",	2,	100000));
//        departmentList.add(new Department(3,	"Software de pruebas",	3,	120000));
//        departmentList.add(new Department(4,	"Desarrollo de servicios nativos",	4,	140000));
//    }
//
//    @Test
//    @Order(1)
//    void testFindAll() {
//        try{
//            Optional<List<Department>> DepartmentListToFindAll = repo.findAll();
//            assertArrayEquals(DepartmentListToFindAll.orElse(null).toArray(), departmentList.toArray());
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testGetById() {
//        try{
//            Optional<Department> departmentGetById = repo.getById(1L);
//            Assertions.assertEquals(departmentGetById.orElse(null), departmentList.get(0));
//        }catch(SQLException e) {
//            fail();
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testInsert() {
//        try{
//            Department departmentToInsert =  new Department(5,	"test",	4,	140000);
//        Optional<Department> departmentOptional = repo.insert(departmentToInsert);
//        Assertions.assertEquals(departmentOptional.orElse(null), departmentToInsert);
//    }catch(SQLException e) {
//        Assertions.fail();
//    }
//    }
//
//    @Test
//    @Order(4)
//    void testUpdate() {
//        try{
//            Department departmentToUpdate =  new  Department(5,	"test1",	4,	150000);
//        Optional<Department> departmentOptional = repo.update(departmentToUpdate);
//        Assertions.assertEquals(departmentOptional.orElse(null), departmentToUpdate);
//    }catch(SQLException e) {
//        Assertions.fail();
//    }
//    }
//
//    @Test
//    @Order(5)
//    void testDelete() {
//        try{
//            Department DepartmentToDelete =  new  Department(5,	"test1",	4,	150000);
//            Optional<Department> departmentOptional = repo.delete(DepartmentToDelete);
//            Assertions.assertEquals(departmentOptional.orElse(null), DepartmentToDelete);
//        }catch(SQLException e) {
//            Assertions.fail();
//        }
//    }
//}