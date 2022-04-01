//package vtb.spring.repository;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Repository;
//import vtb.spring.repository.entities.OperaEntity;
//import vtb.spring.repository.interfaces.OperaRepository;
//
//import javax.swing.plaf.nimbus.State;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import static vtb.spring.repository.Config.*;
//
//@Repository
////@Profile("jdbc")
//public class JdbcOperaRepository implements OperaRepository {
//
//    private Connection connection;
//
//    public JdbcOperaRepository() {
//        try {
//            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        } catch (SQLException e) {
//            System.err.println(DB_URL + DB_USERNAME + DB_PASSWORD);
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Collection<OperaEntity> getAll() {
//        System.err.println("******");
//        List<OperaEntity> result = new ArrayList<>();
//        String sql = "select * from opera";
//        System.out.println("++++");
//        try (Statement statement = connection.createStatement()) {
//            System.out.println("----------");
//            final ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//
//                result.add(new OperaEntity(
//                        //resultSet.getInt("opera_id"),
//                        resultSet.getString("label"),
//                        resultSet.getInt("age"),
//                        resultSet.getString("type")));
//            }
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public boolean save(OperaEntity opera) {
//        String sql = " INSERT INTO OPERA(label, age, type) VALUES(?,?,?);";
//        try(PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setString(1, opera.getLabel());
//            statement.setInt(2, opera.getAge());
//            statement.setString(3, opera.getType());
//
//            final int rowsCount = statement.executeUpdate();
//            final ResultSet generatedKeys = statement.getGeneratedKeys();
//            generatedKeys.next();
//            final long createId = generatedKeys.getLong(1);
//            System.out.println(createId);
//
//            return statement.executeUpdate() == 1;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean delete(int operaId) {
//        String sql = " DELETE FROM opera WHERE opera_id = ?;";
//        try(PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setInt(1, operaId);
//            return statement.executeUpdate() == 1;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//
//    }
//}
