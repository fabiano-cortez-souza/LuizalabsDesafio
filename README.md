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
