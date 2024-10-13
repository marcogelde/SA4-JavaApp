<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gerenciamento de Estudantes e Professores</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f0f0f0;
            color: #333;
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        h2 {
            color: #333;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        p {
            line-height: 1.6;
        }
        ul {
            margin-left: 20px;
        }
        li {
            margin-bottom: 10px;
        }
        .code {
            background-color: #e8e8e8;
            padding: 10px;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Sistema de Gerenciamento de Estudantes e Professores</h1>

    <h2>Descrição do Projeto</h2>
    <p>
        O projeto consiste em um sistema de gerenciamento de estudantes e professores, desenvolvido em Java utilizando o padrão DAO (Data Access Object) e implementando operações de CRUD (Create, Read, Update, Delete). A arquitetura segue os princípios da Programação Orientada a Objetos (POO), com herança e polimorfismo, integrando o sistema a um banco de dados relacional por meio da classe <strong>Conexao</strong>, responsável por estabelecer a conexão com o banco de dados.
    </p>

    <h2>Estrutura de Classes</h2>
    <h3>Classe Pessoa</h3>
    <p>A classe <strong>Pessoa</strong> é a superclasse do projeto, contendo os atributos comuns a todas as pessoas gerenciadas pelo sistema:</p>
    <ul>
        <li><strong>id</strong>: identificador único para cada pessoa.</li>
        <li><strong>nome</strong>: nome da pessoa.</li>
        <li><strong>email</strong>: endereço de e-mail da pessoa.</li>
    </ul>
    <p>A classe também possui os métodos comuns, como getters e setters para os atributos, além de métodos de exibição de dados como <code>toString()</code>.</p>

    <h3>Classe Estudante (herda de Pessoa)</h3>
    <p>A classe <strong>Estudante</strong> é uma subclasse de <em>Pessoa</em>, representando especificamente os estudantes. Ela adiciona o atributo:</p>
    <ul>
        <li><strong>matricula</strong>: número de matrícula do estudante.</li>
    </ul>
    <p>Como <em>Estudante</em> herda de <em>Pessoa</em>, ela mantém os atributos e métodos gerais, além de possuir métodos próprios para manipular o dado de matrícula.</p>

    <h3>Classe Professor (herda de Pessoa)</h3>
    <p>A classe <strong>Professor</strong> herda de <em>Pessoa</em>, representando os professores. O único atributo específico da classe é:</p>
    <ul>
        <li><strong>departamento</strong>: o departamento ao qual o professor está vinculado.</li>
    </ul>
    <p>Assim como <em>Estudante</em>, a classe <em>Professor</em> herda todos os atributos e métodos de <em>Pessoa</em>, além de possuir métodos específicos para o departamento.</p>

    <h2>Interface DAO</h2>
    <p>O sistema utiliza uma interface <strong>DAO&lt;T&gt;</strong>, que define os métodos genéricos para manipulação de dados de qualquer entidade no banco de dados. A interface garante que todas as classes DAO sigam o mesmo padrão de implementação das operações CRUD. Os métodos definidos na interface são:</p>
    <ul>
        <li><strong>inserir(T objeto)</strong>: Insere um novo registro no banco de dados.</li>
        <li><strong>buscarPorID(int id)</strong>: Busca um registro no banco de dados com base no ID.</li>
        <li><strong>listarTodos()</strong>: Retorna uma lista de todos os registros da entidade.</li>
        <li><strong>atualizar(T objeto)</strong>: Atualiza um registro existente.</li>
        <li><strong>deletar(int id)</strong>: Remove um registro com base no ID.</li>
    </ul>

    <h2>DAO para Pessoa, Estudante e Professor</h2>
    <p>Cada classe (<em>Pessoa</em>, <em>Estudante</em>, <em>Professor</em>) possui uma classe DAO que implementa a interface <strong>DAO</strong>. Isso permite que as operações de manipulação de dados de cada entidade sejam realizadas de forma organizada e reutilizável.</p>
    <ul>
        <li><strong>PessoaDAO</strong>: Implementa a interface <em>DAO</em> e gerencia todas as operações de CRUD para a entidade <em>Pessoa</em>, manipulando o banco de dados para inserir, atualizar, buscar, listar e deletar registros de pessoas.</li>
        <li><strong>EstudanteDAO</strong>: Implementa a interface <em>DAO</em> e foca nas operações de CRUD para <em>Estudante</em>, tratando o atributo adicional de matrícula.</li>
        <li><strong>ProfessorDAO</strong>: Implementa a interface <em>DAO</em> e foca nas operações de <em>Professor</em>, tratando o atributo de departamento.</li>
    </ul>

    <h2>Conexão com Banco de Dados</h2>
    <p>
        A classe responsável por estabelecer a conexão com o banco de dados é chamada <strong>Conexao</strong>. Ela contém os métodos necessários para abrir a conexão com o banco de dados e possibilitar as operações de CRUD nas classes DAO.
    </p>
    <p>
        A conexão com o banco de dados é configurada utilizando <strong>JDBC (Java Database Connectivity)</strong>, e a classe <em>Conexao</em> fornece o método <code>getConnection()</code> para retornar uma conexão ativa ao banco. No entanto, o fechamento da conexão após cada operação ainda não foi implementado, o que seria um aspecto importante para a eficiência e segurança do sistema.
    </p>

    <h2>Operações CRUD no Main</h2>
    <p>No método <em>main</em> do projeto, as operações de CRUD são realizadas como testes para validar o funcionamento do sistema. As operações incluem:</p>
    <ul>
        <li><strong>Inserção de dados</strong>: Inserir novos registros de estudantes e professores no banco de dados.</li>
        <li><strong>Busca por ID</strong>: Realizar consultas para buscar um estudante ou professor específico com base em seu ID.</li>
        <li><strong>Listagem de todos os registros</strong>: Listar todos os estudantes e professores cadastrados no sistema, exibindo seus dados de forma organizada.</li>
        <li><strong>Atualização de registros</strong>: Atualizar as informações de um estudante ou professor existente no banco de dados.</li>
    </ul>
</body>
</html>
