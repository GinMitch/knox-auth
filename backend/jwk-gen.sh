#!/bin/bash

keysDir="src/main/resources/jwks";

keyId=$(openssl rand -hex 10);
pathPem="${keysDir}/current.pem";
pathTxt="${keysDir}/current.txt";

openssl genrsa -out ${pathPem} 2048;
echo -e "    id: $keyId" > $pathTxt;

keyPkcs8=$(openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in ${pathPem} | tr -d '\n');
keyPkcs8=${keyPkcs8/"-----BEGIN PRIVATE KEY-----"/""};
keyPkcs8=${keyPkcs8/"-----END PRIVATE KEY-----"/""};
echo -e "    pkcs8: $keyPkcs8" >> $pathTxt;

keyX509=$(openssl rsa -pubout -in ${pathPem} | tr -d '\n');
keyX509=${keyX509/"-----BEGIN PUBLIC KEY-----"/""};
keyX509=${keyX509/"-----END PUBLIC KEY-----"/""};
echo -e "    x509: $keyX509" >> $pathTxt;
