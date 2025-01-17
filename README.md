<h1 align="center"><img width=40px src="https://cdn-icons-png.flaticon.com/128/33/33736.png"> Football Manager <img width=40px src="https://cdn-icons-png.flaticon.com/128/33/33736.png"></h1>
<br>
<h3 align=center><img width=25px src="https://cdn-icons-png.flaticon.com/128/1/1176.png"> About project <img width=25px src="https://cdn-icons-png.flaticon.com/128/1/1176.png"></h3>
<p>This project was created to simulate the management of football teams and players. The user can create new players, create new teams and add players to teams. To buy a player, the following formula is used: player experience * 100000 / player age + team commission</p>
<br>
<h2 align=center><img src="https://cdn-icons-png.flaticon.com/128/4365/4365271.png" width=30px>Technologies <img src="https://cdn-icons-png.flaticon.com/128/4365/4365271.png" width=30px></h2>
<code>Java 17</code> |
<code>Spring Boot</code> |
<code>Spirng Boot Dev Tools</code> |
<code>Spring Web</code> |
<code>Spring Data JPA</code> |
<code>Lombok</code> |
<code>PostgreSQL</code>
<br>
Future: <code>MapStruct</code>, <code>Docker</code>, <code>Pagination</code>, <code>Liquibase</code>, <code>UI</code>
<h3 align=center><img width=30px src="https://cdn-icons-png.flaticon.com/128/4357/4357645.png"> How to run <img width=30px src="https://cdn-icons-png.flaticon.com/128/4357/4357645.png"></h3>



<p>1. Create a database with the command <code>CREATE DATABASE football_manager;</code></p>
<p>2. Change the password and username for accessing the database in the <code>application.properties</code> file</p>
<p>3. Run this command in football-manager-java package: <code>mvn package</code></p>
<p>2. Run the project with IDEA or with the command <code>java -jar target/manager-0.0.1-SNAPSHOT.jar</code></p>
<p>3. Use <a href="https://www.postman.com/maintenance-operator-97553329/test-projects/request/u09d82i/transfer-player-from-team-to-team">Postman collection</a> to test this project</p>
