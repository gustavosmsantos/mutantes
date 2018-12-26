# Mutantes

Aplicação para verificação de DNAs de mutantes.

## Aplicação

Para subir a aplicação, executar o comando `./gradlew bootRun`, que
inicializa o serviço na porta 8080.

## Endpoints

- \[POST\] `/mutant`: Verifica se uma sequência de DNA informada caracteriza um mutante. Em caso positivo, a API retorna o status code 200, e em caso negativo retorna o status code 403.

    Exemplo de payload:

    ```javascript
        {
            "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
        }
    ```

    Response:
    200 (OK)

- \[GET\] `/stats`: Retorna dados estatísticos com a proporção entre mutantes e humanos

    Exemplo de response:

    ```javascript
        {
            “count_mutant_dna”:40,
            “count_human_dna”:100,
            “ratio”:0.4
        }
    ```

    200 (OK)

# Testes

A execução dos testes automatizados pode ser feita através do comando `./gradlew test`, e um relatório de code coverage é automaticamente gerado no path `build/reports/jacocoTest`

# Notas

- A aplicação está utilizando uma base em memória (h2) e a engine de cache básica do spring. Em uma aplicação real soluções mais robustas seriam utilizadas.