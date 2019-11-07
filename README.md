# C-SAST
An automated software vulnerability detection app with machine learning.


docker build -t "test:dockerfile" .
docker images
<get image ID from test repository>
  
docker run -d -p 443:443 <image ID>
docker ps
<get container ID>
docker exec -it <container ID> /bin/bash
