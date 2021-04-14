# LuizalabsDesafio
O desafio consiste na implementação de microserviço de agendamento, sendo
 - Endpoint de Inserção do agendamento;
 - Endpoint de Consulta do agendamento;
 - Endpoint de Remoção do agendamento.

Em relação as integrações para as possíveis comunicações, que podem ser enviadas são: email, sms, push e whatsapp, irei presumir que estes são outros microserviçõs, com seus respsctivos endpoints, mas irei implementar mock para estes também.
 - Endpoint envio Agendamento email
 - Endpoint envio Agendamento sms
 - Endpoint envio Agendamento push
 - Endpoint envio Agendamento whatsapp

Visando abstração de deploy em esteira CI/CD, o mesmo não se dara em ambiente Devops, com Jenkis, ou seja, o deploy sera uma imagem docker, porem com a implementação dos testes em Junit, com QA via Sonar

A interação com o microserviço se dara via Postman

# Linhas gerais na montagem de ambiente DEV 
Sem entrar em detalhes do como, segue ações realizadas para a preparação do ambiente local de desenvolvimento:

Instalação do java 11;
Instalação do Yatta/Eclipse - profile: clean-code-sts;
Instalação e configuração do Mavem;
Instalação do DBEAVER para acesso ao Bando de Dados;
Instalação do Postman (com colection disponivel em \src\main\resources\infra\Postman\LuizaLabs.postman_collection.json);
Instalação do Git (Git Bash e sourcetree) e customização de aliases.
Instalação do docker windows (Mais detalhes em arquivos readme em \src\main\resources\infra):
  - Obtenção da imagem do Mysqld (cmd: docker pull mysql) e geração do docker yml para stop/start;
  - Obtenção da imagem Sonarqube/postgres (execução de script ) e geração do docker yml para stop/start.
Criação da branch master no github;
  - Clone desta branch para maquina local
Criação de projeto "Spring Starter Project", agregando os modulos basicos:
  - Aproveitamento de estruturas e classes de projeto em que já atuei, com as costumizações inerentes ao desafio LuizaLabs 

Em relação a boas praticas TDD, BDD ou DDD
  padroes de projeto(design patterns java)
Como o framework Spring já esta aderente a varios padroes de projeto, pelo container Spring, seu uso já implica automaticamente no uso de varias boas praticas no desenvolvimento java, por conta da Inversão de Controle e Injeção de Dependências

# Comandos Mavem para build:
Ir ao diretório que contem o arquivo pom.xml.
cmd: mvn clean install -U spring-boot:repackage dockerfile:build

