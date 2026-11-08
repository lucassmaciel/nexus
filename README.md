# Nexus

API em Java com Spring Boot 3 que gerencia uma fila de tarefas e gera relatórios em PDF. A documentação dos endpoints sai pelo Swagger (springdoc-openapi).

## Estrutura

| Pacote | Conteúdo |
|---|---|
| `queue` | `Task`, `TaskManager` e `TaskController` (fila de tarefas) |
| `pdf` | `PDFReportGenerator` e `PDFReportController` (relatórios em PDF) |
| `config` | `SwaggerConfig` |

## Como rodar

```bash
./mvnw spring-boot:run
```

A documentação fica em <http://localhost:8080/swagger-ui.html>.
