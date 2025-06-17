<h1 align="center"> 
	  🚀✅ Health Hands - Concluído ✅🚀
</h1>

<!-- MODELO MENU DE NAVEGAÇÃO -->
<p align="center">
<a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-Descrição-do-entregável">Descrição do Entregável</a> •
 <a href="#-funcionalidades ">Funcionalidades </a> • 
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-pré-requisitos">Pré-requisitos</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-ferramentas">Ferramentas</a> • 
 <a href="#-autor">Autor</a> • 
 <a href="#-licença">Licença</a>
</p>


## 💻 Sobre o projeto

O **HealthHands** é uma aplicação completa de gerenciamento clínico, desenvolvida com **Spring Boot** no backend e **React** no frontend.

---


<!-- MODELO DE DESCRIÇÃO -->
## 📄 Descrição do entregável

Este repositório está dividido em duas partes principais:

---

### ⚙️ `clinica-back` (API - Java Spring Boot)

Contém toda a lógica de negócio e as rotas da API, responsáveis pelo gerenciamento de usuários, autenticação e controle de consultas.

### 📁 Estrutura de Pastas:
src/
  * main/
    * java/com/gestao/clinica/
      * controllers/         # Endpoints da API
      * services/            # Regras de negócio
      * repositories/        # Acesso ao banco via JPA
      * entities/            # Entidades 
      * dto/                 # Data Transfer Objects
      * config/            # Configurações de autenticação JWT
    * resources/
      * relatorios    # Relatório de consultas
      * application.properties   # Configurações da aplicação
      

## 📂 Estrutura das Rotas da API (Spring Boot)

### Autenticação
| Método | Rota                  | Acesso    | Descrição                          |
|--------|-----------------------|-----------|-----------------------------------|
| POST   | `/api/login`         | Público   | Login e geração de token JWT       |

### Paciente
| Método | Rota                  | Acesso    | Descrição                          |
|--------|-----------------------|-----------|-----------------------------------|
| POST   | `/pacientes/create`          | Público   | Cadastrar paciente                 |

### Médico
| Método | Rota                  | Acesso    | Descrição                          |
|--------|-----------------------|-----------|-----------------------------------|
| POST   | `/medicos/create`            | Público   | Cadastrar médico                   |
| GET    | `/medicos/all`            | Todos     | Listar todos os médicos            |
| GET    | `/medicos/{id}`       | Todos     | Buscar médico por ID               |

### Consultas
| Método | Rota                          | Acesso              | Descrição                             |
|--------|-------------------------------|---------------------|----------------------------------------|
| POST   | `/consultas/agendar`          | PACIENTE            | Agendar nova consulta                  |
| GET    | `/consultas/all`              | ADMIN, MÉDICO, PACIENTE | Listar todas as consultas         |
| PUT    | `/consultas/update/{id}`      | PACIENTE ou MÉDICO  | Editar data da consulta                |
| DELETE | `/consultas/delete/{id}`      | PACIENTE ou MÉDICO  | Cancelar uma consulta                  |
| GET    | `/consultas/buscar?termo=X`   | ADMIN, MÉDICO, PACIENTE | Buscar consulta por nome, data ou especialidade |
| GET    | `/consultas/medico/{id}`      | Todos               | Listar consultas de um médico específico |


---

### 🌐 `clinica-web` (Frontend - React)

Interface gráfica da aplicação, construída em React com . Responsável pela interação com os usuários (médicos e pacientes) e pelo consumo da API.

### 📁 Estrutura de Pastas:
src/
  * assets/
    * images/     # Logos, ícones, imagens em geral

  * components/     # Componentes reutilizáveis 

  * pages/          # Telas da aplicação 
  * services/
    * axiosConfig.js  # Configuração base do Axios para a API

  * style/          # Estilizações globais CSS

  * App.jsx         # Componente principal com rotas

  



<!-- MODELO DESCRIÇÃO SOBRE O PROJETO: -->


## 🔗 Funcionalidades 

### 👩‍⚕️ Médicos
- Cadastro com especialidade e CRM
- Visualização das consultas agendadas

### 👤 Pacientes
- Cadastro com dados pessoais
- Agendamento de consultas
- Visualização, edição e cancelamento de consultas

### 🔐 Autenticação
- Login com JWT
- Controle de rotas baseado no tipo de usuário (admin, médico, paciente)

---


<!-- EXEMPLO DE LAYOUT: -->
## 🎨 Layout

![Web1](https://github.com/maridabrins/Nexcent/blob/main/src/assets/images/Thumbnail.png)

---

<!-- ---------------------------------------------------------------------- -->

<!-- MODELO DE COMO EXECUTAR O PROJETO -->
## 🚀 Como executar o projeto

### ✅ Backend (Java)

1. Abrir o projeto no Eclipse ou IntelliJ
2. Verificar o arquivo `application.properties` (porta e banco)
3. Rodar a aplicação com `ClinicaApplication.java`
4. A API estará disponível em `http://localhost:8080`

### ✅ Frontend (React)

1. Entrar na pasta `clinica-web`
2. Instalar as dependências: ``npm install``
3. Iniciar aplicação:  `npm start`
4. A interface estará disponível em `http://localhost:3000`
### Ou acesse por aqui:
Você também pode acessar clicando nesse link da Azure:

- [HealthHands](gestao-clinicamedica-c2edatdwbcace3gw.brazilsouth-01.azurewebsites.net)

---

<!-- MODELO DE PRÉ REQUISITOS -->
### Pré-requisitos


Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/) (v16+ recomendado)
- [NPM](https://www.npmjs.com/) ou [Yarn](https://yarnpkg.com/)
- [Git](https://git-scm.com/)
- Um editor de código, como o [Visual Studio Code](https://code.visualstudio.com/) ou [Eclipse](https://www.eclipse.org/sponsor/ide/?scope=Eclipse%20IDE%20for%20Java%20Developers%20(includes%20Incubating%20components)&version=4.34.0.20241128-0756&campaign=2025-06)


---

<!-- ---------------------------------------------------------------------- -->

<!-- MODELO DE TECNOLOGIAS -->
## 💻 Tecnologias

<div style="display: flex; gap: 10px; align-items: center; flex-wrap: wrap;">
<img src="https://cdn.simpleicons.org/react/61DAFB" height="40" alt="react logo"  />
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" height="40" alt="css3 logo"  />
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />


 <img src="https://cdn.simpleicons.org/spring/6DB33F" height="40" alt="spring logo"  />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/azure/azure-original.svg" height="40" alt="azure logo"  />
 <img src="https://cdn.simpleicons.org/figma/F24E1E" height="40" alt="figma logo"  />
 <img src="https://cdn.simpleicons.org/trello/0052CC" height="40" alt="trello logo"  />
</div>


## 🔨 Ferramentas: 

<div style="display: flex; gap: 10px; align-items: center; flex-wrap: wrap;">
  <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?logo=visualstudiocode&logoColor=white&style=for-the-badge" height="40" alt="vscode logo" />
  <img src="https://img.shields.io/badge/Eclipse IDE-2C2255?logo=eclipseide&logoColor=white&style=for-the-badge" height="40" alt="eclipseide logo"  />
  <img src="https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white&style=for-the-badge" height="40" alt="git logo" />
  <img src="https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=white&style=for-the-badge" height="40" alt="github logo" />
</div>

---

<!-- ---------------------------------------------------------------------- -->

<!-- MODELO DE COMO CONTRIBUIR PARA O PROJETO -->
## 💪 Como contribuir para o projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feature: My new feature"`
4. Envie as suas alterações: `git push origin my-feature`
> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](./CONTRIBUTING.md)

---

<!-- ---------------------------------------------------------------------- -->

<!-- MODELO DE AUTOR-->
## 🦸 Autor

<a href="(https://www.linkedin.com/in/mariana-dabrins-95a971328/)">
Mariana Dabrins</a>
 <br />
 
[![Gmail Badge](https://img.shields.io/badge/-maridabrins@hotmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:maridabrins@hotmail.com)](mailto:maridabrins@hotmail.com)

---

<!-- ---------------------------------------------------------------------- -->

<!-- MODELO DE LICENÇA -->
## 📝 Licença

Este projeto esta sobe a licença [MIT](./LICENSE).

Feito por Mariana Dabrins👋🏽 


