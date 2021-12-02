package Manual.repositories;

import Manual.daos.Commit;
import Manual.daos.Issue;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommitRepoTest extends CommitRepo {
    private static CommitRepo repo = new CommitRepo();
    private static List<Commit> commitList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        commitList.add(new Commit(1,"Los buenos matafuegos",
                "Problema solucionado con los buenos matafuegos, mitad de las cosas quemadas",
                LocalDateTime.parse("2021-02-09 15:42:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                1,9,1));
        commitList.add(new Commit(2,"El jefe nos ha traido los pinceles",
                "Aunque le parecia un desperdicio, el jefe de mala gana nos dio unos cuantos",
                LocalDateTime.parse("2019-12-11 16:03:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                2,10,3));
        commitList.add(new Commit(3,"hemos puesto random a todo, solucinado",
                "entre nada y el azar decidimos rellenarlo todo con aleatorios",
                LocalDateTime.parse("2021-02-09 21:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                3,	11,	5));
        commitList.add(new Commit(4,"la ps5 hizo crack asi que ahora tiene tiritas",
                "despues de unos cuantos martillazos le logramos crackear al viejo estilo",
                LocalDateTime.parse("2021-02-09 23:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                4,	12,	7));
    }

    @Test
    @Order(1)
    void testFindAll() {
        try{
            Optional<List<Commit>> commitListToFindAll = repo.findAll();
            assertArrayEquals(commitListToFindAll.orElse(null).toArray(), commitList.toArray());
        }catch(SQLException e) {
            fail();
        }
    }

    @Test
    @Order(2)
    void testGetById() {
        try{
        Optional<Commit> commitOptional = repo.getById(1L);
        Assertions.assertEquals(commitOptional.orElse(null), commitList.get(0));
    }catch(SQLException e) {
        fail();
    }
    }

    @Test
    @Order(3)
    void testInsert() {
        try{
            Commit commitToInsert =  new Commit(5,"test", "test",
                    LocalDateTime.parse("2021-02-09 23:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    4,	12,	7);
        Optional<Commit> commitOptional = repo.insert(commitToInsert);
        Assertions.assertEquals(commitOptional.orElse(null), commitToInsert);
    }catch(SQLException e) {
        Assertions.fail();
    }
    }

    @Test
    @Order(4)
    void testUpdate() {
        try{
            Commit commitToUpdate =  new  Commit(5,"test1", "test1",
                    LocalDateTime.parse("2021-01-09 21:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    4,	12,	7);
            Optional<Commit> commitOptional = repo.update(commitToUpdate);
            Assertions.assertEquals(commitOptional.orElse(null), commitToUpdate);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    void testDelete() {   try{
        Commit commitToDelete =  new  Commit(5,"test1", "test1",
                LocalDateTime.parse("2021-01-09 21:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                4,	12,	7);
        Optional<Commit> commitOptional = repo.delete(commitToDelete);
        Assertions.assertEquals(commitOptional.orElse(null), commitToDelete);
    }catch(SQLException e) {
        Assertions.fail();
    }
    }
}