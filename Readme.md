## Projeto: Gerenciamento de Usuários
### Esse projeto foi feito na linguagem Java, utilizando conceitos básicos de Servlets.

Trata-se de um projeto de Gerenciamento de Usuários, na qual é possível cadastrar um usuário, alterar dados e listar os funcionários cadastrados.

Não foi utilizada nenhuma tecnologia do lado cliente para estilização das telas, pois o foco desse projeto em um primeiro momento foi o desenvolvimento das Servlets, como forma de treinamento com o básico do Java Web.

## Ferramentas utilizadas:
* IDE: Eclipse (2020-06 - 4.16.0)
* SGBD: MySQL 8.0
* Servidor da Aplicação: Apache Tomcat (9.0.37)
* Sistema Operacional: Windows 10 - 64 Bits


## Material de consulta:
* Apostila da Caelum (FJ-21)
* Site Java T Point (https://www.javatpoint.com/crud-in-servlet)

## Processo de instalação do projeto:
* Ter as ferramentas acima instaladas na sua máquina.


* Executar esse script de criação da tabela Usuarios:
	* **(Observação: No MySQL, você deve criar um schema para criar a tabela)**

```SQL
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    dataNascimento DATE,
    status VARCHAR(20) NOT NULL,
    dataInclusao DATETIME NOT NULL,
    dataModificacao DATETIME,
    motivoAlteracao VARCHAR(500),
    primary key (id)
);
```

* Baixar o arquivo .WAR da aplicação, que se encontra no caminho **GerenciadorDeUsuarios/WAR/** do repositório do GitHub e importá-lo na sua IDE.


* Adicionar o conector do MySql (.jar) no build path do projeto.
	* Para isso, expandir as pastas do projeto no caminho **WebContent/WEB-INF/lib** até encontrar o arquivo do conector do mysql;
  * Clicar com o botão direito do mouse em cima do arquivo do conector;
  * Ir até a opção **Build Path**, e depois clicar na opção **Add to Build Path**;


- Alterar a String de conexão na classe (br.com.helio.jdbc.ConnectionFactory), de acordo com os dados da conexão que você criou:

```Java
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/<SCHEMA_NAME>?useTimezone=true&serverTimezone=UTC",
					"<SCHEMA_USERNAME>", "<SCHEMA_PASSWORD>");
```

* Rodar a aplicação no servidor da aplicação:
  * Clicar com o botão direito do mouse no nome do projeto;
  * Ir até a opção **Run As**;
  * Clicar na opção **Run on server**;
  * Ao abrir a janela **Run On Server**, clicar no botão **Finish**;
