# garage simulation REST service

by default garage simulation has 9 places on 3 floors

by default service uses in-memory relational database

## runnig service

```Shell
mvn clean install

java -jar garage-simulation-0.0.1-SNAPSHOT.jar
```
service will start on 8080 port

## requests example

check whether service started 
```Shell
curl -v "http://localhost:8080/garage/status"
```

places list
```Shell
curl -v "http://localhost:8080/garage/place/all"
```

car enter method
```Shell
curl -v -X PUT -d "carId=2" "http://localhost:8080/garage/place/lock"
```

car exit method
```Shell
curl -v -X PUT -d "carId=2" "http://localhost:8080/garage/place/release"
```

get place by car id
```Shell
curl -v "http://localhost:8080/garage/car/place?carId=2"
```

get free places count
```Shell
curl -v "http://localhost:8080/garage/place/free/count"
```
