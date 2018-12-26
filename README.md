# Mutantes

Aplicação para verificação de DNAs de mutantes.

## Aplicação

Para subir a aplicação, executar o comando `./gradlew bootRun`, que
inicializa o serviço na porta 8080.

## Endpoints

- \[POST\] `/mutant`: Verifica se uma sequência de DNA informada caracteriza um mutante. Em caso positivo, a API retorna o status code 200, e em caso negativo retorna o statusCode 403.

Exemplo de payload:

```javascript
    {
        "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }
```

Response:
200