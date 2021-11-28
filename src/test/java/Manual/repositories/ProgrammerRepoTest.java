package Manual.repositories;

import Manual.daos.Programmer;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProgrammerRepoTest extends ProgrammerRepo {
    private static ProgrammerRepo repo = new ProgrammerRepo();
    private static List<Programmer> programmersTest = new ArrayList<Programmer>();


    @BeforeAll
    static void fillProgrammersTest ()
    {
        programmersTest.add(new Programmer(1, "Sergio Pérez", LocalDateTime.parse("2020-12-21 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "a3b3c6aa9b3d07a7bf61e44b9ef5a8d1eb1f6c0fc6ceaaff8ffc1e9b1708e0cb", "Java;C", 3000, 1));
        programmersTest.add(new Programmer(2, "Federico Toledo", LocalDateTime.parse("2019-08-18 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "006d060ed77eb19c0161ceedf2c465a185c9ee7a5860bde9c91ae7c836e50267", "Java;C#;C++;Javascript", 2000, 2));
        programmersTest.add(new Programmer(3, "Juanito Unitario", LocalDateTime.parse("2018-08-10 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e65d46ee325da5e4626913a382028d7b38336048544fa143c667bbf4c8ac6f63", "Java", 1500, 3));
        programmersTest.add(new Programmer(4, "Iria Manzanara", LocalDateTime.parse("2021-01-20 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6a0f4c8407c5dc66f3d43e1cb8154a6b7671ac6a31ba9e636ab43119e5899e3f", "C;C++;Javascript", 2000, 4));
        programmersTest.add(new Programmer(5, "Alonso Mateo", LocalDateTime.parse("2020-12-29 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "2d4fa8182ee9f5b59555ea8777b0f592aa4bd0bc80d332e7c328aa4df4c0f62b", "Java;Python", 1500, 1));
        programmersTest.add(new Programmer(6, "Vicente Vago", LocalDateTime.parse("2020-02-27 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "e9739cca7c3a6f780d4ebac52cde92e19e92e19629990116106c7380b0ffcaf0", "Java", 1500, 2));
        programmersTest.add(new Programmer(7, "Mavi López", LocalDateTime.parse("2021-03-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "c8a101fe59dd547a9488019e904c7c1fccfea1a72db82b4f6cd9ac0c815082f5", "Java;C;C++", 1250, 3));
        programmersTest.add(new Programmer(8, "Katarina Loto", LocalDateTime.parse("2020-12-11 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "86de21307fd8ffd1049d2cac37df814af23535277422dcb1538be6c62819a18e", "C++", 1000, 4));
        programmersTest.add(new Programmer(9, "Ruben Pereda", LocalDateTime.parse("2020-02-10 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "982183af3549b015f2504cf9f61294e20bb83d02fe00c226fc55d396dfe2da9c", "Java;C;Python", 1500, 1));
        programmersTest.add(new Programmer(10, "Milagros García", LocalDateTime.parse("2020-04-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "cd61ff0f7dff7085c4c68fc71057c938d10925828060298fb51a2272442bd30e", "Java", 1500, 2));
        programmersTest.add(new Programmer(11, "Ulises Grande", LocalDateTime.parse("2018-05-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "a121aefd62de1770cf4f75deb7f24d5a54a201b551101f55ca7e5e6e2f138c16", "C;Python", 1250, 3));
        programmersTest.add(new Programmer(12, "Clementina Pérez", LocalDateTime.parse("2021-06-11 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "b77cb1f557a9c46e1bd5b993218995c3fedd832bd083c1c0e7d05569c9c0a196", "Java;C++", 1000, 4));
        programmersTest.add(new Programmer(13, "Lucia Manzano", LocalDateTime.parse("2021-03-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "b518b6002ca58d6f5245bd1a5df30ab3441092946f08294592927c397ca5c954", "Python", 1500, 1));
        programmersTest.add(new Programmer(14, "Carlos Carlanga", LocalDateTime.parse("2020-08-05 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "504826d1c03f569926d280ce32b913bcf72008a4bc566de58e0e52fde17823b4", "Java", 1000, 2));
        programmersTest.add(new Programmer(15, "Eliseo Santo", LocalDateTime.parse("2021-10-20 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6f07c1f8a614488dc3d15fb3606a6be2735b2c5bf6796b12dcf4a967bfb9ae1e", "C;Python", 1200, 3));
        programmersTest.add(new Programmer(16, "Jesus Matamoros", LocalDateTime.parse("2020-03-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6917891fafbee3da49e397ce79d08566e319e48619b82ff69474afdce921c1bf", "Java;C++", 1200, 4));
    }

    @Test
    @Order(1)
    void testFindAll() {
        try{
            Optional<List<Programmer>> programmers = repo.findAll();
            Assertions.assertArrayEquals(programmers.orElse(null).toArray(), programmersTest.toArray());
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(2)
    void testGetById() {
        try{
            Optional<Programmer> programmer = repo.getById(13L);
            Assertions.assertEquals(programmer.orElse(null), programmersTest.get(12));
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(3)
    void testInsert() {
        try{
            Programmer programmerToInsert =  new Programmer(17, "Jesus Matamoros", LocalDateTime.parse("2020-03-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6917891fafbee3da49e397ce79d08566e319e48619b82ff69474afdce921c1bf", "Java;C++", 1200, 1);
            Optional<Programmer> programmer = repo.insert(programmerToInsert);
            Assertions.assertEquals(programmer.orElse(null), programmerToInsert);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    void testUpdate() {
        try{
            Programmer programmerToUpdate =  new Programmer(17, "Jesus Matacorros", LocalDateTime.parse("2020-03-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6917891fafbee3da49e397ce79d08566e319e48619b82ff69474afdce921c1bf", "Java;C++", 1200, 1);
            Optional<Programmer> programmer = repo.update(programmerToUpdate);
            Assertions.assertEquals(programmer.orElse(null), programmerToUpdate);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        try{
            Programmer programmerToDelete =  new Programmer(17, "Jesus Matacorros", LocalDateTime.parse("2020-03-12 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "6917891fafbee3da49e397ce79d08566e319e48619b82ff69474afdce921c1bf", "Java;C++", 1200, 1);
            Optional<Programmer> programmer = repo.delete(programmerToDelete);
            Assertions.assertEquals(programmer.orElse(null), programmerToDelete);
        }catch(SQLException e) {
            Assertions.fail();
        }
    }
}