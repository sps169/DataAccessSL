package Manual.repositories;

import Manual.daos.Project;
import Manual.daos.ProjectAssignment;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectAssignmentRepoTest extends ProjectAssignmentRepo {
    private static ProjectAssignmentRepo repo = new ProjectAssignmentRepo();
    private static List<ProjectAssignment> projectAssignmentList = new ArrayList<>();
    @BeforeAll
    static void setUp() {
        projectAssignmentList.add(new ProjectAssignment(9,1,
                LocalDateTime.parse("2020-12-11 00:00:00",	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
        projectAssignmentList.add(new ProjectAssignment(10,2,
                LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2020-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        projectAssignmentList.add(new ProjectAssignment(11,3,
                LocalDateTime.parse("2021-02-09 00:00:00",	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
        projectAssignmentList.add(new ProjectAssignment(12,4,
                LocalDateTime.parse("2021-02-09 00:00:00",	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
        projectAssignmentList.add(new ProjectAssignment(13,1,
                LocalDateTime.parse("2021-02-01 00:00:00",	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
        projectAssignmentList.add(new ProjectAssignment(14,2,
                LocalDateTime.parse("2020-12-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2020-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        projectAssignmentList.add(new ProjectAssignment(15,3,
                LocalDateTime.parse("2021-02-12 00:00:00",	DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
        projectAssignmentList.add(new ProjectAssignment(16,4,
                LocalDateTime.parse("2021-02-11 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null));
    }

    @Test
    @Order(1)
    void testFindAll() {
        try{
            Optional<List<ProjectAssignment>> projectAssignmentToFindAll = repo.findAll();
            Assertions.assertArrayEquals(projectAssignmentToFindAll.orElse(null).toArray(), projectAssignmentList.toArray());
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(2)
    void testGetById() {
        try{
        Optional<ProjectAssignment> projectAssignmentToGetById = repo.getById(10L,2L,
                LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Assertions.assertEquals(projectAssignmentToGetById.orElse(null), projectAssignmentList.get(1));
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(3)
    void testInsert() {
        try{
            ProjectAssignment projectAssignmentToInsert =  new ProjectAssignment(16,1,
                    LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse("2020-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Optional<ProjectAssignment> projectAssignmentOptional = repo.insert(projectAssignmentToInsert);
            Assertions.assertEquals(projectAssignmentOptional.orElse(null), projectAssignmentToInsert);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    void testUpdate() {
        try{
            ProjectAssignment projectAssignmentToUpdate =  new ProjectAssignment(16,1,
                    LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse("2020-12-12 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Optional<ProjectAssignment> projectAssignmentOptional = repo.update(projectAssignmentToUpdate);
            Assertions.assertEquals(projectAssignmentOptional.orElse(null), projectAssignmentToUpdate);
        }catch(SQLException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        try{
            ProjectAssignment projectAssignmentToDelete =  new ProjectAssignment(16,1,
                    LocalDateTime.parse("2019-12-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse("2020-12-12 01:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Optional<ProjectAssignment> projectAssignmentOptional = repo.delete(projectAssignmentToDelete);
        Assertions.assertEquals(projectAssignmentOptional.orElse(null), projectAssignmentToDelete);
        }catch(SQLException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}