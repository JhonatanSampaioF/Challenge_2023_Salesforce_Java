package fiap.tds.repositories;

import fiap.tds.models.ConexaoOracle;
import fiap.tds.models.Especialista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

import static fiap.tds.Main.LOGGER;

public class EspecialistaRepositoryOracle {

    public static final String URL_CONNECTION = ConexaoOracle.URL_CONNECTION;
    public static final String USER = ConexaoOracle.USER;
    public static final String PASSWORD = ConexaoOracle.PASSWORD;
    private static final String TB_NAME = "TB_ESPECIALISTA";
    private static final Map<String, String> TB_COLUMNS = Map.of(
        "ID", "id_espec",
        "NOME", "nm_espec",
        "EMAIL", "email_espec",
        "TELEFONE", "telefone_espec"
    );
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
    }

    public EspecialistaRepositoryOracle(){
    }

    //Scanner scanner = new Scanner(System.in);

    public Especialista findById(int id){
        var especialista = new Especialista();
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "SELECT * FROM %s WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                var _id = rs.getInt(TB_COLUMNS.get("ID"));
                var nome = rs.getString(TB_COLUMNS.get("NOME"));
                var email = rs.getString(TB_COLUMNS.get("EMAIL"));
                var telefone = rs.getString(TB_COLUMNS.get("TELEFONE"));
                especialista = new Especialista(_id,nome,email,telefone);
                LOGGER.info("Especialista retornado com sucesso");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar especialista: {0}", e.getMessage()));
        }
        /*System.out.println("ID: " + especialista.getId());
        System.out.println("Nome: " + especialista.getNomeEspec());
        System.out.println("Email: " + especialista.getEmailEspec());
        System.out.println("Telefone: " + especialista.getTelefoneEspec());
        System.out.println("--------------------------------");*/

        return especialista;
    }

    public void create(Especialista especialista){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("NOME"),
                        TB_COLUMNS.get("EMAIL"),
                        TB_COLUMNS.get("TELEFONE")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do especialista: ");
            especialista.setId(scanner.nextInt());
            System.out.println("Insira o nome do especialista: ");
            especialista.setNomeEspec(scanner.next());
            System.out.println("Insira o email do especialista: ");
            especialista.setEmailEspec(scanner.next());
            System.out.println("Insira o telefone do especialista");
            especialista.setTelefoneEspec(scanner.next());*/
            stmt.setString(1, especialista.getNm_espec());
            stmt.setString(2, especialista.getEmail_espec());
            stmt.setString(3, especialista.getTelefone_espec());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Especialista criado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao criar especialista: {0}", e.getMessage()));
        }
    }

    public List<Especialista> readAll(){
        var lista = new ArrayList<Especialista>();
        try (
            var conn = getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM "+TB_NAME)
        )
        {
            var rs = stmt.executeQuery();
            while (rs.next()){
                lista.add(
                    new Especialista(
                        rs.getInt(TB_COLUMNS.get("ID")),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("EMAIL")),
                        rs.getString(TB_COLUMNS.get("TELEFONE"))
                    )
                );
            }
            LOGGER.info("Especialistas retornados com sucesso");
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar especialistas: {0}", e.getMessage()));
        }
        /*for (Especialista especialista : lista) {
            System.out.println("ID: " + especialista.getId());
            System.out.println("Nome: " + especialista.getNomeEspec());
            System.out.println("Email: " + especialista.getEmailEspec());
            System.out.println("Telefone: " + especialista.getTelefoneEspec());
            System.out.println("--------------------------------");
        }*/
        return lista;
    }

    public int update(Especialista especialista){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("NOME"),
                        TB_COLUMNS.get("EMAIL"),
                        TB_COLUMNS.get("TELEFONE"),
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do especialista que deseja alterar: ");
            especialista.setId(scanner.nextInt());
            System.out.println("Insira o novo nome do especialista: ");
            especialista.setNomeEspec(scanner.next());
            System.out.println("Insira o novo email do especialista: ");
            especialista.setEmailEspec(scanner.next());
            System.out.println("Insira o novo telefone do especialista");
            especialista.setTelefoneEspec(scanner.next());*/
            stmt.setString(1, especialista.getNm_espec());
            stmt.setString(2, especialista.getEmail_espec());
            stmt.setString(3, especialista.getTelefone_espec());
            stmt.setInt(4, especialista.getId());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Especialista atualizado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao atualizar especialsita: {0}", e.getMessage()));
        }
        return 0;
    }

    public void delete(int id){
        try (
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "DELETE FROM %s WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            stmt.setInt(1, id);
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Especialista removido com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao deletar especialista: {0}", e.getMessage()));
        }
    }
}
