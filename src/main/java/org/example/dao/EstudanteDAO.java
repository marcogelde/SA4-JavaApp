package org.example.dao;

import org.example.Conexao;
import org.example.dao.interf.IDAO;
import org.example.model.Estudante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO implements IDAO<Estudante> {

    @Override
    public void inserir(Estudante estudante) {
        String sqlPessoa = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";
        String sqlEstudante = "INSERT INTO estudantes (pessoa_id, matricula) VALUES (?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtEstudante = conexao.prepareStatement(sqlEstudante)) {

            conexao.setAutoCommit(false);
            stmtPessoa.setString(1, estudante.getNome());
            stmtPessoa.setString(2, estudante.getEmail());
            stmtPessoa.executeUpdate();

            try (ResultSet generatedKeys = stmtPessoa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    estudante.setId(generatedKeys.getInt(1));
                }
            }
            stmtEstudante.setInt(1, estudante.getId());
            stmtEstudante.setString(2, estudante.getMatricula());
            stmtEstudante.executeUpdate();

            conexao.commit();
        } catch (SQLException rollbackEx) {
            System.err.println("Erro no rollback: " + rollbackEx.getMessage());
        }
    }

    @Override
    public Estudante buscarPorId(int id) {
        String sql = "SELECT p.id, p.nome, p.email, es.matricula FROM pessoas p JOIN estudantes es ON p.id = es.pessoa_id WHERE p.id = ?";
        Estudante estudante = null;

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estudante = new Estudante(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudante;
    }

    @Override
    public List<Estudante> listarTodos() {
        String sql = "SELECT p.id, p.nome, p.email, es.matricula " +
                "FROM pessoas p " +
                "JOIN estudantes es " +
                "ON p.id = es.pessoa_id";
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estudante estudante = new Estudante(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula")
                );
                estudantes.add(estudante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudantes;
    }

    @Override
    public void atualizar(Estudante estudante) {
        String sqlPessoa = "UPDATE pessoas SET nome = ?, email = ? WHERE id = ?";
        String sqlEstudante = "UPDATE estudantes SET matricula = ? WHERE pessoa_id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement stmtEstudante = conexao.prepareStatement(sqlEstudante)) {

            conexao.setAutoCommit(false);

            stmtPessoa.setString(1, estudante.getNome());
            stmtPessoa.setString(2, estudante.getEmail());
            stmtPessoa.setInt(3, estudante.getId());
            stmtPessoa.executeUpdate();

            stmtEstudante.setString(1, estudante.getMatricula());
            stmtEstudante.setInt(2, estudante.getId());
            stmtEstudante.executeUpdate();

            conexao.commit();

            // Mensagem de confirmação
            System.out.printf("Estudante com ID %d atualizado com sucesso.%n", estudante.getId());

        } catch (SQLException rollbackEx) {
            System.err.println("Erro no rollback: " + rollbackEx.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
