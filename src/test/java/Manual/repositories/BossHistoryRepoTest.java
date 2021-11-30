package Manual.repositories;

import Manual.daos.BossHistory;
import org.junit.jupiter.api.*;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BossHistoryRepoTest extends BossHistoryRepo {
    private static BossHistoryRepo repo = new BossHistoryRepo();
    private static List<BossHistory>  bossHistoryList = new ArrayList<>();

    @BeforeAll
    static void fillBossHistory(){
        bossHistoryList.add(new BossHistory(1,	1,
                LocalDateTime.parse("2021-02-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),	null));
        bossHistoryList.add(new BossHistory(1, 2,
                LocalDateTime.parse("2020-12-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2021-02-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bossHistoryList.add(new BossHistory(2,	2,
                LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),	null));
        bossHistoryList.add(new BossHistory(2, 3,
                LocalDateTime.parse("2019-08-18 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bossHistoryList.add(new BossHistory(3,	3,
                LocalDateTime.parse("2019-04-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),	null));
        bossHistoryList.add(new BossHistory(3,	4,
                LocalDateTime.parse("2018-08-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2019-04-11 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bossHistoryList.add(new BossHistory(4,	1,
                LocalDateTime.parse("2021-01-20 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2021-03-25 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        bossHistoryList.add(new BossHistory(4,	4,
                LocalDateTime.parse("2021-03-25 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),	LocalDateTime.parse("1000-01-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }
    @Test
    @Order(1)
    void testFindAll() {
        try{
            Optional<List<BossHistory>> bossHistories = repo.findAll();
            assertArrayEquals(bossHistories.orElse(null).toArray(), bossHistoryList.toArray());
        }catch(SQLException e) {
            fail();
        }
    }

    @Test
    @Order(2)
    void testGetById() {
        try{
            Optional<BossHistory> bossHistory = repo.getById(1L,2L,LocalDateTime.parse("2020-12-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            assertEquals(bossHistory.orElse(null), bossHistoryList.get(1));
        }catch(SQLException e) {
            fail();
        }
    }

    @Test
    @Order(3)
    void testInsert() {
        try{
            BossHistory bossHistory =  new BossHistory(4,1, LocalDateTime.parse("2020-12-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),LocalDateTime.parse("1000-01-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Optional<BossHistory> historyOptional = repo.insert(bossHistory);
            Assertions.assertEquals(historyOptional.orElse(null), bossHistory);
        }catch(SQLException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    void testUpdate() {
        try{
            BossHistory bossHistoryToUpdate =  new BossHistory(4,1, LocalDateTime.parse("2020-12-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),LocalDateTime.parse("1000-01-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Optional<BossHistory> bossHistory = repo.update(bossHistoryToUpdate);
            Assertions.assertEquals(bossHistory.orElse(null), bossHistoryToUpdate);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        try{
            BossHistory bossHistoryToDelete =  new BossHistory(4,1, LocalDateTime.parse("2020-12-21 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),LocalDateTime.parse("1000-01-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Optional<BossHistory> bossHistory = repo.delete(bossHistoryToDelete);
            Assertions.assertEquals(bossHistory.orElse(null), bossHistoryToDelete);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }
}