FROM debian
MAINTAINER alexfrancow

COPY docker-entrypoint.sh /docker-entrypoint.sh

RUN apt update; \
    apt install -y gcc \
    make \
    libssl-dev \
    git

RUN git clone https://github.com/CoolerVoid/codewarrior/ && cd codewarrior && \
    sed -i -e 's/1345/1011/g' src/main.c && \
    sed -i -e 's/if(whitelist_ip(addr)==true)/if(whitelist_ip(addr)==false)/g' src/routes.c && \
    sed -i -e 's/127.0.0.1:1345/192.168.0.179:1011/g' web/index.html && \
    sed -i -e 's/127.0.0.1:1345/192.168.0.179:1011/g' web/viewcode.html && \
    sed -i -e 's/127.0.0.1:1345/192.168.0.179:1011/g' web/edit_module.html && \
    make && openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout cert/certkey.key -out cert/certificate.crt -subj "/CN=$cn\/emailAddress=admin@$cn/C=US/ST=Ohio/L=Columbus/O=Widgets Inc/OU=Some Unit" && \
    cat cert/certificate.crt cert/certkey.key > cert/certkey.pem && chmod 777 /docker-entrypoint.sh

RUN cd /codewarrior/web/ && grep -rnw wss

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 443 1011
