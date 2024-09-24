package gin.knox.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class JwtDataProviderImpl implements JwtDataProvider {

    @Getter
    @Value("${jwt.issuer}")
    private String issuer;

    @Getter
    @Value("${jwt.key.id}")
    private String privateKeyId;

    @Value("${jwt.key.pkcs8}")
    private String keyPkcs8;

    @Value("${jwt.key.x509}")
    private String keyX509;

    private RSAPublicKey publicKey;

    @Getter
    private RSAPrivateKey privateKey;

    @PostConstruct
    private void postConstruct() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        this.publicKey =
                (RSAPublicKey)
                        keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(this.keyX509)));
        this.privateKey =
                (RSAPrivateKey)
                        keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(this.keyPkcs8)));
    }

    public RSAPublicKey getPublicKeyById(String kid) {
        return this.publicKey;
    }
}
