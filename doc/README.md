# Worldinterlux E-commerce Platform

## Visão Geral

O **Worldinterlux** é uma plataforma de e-commerce modular desenvolvida para permitir a venda de produtos físicos com:
- Pagamento total ou parcial
- Levantamento físico da encomenda
- Sistema de fidelização
- Pesquisa e recomendações inteligentes
- Assistente de AI no site e no WhatsApp

A primeira versão (V1) foi desenhada como um **Monólito Modular em Spring Boot**, preparado para evoluir para microservices conforme o crescimento do negócio.

---

## Objetivos do Sistema

- Oferecer uma experiência de compra simples e rápida
- Garantir consistência forte em encomendas e pagamentos
- Fornecer recomendações personalizadas
- Integrar canais digitais (Website e WhatsApp)
- Suportar crescimento sem reescrita do core

---

## Arquitetura Geral

- **Backend:** Spring Boot (Java 17+)
- **Arquitetura:** Monólito Modular (DDD leve)
- **Banco de Dados:** PostgreSQL
- **Cache:** Redis
- **Autenticação:** JWT + Roles
- **Storage de Imagens:** Cloud Storage (S3 ou similar)
- **AI Assistant:** Serviço interno com integração futura a LLMs
- **Mensageria (futuro):** Event-based

---

## Módulos Principais

- `auth` – Autenticação e autorização
- `users` – Perfis de usuários
- `products` – Catálogo, grupos e avaliações
- `search` – Pesquisa de produtos
- `recommendations` – Recomendações inteligentes
- `orders` – Encomendas e estados
- `payments` – Integração com gateway de pagamento
- `loyalty` – Sistema de fidelização
- `ai` – Assistente virtual
- `whatsapp` – Bot e integração externa

---

## Fluxo do Sistema

### 1. Autenticação
- O usuário realiza login
- Um JWT é emitido
- O token é utilizado para acessar recursos protegidos

---

### 2. Pesquisa e Navegação
- Usuários podem:
  - Pesquisar produtos por nome, categoria ou tags
  - Visualizar recomendações na homepage
- Resultados frequentes são cacheados para baixa latência

---

### 3. Encomenda de Produtos

1. Usuário seleciona produtos
2. Escolhe forma de pagamento:
   - Pagamento total online
   - Pagamento parcial (entrada)
3. Encomenda é criada com status inicial:
   - `PENDING` ou `PARTIALLY_PAID`
4. Pagamento é processado pelo gateway externo
5. Status da encomenda é atualizado

---

### 4. Levantamento da Encomenda

- O valor restante é pago no ponto físico
- A encomenda passa para o estado:
  - `COLLECTED`
- Pontos de fidelização são atribuídos ao usuário

---

### 5. Sistema de Fidelização

- Pontos são acumulados por compra
- Níveis de usuário:
  - Bronze
  - Prata
  - Ouro
- Benefícios incluem descontos e promoções

---

### 6. Avaliações de Produtos

- Usuários autenticados podem:
  - Escrever avaliações
  - Visualizar avaliações de outros usuários
- Avaliações influenciam recomendações futuras

---

## Assistente de AI

### AI no Website
- Disponível apenas para usuários autenticados
- Funcionalidades:
  - Pesquisa de produtos
  - Recomendações personalizadas
  - Consulta de encomendas
  - Status de pedidos

### AI no WhatsApp
- Identificação por número de telefone
- Funcionalidades:
  - Lista de encomendas
  - Status do pedido
  - Recomendações simples
- Não requer login

---

## Requisitos Funcionais

- Autenticação de usuários
- Pesquisa de produtos
- Recomendações na homepage
- Criação e acompanhamento de encomendas
- Pagamento total ou parcial
- Sistema de fidelização
- Avaliação de produtos
- Assistente AI no site e WhatsApp

---

## Requisitos Não Funcionais

- **Baixa latência** em pesquisa e recomendações
- **Alta consistência** em pagamentos e encomendas
- **Segurança** (JWT, RBAC)
- **Escalabilidade modular**
- **Disponibilidade** do sistema
- **Observabilidade** (logs e métricas)

---

## Capacidade Estimada (V1)

- Usuários ativos mensais: ~1.000
- Pesquisas mensais: ~20.000
- Total de produtos: ~2.000
- Armazenamento estimado:
  - 20 GB (imagens + descrições)

---

## Evolução Planejada

- Extração de módulos para microservices
- Elasticsearch para busca avançada
- Recomendações baseadas em ML
- Busca por imagem
- Integração completa com LLMs
- Event-driven architecture

---

## Considerações Finais

Este projeto foi projetado para:
- Resolver necessidades reais do negócio
- Minimizar complexidade na V1
- Garantir base sólida para crescimento

O design privilegia **simplicidade, consistência e evolução contínua**.

---

## Licença
Proprietária – Worldinterlux




```bash
com.worldinterlux.ecommerce
│
├── EcommerceApplication.java
│
├── config
│   ├── security
│   │   ├── SecurityConfig.java
│   │   ├── JwtAuthenticationFilter.java
│   │   └── JwtProvider.java
│   ├── openapi
│   ├── cache
│   └── messaging
│
├── shared
│   ├── exception
│   ├── dto
│   ├── enums
│   ├── mapper
│   ├── util
│   └── event
│
├── auth
│   ├── controller
│   ├── service
│   └── dto
│
├── users
│   ├── controller
│   ├── service
│   ├── entity
│   ├── repository
│   └── dto
│
├── products
│   ├── controller
│   ├── service
│   ├── entity
│   ├── repository
│   ├── dto
│   ├── search
│   ├── reviews
│   └── group
│
├── orders
│   ├── controller
│   ├── service
│   ├── entity
│   ├── repository
│   ├── dto
│   └── event
│
├── payments
│   ├── controller
│   ├── service
│   ├── dto
│   └── gateway
│
├── loyalty
│   ├── service
│   ├── entity
│   ├── repository
│   └── event
│
├── search
│   ├── service
│   └── dto
│
├── recommendations
│   ├── service
│   └── strategy
│
├── ai
│   ├── controller
│   ├── service
│   ├── prompt
│   └── provider
│
├── whatsapp
│   ├── controller
│   ├── service
│   └── webhook
│
└── infrastructure
    ├── storage
    ├── cache
    ├── messaging
    └── external
```

```bash
com.worldinterlux.ecommerce
│
├── config
│   ├── security        # JWT, filtros, regras de acesso
│   ├── cache           # Redis
│   ├── openapi         # Swagger / OpenAPI
│   └── messaging       # Eventos (futuro)
│
├── shared
│   ├── exception       # Exceptions de negócio e técnicas
│   ├── dto             # DTOs compartilhados
│   ├── enums           # Enums globais
│   ├── mapper          # Mapeamento Entity <-> DTO
│   ├── util            # Utilitários
│   └── event           # Eventos de domínio
│
├── auth                # Autenticação e autorização
├── users               # Usuários
├── products            # Produtos, grupos, reviews
├── search              # Pesquisa de produtos
├── recommendations     # Regras e algoritmos de recomendação
├── orders               # Encomendas
├── payments            # Pagamentos
├── loyalty             # Fidelização
├── ai                  # Assistente de AI
├── whatsapp            # Integração WhatsApp
└── infrastructure      # Integrações externas
```
