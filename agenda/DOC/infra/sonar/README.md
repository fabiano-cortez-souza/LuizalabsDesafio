==========================================================================================
== No Prompt Docker: Comandos para normalizar configuração do uso de memória pelo Sonar 
==========================================================================================

# Comando para entrar no VM Linux que suporta o Docker
Docker VM Linux:
 docker-machine ssh

# No Docker, ajustar o nivel de memória 
Docker VM Linux:
 cmd: sudo sysctl -w vm.max_map_count=262144
 cmd: sudo sysctl -w fs.file-max=65536

Docker Windows - powershell:
 cmd: wsl -d docker-desktop
 cmd: sysctl -w vm.max_map_count=262144
 cmd: sysctl -w fs.file-max=65536

# Em VM Linux, via editor VI, avaliar os parametros docker abaixo, onde devem ser igual ou maior que os indicados
# Avaliar o parametro: ulimit -n valor: 65536
# Avaliar o parametro: ulimit -u valor: 4096
cmd: sudo vi /etc/init.d/docker

exit

==========================================================================================
== No Prompt Docker: Comandos subir e avaliar status do Sonar
==========================================================================================

No projeto ir ao local: /src/main/resources/infra/sonar
cmd: docker-compose -f sonar.yml up -d

# Avaliar se os respectivos containers "postgres" e "sonarqube" estão em pé
cmd: docker ps 

# Acompanhar os steps de subida do sonar e execucao deste no projeto
docker logs -f <CONTAINER ID >

==========================================================================================
== No Prompt Docker: Comandos para execução do Sonar
==========================================================================================
No projeto ir ao local do projeto : # Ex: "C:\Users\DELL\git\LuizalabsDesafio\agenda" 
cmd: mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install spring-boot:repackage

# Observar qual o IP da VM Docker, para usar no comando Mavem: mvn -Dsonar.host.url=http://<IP VM DOCKER>:9000 sonar:sonar
Docker VM Linux:
cmd: mvn -Dsonar.host.url=http://192.168.99.102:9000 sonar:sonar

Docker Windows:
cmd: mvn verify -Dsonar.host.url=http://localhost:9000 sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123


==========================================================================================
== Visualizar o report Sonar
==========================================================================================
VM Linux:
 Acessar a URL http://192.168.99.102:9000, no browser para acesso ao reporte gerado.

VM Windows:
 Acessar a URL http://localhost:9000, no browser para acesso ao reporte gerado, onde no primeiro acesso, user: admin, pass: admin
 