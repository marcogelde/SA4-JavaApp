<h1 align="center"> SA4-JavaApp </h1>



O projeto consiste em um sistema de gerenciamento de estudantes e professores, desenvolvido em Java utilizando o padrão DAO (Data Access Object) e implementando operações de CRUD (Create, Read, Update, Delete). A arquitetura segue os princípios da Programação Orientada a Objetos (POO), com herança e polimorfismo, integrando o sistema a um banco de dados relacional por meio da classe Conexao, responsável por estabelecer a conexão com o banco de dados.

Estrutura de Classes:
Classe Pessoa: A classe Pessoa é a superclasse do projeto, contendo os atributos comuns a todas as pessoas gerenciadas pelo sistema:

id: identificador único para cada pessoa.
nome: nome da pessoa.
email: endereço de e-mail da pessoa.
A classe também possui os métodos comuns, como getters e setters para os atributos, além de métodos de exibição de dados como toString().

Classe Estudante (herda de Pessoa): A classe Estudante é uma subclasse de Pessoa, representando especificamente os estudantes. Ela adiciona o atributo:

matricula: número de matrícula do estudante.
Como estudante herda de Pessoa, ele mantém os atributos e métodos gerais, além de possuir métodos próprios para manipular o dado de matrícula.

Classe Professor (herda de Pessoa): A classe Professor também herda de Pessoa, representando os professores. O único atributo específico da classe é:

departamento: o departamento ao qual o professor está vinculado.
Assim como Estudante, a classe Professor herda todos os atributos e métodos de Pessoa, além de possuir métodos específicos para o departamento.

Interface DAO:
O sistema utiliza uma interface DAO<T>, que define os métodos genéricos para manipulação de dados de qualquer entidade no banco de dados. A interface garante que todas as classes DAO sigam o mesmo padrão de implementação das operações CRUD. Os métodos definidos na interface são:

inserir(T objeto): Insere um novo registro no banco de dados.
buscarPorID(int id): Busca um registro no banco de dados com base no ID.
listarTodos(): Retorna uma lista de todos os registros da entidade.
atualizar(T objeto): Atualiza um registro existente.
deletar(int id): Remove um registro com base no ID.
DAO para Pessoa, Estudante e Professor:
Cada classe (Pessoa, Estudante, Professor) possui uma classe DAO que implementa a interface DAO. Isso permite que as operações de manipulação de dados de cada entidade sejam realizadas de forma organizada e reutilizável.

PessoaDAO: Implementa a interface DAO e gerencia todas as operações de CRUD para a entidade Pessoa, manipulando o banco de dados para inserir, atualizar, buscar, listar e deletar registros de pessoas.

EstudanteDAO: Implementa a interface DAO e foca nas operações de CRUD para Estudante. Como Estudante é uma subclasse de Pessoa, o DAO realiza as operações de forma específica para a entidade, tratando o atributo adicional de matrícula.

ProfessorDAO: Também implementa a interface DAO, focando nas operações de Professor. O atributo adicional de departamento é manipulado de forma específica para os registros de professores.

Conexão com Banco de Dados:
A classe responsável por estabelecer a conexão com o banco de dados é chamada Conexao. Ela contém os métodos necessários para abrir a conexão com o banco de dados e possibilitar as operações de CRUD nas classes DAO.

A conexão com o banco de dados é configurada utilizando JDBC (Java Database Connectivity), e a classe Conexao fornece o método getConnection() para retornar uma conexão ativa ao banco. No entanto, o fechamento da conexão após cada operação ainda não foi implementado, o que seria um aspecto importante para a eficiência e segurança do sistema.

Operações CRUD no Main:
No método main do projeto, as operações de CRUD são realizadas como testes para validar o funcionamento do sistema. As operações incluem:

Inserção de dados: Inserir novos registros de estudantes e professores no banco de dados.
Busca por ID: Realizar consultas para buscar um estudante ou professor específico com base em seu ID.
Listagem de todos os registros: Listar todos os estudantes e professores cadastrados no sistema, exibindo seus dados de forma organizada.
Atualização de registros: Atualizar as informações de um estudante ou professor existente no banco de dados.
