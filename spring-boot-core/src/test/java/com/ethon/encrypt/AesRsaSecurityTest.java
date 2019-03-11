package com.ethon.encrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.ethan.core.constant.ConfigsEnum;
import com.ethan.core.model.AuthorityName;
import com.ethan.core.providers.TimeProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

// @RunWith(SpringRunner.class)
// @SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= WebportalBeApplication.class)
public class AesRsaSecurityTest {
    @Value("${spring.primary.datasource.username}")
    private String username1;
    @Value("${spring.secondary.datasource.username}")
    private String username2;

    @Test
    public void str() {
        // String pwd = TimeProvider.passwordEncoder().encode("123456");
        // System.out.println(pwd);
        // boolean match = TimeProvider.passwordEncoder().matches("123456", pwd);
        // System.out.println(match);
        List lists = null;
        Optional<String> token = Optional.ofNullable(lists).orElse(Collections.emptyList()).stream().findFirst();
        System.out.println("str():-->" +token.isPresent());
    }

    @Test
    public void encryptPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("password");
        System.out.println(result);
        System.out.println(encoder.matches("password", "$2a$10$i.ZD5NBcLsWQNhEYlr6kCO8vVHASvG5NGChAAVw5zQ9550OgEHbCC"));
    }

//    @Test
    public void shouldAlwaysAllowPublic() throws Exception {
        System.out.println("-----------");
        getEncryptStr();
    }
    @Test
    public void getEncryptStr() {
        try {
            StringEncryptor encryptor = null; // beanConfig.stringEncryptor();
            String entStr = encryptor.encrypt("sa");
            System.out.println(entStr);
            System.out.println(encryptor.decrypt(entStr));
            entStr = encryptor.encrypt("sa");
            System.out.println(entStr);
            System.out.println(encryptor.decrypt(entStr));
            
            System.out.println("username1:"+username1+" username2:"+username2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generatePrivateStr() {
        StringEncryptor encryptor = null; // beanConfig.stringEncryptor();
        try {
            String data = "LongKeyEncryptLongPassssssssssWord";
            System.out.println("############generatePrivateStr");
            // use private key to encrypt.
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey());
            byte[] jmdata = cipher.doFinal(data.getBytes());
            System.out.println(Base64.getEncoder().encodeToString(jmdata));
            // use public key to decrypt.
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey());
            jmdata = cipher.doFinal(jmdata);
            System.out.println(new String(jmdata));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getReosurces() {
        try {
            InputStream resource = new ClassPathResource("certificate/keypass.text").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            String employees = reader.lines().collect(Collectors.joining("\n"));
            System.out.println(employees);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void listAlgorithm() {
        Security.addProvider(new BouncyCastleProvider());
        for (Provider provider : Security.getProviders()) {
            System.out.println(provider.getName());
            for (Provider.Service s : provider.getServices()) {
                if (s.getType().equals("Cipher"))
                    System.out.println("\t" + s.getType() + " " + s.getAlgorithm());
            }
        }
    }

    public void springEncrypt() {
        String salt = KeyGenerators.string().generateKey();
        System.out.println(salt);
        String password = "";
        String str = "";
        BytesEncryptor encryptor = Encryptors.standard(password, salt);
        byte[] encryptStr = encryptor.encrypt(str.getBytes());
        String result = Base64.getEncoder().encodeToString(encryptStr);
        System.out.println(result);
        byte[] decoder = Base64.getDecoder().decode(result);
        String decoderStr = new String(encryptor.decrypt(decoder));
        System.out.println(decoderStr);
    }

    public void getProviders() {
        try {
            // Required way
//            String algorithm = "PBEWithHmacSHA256AndAES_256";
            String algorithm = "AES/GCM/NoPadding";
            Cipher rawCipher = Cipher.getInstance(algorithm);
            PBEKeySpec pbeKeySpec = new PBEKeySpec("changeme".toCharArray());
            final SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            SecretKey tempKey = factory.generateSecret(pbeKeySpec);
            PBEParameterSpec saltParameterSpec = new PBEParameterSpec(Hex.decode("0123456789ABCDEF"), 1000);
            rawCipher.init(Cipher.ENCRYPT_MODE, tempKey, saltParameterSpec);

            // Save the generated ASN.1-encoded parameters
            byte[] algorithmParameterBytes = rawCipher.getParameters().getEncoded();

            byte[] cipherBytes = rawCipher.doFinal("foo".getBytes(StandardCharsets.UTF_8));

            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(algorithm);
            algorithmParameters.init(algorithmParameterBytes);
            rawCipher.init(Cipher.DECRYPT_MODE, tempKey, algorithmParameters);
            byte[] plainBytes = rawCipher.doFinal(cipherBytes);
            String recovered = new String(plainBytes, StandardCharsets.UTF_8);
            System.out.println(recovered);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrivateKey getPrivateKey() throws IOException {
        InputStream resourceContent = null;
        String password = new String(Base64.getDecoder().decode("c2FzLUNENXZoJFl5Z1hQ"));
        try {
            resourceContent = new ClassPathResource(ConfigsEnum.PRIVATE_KEY.value()).getInputStream();
            KeyStore ks = KeyStore.getInstance("JKS");
            BufferedInputStream ksbufin = new BufferedInputStream(resourceContent);
            ks.load(ksbufin, password.toCharArray());
            PrivateKey prikey = (PrivateKey) ks.getKey("gaickey", password.toCharArray());
            return prikey;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } finally {
            if (resourceContent != null) {
                resourceContent.close();
            }
        }
        return null;
    }

    public PublicKey getPublicKey() throws IOException {
        InputStream resourceContent = null;
        try {
            resourceContent = new ClassPathResource(ConfigsEnum.PUBLIC_KEY.value()).getInputStream();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(resourceContent);
            PublicKey pubKey = cert.getPublicKey();
            return pubKey;
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }finally {
            if(resourceContent !=null) {
                resourceContent.close();
            }
        }
        return null;
    }
    public String getKeyPass() throws IOException {
        InputStream resourceContent = null;
        BufferedReader reader = null;
        String result = "";
        try {
            resourceContent = new ClassPathResource(ConfigsEnum.KEY_PASS.value()).getInputStream();
            reader = new BufferedReader(new InputStreamReader(resourceContent));
            result = reader.lines().collect(Collectors.joining(""));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader !=null) {
                reader.close();
            }
            if(resourceContent !=null) {
                resourceContent.close();
            }
        }
        return result;
    }
}
