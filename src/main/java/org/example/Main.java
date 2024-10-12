package org.example;

import org.example.dao.EstudanteDAO;
import org.example.dao.ProfessorDAO;
import org.example.model.Estudante;
import org.example.model.Professor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // Instanciando DAOs
        ProfessorDAO professorDAO = new ProfessorDAO();
        EstudanteDAO estudanteDAO = new EstudanteDAO();

        // Inserindo 3 professores
        List<Professor> professores = Arrays.asList(
                new Professor(0, "João", "joao@legal.com", "Matemática"),
                new Professor(0, "Maria", "maria@legal.com", "Física"),
                new Professor(0, "Carlos", "carlos@showdebola.com", "Química")
        );

        professores.forEach(professorDAO::inserir);

        // Listando todos os professores
        System.out.println("Lista de Professores:");
        professorDAO.listarTodos().forEach(System.out::println);

        // Criando e inserindo 3 estudantes
        Estudante e1 = new Estudante(0, "Ana Clara", "ana@gmail.com", "20240001");
        Estudante e2 = new Estudante(0, "Lucas Mendes", "lucas@gmail.com", "20240002");
        Estudante e3 = new Estudante(0, "Bruna Alves", "bruna@gmail.com", "20240003");

        estudanteDAO.inserir(e1);
        estudanteDAO.inserir(e2);
        estudanteDAO.inserir(e3);

        // Listando todos os estudantes
        System.out.println("\nLista de Estudantes:");
        for (Estudante estudante : estudanteDAO.listarTodos()) {
            System.out.println(estudante);
        }

        // Buscando um professor pelo ID
        System.out.println("\nBuscando Professor com ID 1:");
        System.out.println(Optional.ofNullable(professorDAO.buscarPorId(1))
                .map(Professor::toString)
                .orElse("Professor não encontrado"));

        // Buscando um estudante pelo ID
        System.out.println("\nBuscando Estudante com ID 4:");
        Estudante estudanteBuscado = estudanteDAO.buscarPorId(4);
        if (estudanteBuscado != null) {
            System.out.println(estudanteBuscado);
        }

        // Atualizando um professor pelo ID
        System.out.println("\nAtualizando professor com ID 2:");
        professorDAO.atualizar(new Professor(2, "Tiago", "tiago@superlegal.com", "Música"));

        // Atualizando um estudante pelo ID
        System.out.println("\nAtualizando estudante com ID 5:");
        Estudante e4 = new Estudante(5, "Rafael", "rafael@gmail.com", "20241900");
        estudanteDAO.atualizar(e4);

        // Deletar um professor pelo ID
        System.out.println("\nDeletando um professor com ID 3:");
        professorDAO.deletar(3);
        System.out.println("Professor com ID " + 3 + " deletado.");


        //Deletar um estudante pelo ID
        System.out.println("\nDeletando estudante com ID 6:");
        estudanteDAO.deletar(6);
        System.out.println("Estudante com ID " + 6 + " deletado.");
    }
}