# Hackaton - Pos Tech Arquitetura e Desenvolvimento Java

## Descrição
Este é um projeto desenvolvido para o Hackathon da Pos Tech de Arquitetura e Desenvolvimento Java, com o objetivo de criar uma API robusta utilizando **Spring Boot 3**, banco de dados **MySQL**, e diversas bibliotecas para suporte a validação, documentação, testes e manipulação de PDFs.

A API tem como principal objetivo o cadastro e controle de medicamentos em UBSs no Brasil, através do controle de entrada e saída de medicamentos para os pacientes, enviando e-mails com receitas e avisos de disponibilidade de medicamentos, além do gerenciamento de médicos e pacientes.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **Spring Boot Starter Web**
- **Spring Boot Starter Validation**
- **Spring Boot Starter Mail**
- **Springdoc OpenAPI** (Swagger UI)
- **Banco de Dados MySQL** (com suporte a H2 para testes)
- **Lombok** para redução de boilerplate
- **Jsoup** para parse de HTML
- **OpenPDF** para geração de PDFs
- **JUnit 5 e Spring Boot Test** para testes automatizados

## Configuração e Execução

### 1. Clonar o Repositório
```sh
git clone https://github.com/TarcisioFls/hackaton.git
cd hackaton
```

### 2. Configurar Banco de Dados
O projeto está configurado para usar **MySQL**. Certifique-se de ter um banco criado e configure o **application.yml**:

```properties
spring:
    application:
        name: hackaton
    datasource:
        url: jdbc:mysql://localhost:3306/hackaton
        username: your_username
        password: your_password
        driver-class-name: com.mysql.jdbc.Driver
    jpa:
        hibernate:
            ddl-auto: create-drop
        defer-datasource-initialization: true
        properties:
            hibernate:
                dialect: org.hibernate.dialect.MySQL8Dialect
```

Para ambiente de testes, o banco **H2** será utilizado automaticamente.

### 3. Configurar Disparo de E-mails
O projeto está configurado para usar **MailTrap** para envio de e-mails. Configure o **application.yml** com suas credenciais:

```properties
spring:
    mail:
        host: sandbox.smtp.mailtrap.io
        port: 2525
        username: your_username
        password: your_password
        properties:
            mail:
                smtp:
                    auth: true
                    starttls:
                        enable: true
```

Para execução de testes utilizando **MailTrap**, é necessária a criação de uma conta gratuita em [MailTrap](https://mailtrap.io/).

### 3. Compilar e Executar

#### Executar com Maven
```sh
mvn spring-boot:run
```

#### Gerar JAR executável
```sh
mvn clean package
java -jar target/hackaton-0.0.1-SNAPSHOT.jar
```

## Documentação da API (Swagger)

Após iniciar o projeto, acesse a documentação interativa via **Swagger UI**:
```sh
http://localhost:8080/swagger-ui/index.html
```

## Testes
Os testes podem ser executados com:
```sh
mvn test
```

## Estrutura do Projeto

```sh
hackaton/
├── src/
│   ├── main/
│   │   ├── java/br/com/hackaton/
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   ├── application.yml
│   │   │   ├── application-test.yml
│   │   │   ├── data.sql
│   ├── test/
│       ├── java/br/com/hackaton/
├── pom.xml
├── README.md
```

## Contribuição
1. Fork o repositório
2. Crie uma branch (`git checkout -b feature/nova-feature`)
3. Commit suas alterações (`git commit -m 'Adicionando nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request
