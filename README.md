# C-SAST
An automated software vulnerability detection app with machine learning.

```bash
$ docker build -t "test:dockerfile" .
$ docker images
<get image ID from test repository>

#Persistencia:
$ docker volume create codewarrior
codewarrior

$ docker volume inspect codewarrior
[
    {
        "CreatedAt": "2019-11-07T16:05:42+01:00",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/codewarrior/_data",
        "Name": "codewarrior",
        "Options": {},
        "Scope": "local"
    }
]

# Todo lo que haya dentro de /var/lib/docker/volumes/codewarrior/_data va a aparecer en la carpeta /apps de nuestro contenedor.

$ docker run -d -v codewarrior:/apps -p 1345:1345 <image ID>
$ docker ps

CONTAINER ID        IMAGE               COMMAND                  CREATED              PORTS                             
ccc823cec873        6ab4e54752df        "/docker-entrypoint.…"   About a minute ago   443/tcp, 0.0.0.0:1345->1345/tcp

$ docker exec -it <container ID> /bin/bash

```
Test ```/apps/DVWA/vulnerabilities/sqli/source```

<p align="center"><img src="images/01.png" /></p>
