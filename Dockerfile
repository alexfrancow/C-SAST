FROM debian
MAINTAINER alexfrancow

COPY docker-entrypoint.sh /docker-entrypoint.sh

RUN apt update; \
    apt install -y gcc \
    make \
    libssl-dev \
    git

RUN git clone https://github.com/CoolerVoid/codewarrior/ && cd codewarrior && make && \
    openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout cert/certkey.key -out cert/certificate.crt -subj "/CN=$cn\/emailAddress=admin@$cn/C=US/ST=Ohio/L=Columbus/
O=Widgets Inc/OU=Some Unit" && \
    cat cert/certificate.crt cert/certkey.key > cert/certkey.pem && chmod 777 /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 1345
