==========================================================================================
== No Prompt Docker: Comandos subir e avaliar status do DB MySQL
==========================================================================================

No projeto ir ao local: /src/main/resources/infra/MySQL
cmd: docker-compose -f stack_db.yml up -d

# Avaliar se os respectivos containers "postgres" e "sonarqube" estão em pé
docker ps 

# Acompanhar os steps de subida do sonar e execucao deste no projeto
docker logs -f <CONTAINER ID >