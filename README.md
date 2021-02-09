# Scrum-Deck Backend

Sejam bem-vindos, queridos alunos! Este é o backend do projeto Scrum Deck, 
no qual vocês devem estar trabalhando agora (ou não).

Este é um backend simplificado, mas que mimetiza uma aplicação de backend do 
mundo real. Aqui, tem alguns _endpoints_ RESTful, implementados com 
[Spring Boot](https://spring.io/projects/spring-boot), atendendo ao nível 2 do
[Modelo de Maturidade de Richardson](https://boaglio.com/index.php/2016/11/03/modelo-de-maturidade-de-richardson-os-passos-para-a-gloria-do-rest/).

Por hora, tudo que vocês precisam saber da intimidade deste backend já foi dito, 
uma vez que os detalhes dele não fazem parte da nossa disciplina, sendo o 
consumo dele o nosso objeto de estudo.

## Como usar?

Você tem três opções:

1. Usar on-line (ambiente compartilhado com outros alunos, não há segregação de registros)
2. Baixar o código e executar (Só é recomendado caso você tenha intimidade com Java e com Spring Boot);
3. Executar a imagem Docker que foi disponibilizada (opção recomendada)

Vamos a elas...

### Usar on-line

Esse é o jeito mais fácil, porém apresenta um grande ponto negativo: o ambiente
é compartilhado com todos. Os dados manipulados por um, podem ser manipulados por 
qualquer outro.

A maior utilidade desse ambiente é consultar a documentação da API, antes de usar 
umas das opções 2 ou 3.

Para acessar, basta clicar: [https://scrum-deck-backend.herokuapp.com/swagger-ui](https://scrum-deck-backend.herokuapp.com/swagger-ui)

### Executando à partir do código-fonte

#### Requisitos de Ambiente

Para executar à partir do código-fonte, você vai precisar ter no seu
ambiente local:

- Git instalado e configurado;
  - Está disponível nos gerenciadores de pacote do Linux (`apt`, `apk`, `yum`, etc);
  - Disponível no macOS após a instalação do Xcode;
  - No Windows, pode ser instalado via [`choco`](https://chocolatey.org/packages/git)
    ou via [download](https://git-scm.com/download/win) (não recomendo versão portable);
- Java Development Kit (JDK), versão 11 LTS. Se você não tiver, indico baixar
uma [aqui](https://adoptopenjdk.net/releases.html?variant=openjdk11&jvmVariant=openj9).

#### Procedimento

Uma vez atendidos os requisitos, vamos às etapas:

1. Para obter o código-fonte com o `git`, abra seu terminal e navegue até uma pasta
adequada (preferencialmente vazia, com permissão de escrita pelo seu usuário) e
execute:

```shell
git clone https://github.com/IESB-Dispositivos-Moveis-2019-2/scrum-deck-backend.git
```

2. Em seguida, entre na pasta do projeto

```shell
cd scrum-deck-backend
```

3. Depois, execute
   - Pode ser necessário atribuir permissão de execução para o arquivo `mvnw`
    ou `mvnw.cmd` (Windows)

Linux
```shell
./mvnw clean package spring-boot:run -B -DskipTests -T $(nproc)
```

macOS
```shell
./mvnw clean package spring-boot:run -B -DskipTests -T $(sysctl -n hw.logicalcpu)
```

Windows
```shell
mvnw clean package spring-boot:run -B -DskipTests
```

4. A aplicação irá iniciar, usando a porta `8080`, usando um banco de dados 
   [H2](http://www.h2database.com/html/main.html), no modo disco na pasta
   temporária da JVM com o arquivo chamado `iesb.mv.db`.
   - Ao iniciar, a aplicação exibirá algo parecido com isso:
     ```shell
        2021 - IESB - Instituto de Educação Superior de Brasília
        Backend do projeto Scrum Deck ~~ Fins Didáticos ~~
        
        
        ***
        
            Aplicação scrum-deck-backend iniciada com sucesso!
            Disponível nos endereços:
            Local: http://localhost:8080 
            Externo: http://127.0.0.1:8080 
            Swagger: http://127.0.0.1:8080/swagger-ui.html 
            Local Swagger: http://localhost:8080/swagger-ui.html 
            URL JDBC: jdbc:h2:/var/folders/h4/8dv02_1n0kb8j9w2_l7xmf_80000gn/T//iesb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE; 
        
        ***
     ```
     As informações mais relevantes aqui são: `Local Swagger` e `URL JDBC`;
   - Por padrão, o banco de dados com o modo `AUTO_SERVER` habilitado, 
     permitindo o uso do [console H2](http://localhost:8080/h2-console) ou de 
     qualquer outra ferramenta para manipulá-lo.
   - Caso seja necessário, é possível alterar a porta a ser usada. Para isso,
     basta criar uma variável ambiente com o nome `SERVER_PORT` e atribuir o valor
     desejado.
     
5. Você poderá acessar o Swagger-UI através do seu navegador, onde poderá acessar
a documentação da API, bem como realizar chamadas de teste para a mesma. A URL é 
aquela que está informada em `Local Swagger`, por padrão [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).


### Usando a imagem Docker

#### Requisitos de Ambiente

Para usar a imagem Docker, você vai precisar ter no seu ambiente local:

- Docker instalado e configurado
   - Pelo menos uma pasta configurada para compartilhamento entre hospedeiro e 
    containeres (função File Sharing)

#### Procedimento

Uma vez atendidos os requisitos, vamos às etapas:

1. Será necessário fazer login no _registry_ do Github. Para isso, é necessária a
criação de um token de autenticação específico para esta finalidade. Para criar um,
vá até o endereço: [https://github.com/settings/tokens](https://github.com/settings/tokens)
e clique no botão `Generate new token`;
   - No campo `Note`, recomendo escrever `GitHub Packages Read`;
   - A única permissão necessária é `read:packages`;
   - Preste atenção! Uma vez gerado, o token jamais lhe será exibido novamente,
    então, guarde-o em local seguro.
2. De posse do token, execute o comando de login (trocando os campos `seu@email.com` e
`token_que_voce_gerou` pelos valores corretos):
```shell
docker login docker.pkg.github.com --username=seu@email.com --password=token_que_voce_gerou
```
3. Tudo certo! Hora de rodar a aplicação. Para isso, usar o comando (trocando
o valor de `/minha/pasta` para o correto):
```shell
docker run -p 8080:8080 --name=scrum-deck-backend --rm -v /minha/pasta:/opt/app/banco docker.pkg.github.com/iesb-dispositivos-moveis-2019-2/scrum-deck-backend/scrum-deck-backend:latest
```
   - No modo Docker, não é possível manipular o banco de dados enquanto a 
     aplicação está em uso, entretanto, o arquivo fica na pasta indicada no comando
     acima;
   - Caso seja necessário, é possível alterar a porta a ser usada. Para isso,
     basta alterar o primeiro `8080` para o número de porta desejado;
   - A aplicação vai estar disponível no endereço informado: 
     `Local Swagger: http://localhost:8080/swagger-ui.html`, atendando.
     **Atenção**: caso você mude a porta, devido à natureza de funcionamento do 
     docker, a mudança não irá refletir nesse log, mas será respeitada. Portanto,
     será necessário que você ajuste a URL _antes_ de acessar no navegador.
     

## Colaborando com este repositório

Como já foi dito, este repositório tem fins puramente didáticos e não está
previsto suporte diverso a esta finalidade. Entretanto, caso queira contribuir,
o processo de ramificação [GitFlow](https://guides.github.com/introduction/flow/)
deve ser seguido. Aguardo o seu _Pull Request_, devidamente documentado (tanto a 
nível de código quanto neste `README`, caso seja pertinente).

## Licença

MIT License

Copyright (c) 2021 IESB - Dispositivos Móveis 2019-2 - Prof. Pedro Henrique

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
