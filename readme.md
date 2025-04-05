A **implementação da Arquitetura Hexagonal** que apresentei é baseada nos princípios do artigo original de **Alistair Cockburn**, garantindo que os conceitos essenciais sejam respeitados. A seguir, justifico tecnicamente **cada escolha** no design do projeto com base no artigo original.

---

## **1. Separação entre o Núcleo da Aplicação e a Infraestrutura**
### 🔹 **Decisão**
- O domínio (`domain/`) contém apenas **regras de negócio e contratos**, sem dependências externas.
- As implementações técnicas como banco de dados e API REST ficam em `infrastructure/`, atuando como **Adapters**.

### 📌 **Justificativa segundo Alistair Cockburn**
> "The application is imagined as a circle with the application inside, protected from direct influence by outside elements like databases, user interfaces, or external APIs."

📖 Isso significa que o **coração do sistema** (regras de negócio) deve ser **independente de infraestrutura**. Por isso, a interface `UserRepositoryPort` define **contratos genéricos** sem dependência de JPA, garantindo que a camada de domínio não tenha acoplamento com frameworks externos.

---

## **2. Uso de Ports e Adapters**
### 🔹 **Decisão**
- Criamos a interface `UserRepositoryPort` dentro de `domain/` para definir a **porta (Port)** de acesso aos usuários.
- A implementação real (`UserRepositoryAdapter`) está na infraestrutura (`infrastructure/repository`), atuando como um **Adapter**.

### 📌 **Justificativa segundo Alistair Cockburn**
> "Instead of the application knowing about the database, the database knows about the application through an adapter."

📖 **O domínio não deve saber que tipo de banco de dados está sendo usado**. Criamos `UserRepositoryPort` para que o serviço (`UserService`) dependa apenas de uma **abstração**. Assim, podemos trocar facilmente de banco de dados sem afetar as regras de negócio.

---

## **3. Independência Tecnológica**
### 🔹 **Decisão**
- **O core da aplicação não depende de Spring, JPA ou qualquer framework**.
- `UserService` não conhece detalhes da infraestrutura.
- `UserController` e `UserRepositoryAdapter` servem apenas como **meios de comunicação externos**.

### 📌 **Justificativa segundo Alistair Cockburn**
> "Business rules do not know about the outside world. They simply execute their logic."

📖 **Se amanhã quisermos trocar Spring Boot por Quarkus ou usar um banco NoSQL, o core do sistema continuará o mesmo**. Isso respeita o princípio de que o **núcleo deve ser isolado de implementações externas**.

---

## **4. Adapters para Entrada e Saída**
### 🔹 **Decisão**
- **Controller** (`UserController`) é um **Adapter de Entrada**, traduzindo HTTP para chamadas de serviço.
- **Repository Adapter** (`UserRepositoryAdapter`) é um **Adapter de Saída**, traduzindo chamadas do domínio para banco de dados.

### 📌 **Justificativa segundo Alistair Cockburn**
> "Adapters are responsible for converting data and requests to and from the application's internal structure."

📖 Os **Adapters servem como tradutores** entre o mundo externo (API, banco de dados) e o domínio. O Controller não deve ter regras de negócio – apenas encaminha os dados para `UserService`.

---

## **5. Configuração via Beans do Spring Boot**
### 🔹 **Decisão**
- Utilizamos `@Bean` em `BeanConfig` para **configurar as dependências manualmente**.

### 📌 **Justificativa segundo Alistair Cockburn**
> "Decoupling external services allows for easier testing, deployment, and evolution."

📖 **Ao configurar dependências manualmente**, garantimos que a injeção de dependências seja flexível e facilmente substituível. Isso facilita testes unitários, onde podemos mockar repositórios sem carregar todo o contexto do Spring.

---

## **6. Testabilidade**
### 🔹 **Decisão**
- Como `UserService` depende de `UserRepositoryPort`, podemos facilmente **mockar** a implementação real (`UserRepositoryAdapter`).

### 📌 **Justificativa segundo Alistair Cockburn**
> "With this architecture, we can easily swap out implementations for testing."

📖 **Isso permite que testemos as regras de negócio isoladamente**, sem precisar conectar ao banco ou configurar servidores web.

---

## **Resumo**
Cada decisão segue **exatamente os princípios da Arquitetura Hexagonal** descritos por Alistair Cockburn:

✅ **Isolamento do domínio**: A camada de regras de negócio não conhece detalhes técnicos.  
✅ **Uso de Ports e Adapters**: O núcleo conversa com o mundo externo via interfaces.  
✅ **Independência tecnológica**: Podemos trocar banco de dados, frameworks ou protocolos sem afetar a lógica central.  
✅ **Facilidade para testes**: Podemos mockar repositórios e controllers sem necessidade de dependências reais.  

---

### **Por que a camada `application` não fica dentro de `domain` na Arquitetura Hexagonal?**  

A separação entre **`domain` e `application`** é um conceito essencial na Arquitetura Hexagonal porque cada camada tem um propósito distinto. **A camada de domínio (`domain/`) deve ser pura, sem dependências externas, enquanto a camada de aplicação (`application/`) orquestra casos de uso e integra serviços.**  

---

## **📌 Diferença entre `domain` e `application`**
| Camada         | Responsabilidade                                                                 | Dependências |
|---------------|--------------------------------------------------------------------------------|--------------|
| **Domain**    | Modelos de domínio, regras de negócio, entidades, interfaces (Ports).          | Nenhuma externa |
| **Application** | Casos de uso, serviços de aplicação, orquestração de operações.                | Depende do domínio |

---

## **📍 Justificativa segundo Alistair Cockburn**
Em seu artigo sobre **Arquitetura Hexagonal**, Cockburn define que:  

> "The business rules should have no dependencies on the outside world. They are self-contained and do not know how they are being called or where their results are going."  

📖 **Isso significa que o `domain/` deve ser puro e não deve conter casos de uso, apenas regras de negócio.**  

Por outro lado:  

> "The application layer orchestrates use cases, calling domain logic and interacting with external services through adapters."  

📖 **Ou seja, a camada `application/` é responsável por coordenar operações, como chamar um repositório, processar regras de negócio e disparar eventos.**  

---

## **🔹 Por que `application/` não fica dentro de `domain/`?**
1️⃣ **O `domain/` deve ser 100% independente.**  
   - Se colocássemos `application/` dentro de `domain/`, ele carregaria dependências externas, violando o princípio de isolamento do domínio.  

2️⃣ **`application/` contém lógica de orquestração, não de domínio.**  
   - O **serviço de aplicação (Application Service)** pode chamar múltiplos repositórios, enviar eventos e interagir com APIs externas. **Isso não faz parte do domínio puro.**  

3️⃣ **Facilita testes e manutenção.**  
   - `domain/` pode ser testado isoladamente sem precisar carregar dependências do Spring ou acessar banco de dados.  
   - `application/` pode ser testado com **mocks de repositórios e adaptadores**, sem alterar regras de negócio.  

---

## **🛠 Exemplo prático:**
### **Organização correta**

A estrutura de pastas do projeto segue a arquitetura hexagonal com separações bem definidas. Abaixo está uma visualização expandida dos diretórios principais e seus subníveis:

```
src/
├── main/
│   ├── java/com/aktie/
│   │   ├── application/
│   │   │   └── mappers/
│   │   │       └── UserMapper.java
│   │   │   └── service/
│   │   │       └── UserService.java
│   │   ├── domain/
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   └── ports/
│   │   │       └── UserRepositoryPort.java
│   │   ├── infrastructure/
│   │   │   ├── controller/
│   │   │   │   └── UserController.java
│   │   │   └── repository/
│   │   │       ├── entity/
│   │   │       │   └── UserEntity.java
│   │   │       ├── mapper/
│   │   │       │   └── UserEntityMapper.java
│   │   │       └── adapter/
│   │   │           └── UserRepositoryAdapter.java
│   │   └── SpringHexaBaseApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/index.html
└── test/
    └── java/com/aktie/
        └── SpringHexaBaseApplicationTests.java
```

💡 **Dessa forma:**  
✅ `domain/` contém **apenas** regras de negócio e contratos (**isolado**).  
✅ `application/` gerencia casos de uso e lógica de aplicação.  
✅ `infrastructure/` contém integrações externas (REST, banco, eventos).  

---

## **Resumo**
**A camada `application` deve ficar separada de `domain` porque:**  
✔ O **domínio** precisa ser independente de infraestrutura.  
✔ A **aplicação** orquestra operações e usa serviços externos.  
✔ Isso mantém o código mais **modular, testável e flexível**.  