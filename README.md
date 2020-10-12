# springboot exercise
The Sample Service can be run in either container or as regular service
Use Case: 
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany

The above data is hard coded in the map and accessed in the bussion service(RouteMapRestService).

Container:
Docker File which contains set of intstructions to execute the container in the Docker in the plattform
This program can run in the container. The following commmands can use to run this program in the container.

docker build -f  DockerFile -t roadmapspringbootexercise .

docker run -p 8080:8080 roadmapspringbootexercise .

if you are running this program in windows machine, then please use docker machine ip(192.168.99.100). Please access like below
http://192.168.99.100:8080/connected?origin=Boston&destination=Philadelphia
