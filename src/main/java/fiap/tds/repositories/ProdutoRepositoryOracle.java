package fiap.tds.repositories;

import fiap.tds.models.ConexaoOracle;
import fiap.tds.models.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

import static fiap.tds.Main.LOGGER;

public class ProdutoRepositoryOracle {

    public static final String URL_CONNECTION = ConexaoOracle.URL_CONNECTION;
    public static final String USER = ConexaoOracle.USER;
    public static final String PASSWORD = ConexaoOracle.PASSWORD;
    private static final String TB_NAME = "TB_PRODUTO";
    private static final Map<String, String> TB_COLUMNS = Map.of(
            "ID", "id_produto",
            "NOME", "nm_prod",
            "PLANO", "plano_prod",
            "DESCRICAO", "desc_prod"
    );
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
    }

    public ProdutoRepositoryOracle(){
    }

    //Scanner scanner = new Scanner(System.in);

    public Produto findById(int id){
        var produto = new Produto();
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "SELECT * FROM %s WHERE %s = ?"
                    .formatted(TB_NAME,
                        TB_COLUMNS.get("ID")))
        )
        {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                var _id = rs.getInt(TB_COLUMNS.get("ID"));
                var nome = rs.getString(TB_COLUMNS.get("NOME"));
                var plano = rs.getInt(TB_COLUMNS.get("PLANO"));
                var desc = rs.getString(TB_COLUMNS.get("DESCRICAO"));
                produto = new Produto(_id,nome,plano,desc);
                LOGGER.info("Produto retornado com sucesso");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar produto: {0}", e.getMessage()));
        }
        /*System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNomeProd());
        System.out.println("Plano: " + produto.getPlanoProd());
        System.out.println("Descrição: " + produto.getDescProd());
        System.out.println("--------------------------------");*/

        return produto;
    }

    public void create(Produto produto){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)"
                    .formatted(
                        TB_NAME,
                        TB_COLUMNS.get("NOME"),
                        TB_COLUMNS.get("PLANO"),
                        TB_COLUMNS.get("DESCRICAO")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do produto: ");
            produto.setId(scanner.nextInt());
            System.out.println("Insira o nome do produto: ");
            produto.setNomeProd(scanner.next());
            System.out.println("Insira o plano do produto: ");
            produto.setPlanoProd(scanner.nextInt());
            System.out.println("Insira a descrição do produto: ");
            produto.setDescProd(scanner.next());*/
            stmt.setString(1, produto.getNm_prod());
            stmt.setInt(2, produto.getPlano_prod());
            stmt.setString(3, produto.getDesc_prod());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Produto criado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao criar produto: {0}", e.getMessage()));
        }
    }

    public List<Produto> readAll(){
        var lista = new ArrayList<Produto>();
        try (
                var conn = getConnection();
                var stmt = conn.prepareStatement("SELECT * FROM "+TB_NAME)
            )
            {
            var rs = stmt.executeQuery();
            while (rs.next()){
                lista.add(
                    new Produto(
                        rs.getInt(TB_COLUMNS.get("ID")),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getInt(TB_COLUMNS.get("PLANO")),
                        rs.getString(TB_COLUMNS.get("DESCRICAO"))
                    )
                );
            }
                LOGGER.info("Produtos retornados com sucesso");
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao buscar produtos: {0}", e.getMessage()));
        }
        /*for (Produto produto : lista) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNomeProd());
            System.out.println("Plano: " + produto.getPlanoProd());
            System.out.println("Descrição: " + produto.getDescProd());
            System.out.println("--------------------------------");
        }*/
        return lista;
    }

    public int update(Produto produto){
        try(
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(
                        TB_NAME,
                         TB_COLUMNS.get("NOME"),
                         TB_COLUMNS.get("PLANO"),
                         TB_COLUMNS.get("DESCRICAO"),
                         TB_COLUMNS.get("ID")
                    )
            )
        )
        {
            /*System.out.println("Insira o id do produto que deseja alterar: ");
            produto.setId(scanner.nextInt());
            System.out.println("Insira o novo nome do produto: ");
            produto.setNomeProd(scanner.next());
            System.out.println("Insira o novo plano do produto: ");
            produto.setPlanoProd(scanner.nextInt());
            System.out.println("Insira a nova descrição do produto: ");
            produto.setDescProd(scanner.next());*/
            stmt.setString(1, produto.getNm_prod());
            stmt.setInt(2, produto.getPlano_prod());
            stmt.setString(3, produto.getDesc_prod());
            stmt.setInt(4, produto.getId());
            var rs = stmt.executeUpdate();
            if (rs == 1){
                LOGGER.info("Produto atualizado com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao atualizar produto: {0}", e.getMessage()));
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
                LOGGER.info("Produto removido com sucesso!");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            LOGGER.error(MessageFormat.format("Erro ao deletar produto: {0}", e.getMessage()));
        }
    }
}
