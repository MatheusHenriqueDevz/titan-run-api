# 🏃 TitanRun API

API de alta performance para monitoramento de atividades físicas e gestão de saúde.

## 🚀 Tecnologias

* **Java 17/21**
* **Spring Boot 3**
* **Spring Data JPA**
* **H2 Database** (Ambiente de Dev) / **MySQL** (Produção - Futuro)
* **Lombok**

## 🎯 Objetivo do Projeto

Desenvolver uma solução backend robusta que permita:
1.  Registro e histórico de corridas (Telemetria).
2.  Cálculo de métricas de performance (Pace médio, projeções).
3.  Monitoramento de saúde (Controle de peso e IMC).
4.  Prática de conceitos avançados de Engenharia de Software (SOLID, Clean Arch, Segurança).

## 🛠️ Como rodar

```bash
# Clone este repositório
$ git clone [https://github.com/SEU_USUARIO/titan-run-api.git](https://github.com/SEU_USUARIO/titan-run-api.git)

# Acesse a pasta do projeto no terminal/cmd
$ cd titan-run-api

# Instale as dependências (Maven)
$ ./mvnw clean install

# Execute a aplicação
$ ./mvnw spring-boot:run
