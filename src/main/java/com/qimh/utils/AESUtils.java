package com.qimh.utils;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;



/**
 * • Created by qimh
 * @date 2020-08-27
 */
public class AESUtils {
    private static final Logger log = LoggerFactory.getLogger(AESUtils.class);
    private static final String Algorithm = "DESede";

    /**
     * • 将byte[]转为各种进制的字符串
     * •
     * • @param  bytes byte[]
     * • @param  radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * • @return  转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
// 这里的1代表正数
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * • base 64 encode
     * •
     * • @param  bytes 待编码的byte[]
     * • @return  编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return BaseEncoding.base64().encode(bytes);
    }

    /**
     * • base 64 decode
     * •
     * • @param  base64Code 待解码的base 64 code
     * • @return  解码后的byte[]
     * • @throws  Exception
     */
    public static byte[] base64Decode(String base64Code) {
        return StringUtils.isEmpty(base64Code) ? null : BaseEncoding.base64().decode(base64Code);
    }

    /**
     * • 获取byte[]的md5值
     * •
     * • @param  bytes byte[]
     * • @return  md5
     * • @throws  Exception
     */
    public static byte[] md5(byte[] bytes) {
        return Hashing.md5().hashBytes(bytes).asBytes();
    }

    /**
     * • 获取字符串md5值
     * •
     * • @param  msg
     * • @return  md5
     * • @throws  Exception
     */
    public static byte[] md5(String msg) {
        return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
    }

    /**
     * • 结合base64实现md5加密
     * •
     * • @param  msg 待加密字符串
     * • @return  获取md5后转为base64
     * • @throws  Exception
     */
    public static String md5Encrypt(String msg) {
        return StringUtils.isEmpty(msg) ? null : base64Encode(md5(msg));
    }

    /**
     * • AES加密
     * •
     * • @param  content    待加密的内容
     * • @param  encryptKey 加密密钥
     * • @return  加密后的byte[]
     * • @throws  Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keys = Hex.decodeHex(encryptKey.toCharArray());
        SecretKeySpec key = new SecretKeySpec(keys, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keys));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * • AES加密为base 64 code
     * •
     * • @param  content    待加密的内容
     * • @param  encryptKey 加密密钥
     * • @return  加密后的base 64 code
     * • @throws  Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
//        if (log.isDebugEnabled())
//            log.debug("aesEncrypt-->{},{}", content, encryptKey);
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * • AES解密
     * •
     * • @param  encryptBytes 待解密的byte[]
     * • @param  decryptKey   解密密钥
     * • @return  解密后的String
     * • @throws  Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] keys = Hex.decodeHex(decryptKey.toCharArray());
            SecretKeySpec key = new SecretKeySpec(keys, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keys));
            return new String(cipher.doFinal(encryptBytes));
        } catch (Exception e) {
            log.error("[monitor][encryption][AES解密]", e);
        }
        return null;
    }

    /**
     * • 将base 64 code AES解密
     * •
     * • @param  encryptStr 待解密的base 64 code
     * • @param  decryptKey 解密密钥
     * • @return  解密后的string
     * • @throws  Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) {
        log.debug("aesDecrypt-->{} ===={}", encryptStr, decryptKey);
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * • md5 加密
     * •
     * • @param  s
     * • @return
     */
    public final static String MD5(String s) {
        return Hashing.md5().hashString(s, Charsets.UTF_8).toString();
    }

    /**
     * • 加密方法
     * •
     * • @param  src 源数据的字节数组
     * • @param  key
     * • @return
     */
    public static byte[] encrypt3DES(byte[] src, byte[] key) {
        try {
//生成密钥
            SecretKey deskey = new SecretKeySpec(key, Algorithm);
//实例化负责加密/解密的Cipher工具类
            Cipher c1 = Cipher.getInstance(Algorithm);
//初始化为加密模式
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            log.error("[monitor][encryption][加密方法]", e1);
        } catch (javax.crypto.NoSuchPaddingException e2) {
            log.error("[monitor][encryption][加密方法]", e2);
        } catch (java.lang.Exception e3) {
            log.error("[monitor][encryption][加密方法]", e3);
        }
        return null;
    }

    /**
     * • 解密函数
     * •
     * • @param  src 密文的字节数组
     * • @param  key
     * • @return
     */
    public static byte[] decrypt3DES(byte[] src, byte[] key) {
        try {
            SecretKey deskey = new SecretKeySpec(key, Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
//初始化为解密模式
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            log.error("[monitor][encryption][解密函数]", e1);
        } catch (javax.crypto.NoSuchPaddingException e2) {
            log.error("[monitor][encryption][解密函数]", e2);
        } catch (java.lang.Exception e3) {
            log.error("[monitor][encryption][解密函数]", e3);
        }
        return null;
    }

    public static String signData(String data, PrivateKey privateKey, String algorithm) {
        try {
            byte[] sigBytes = signData(data.getBytes("utf-8"), privateKey, algorithm);
            return base64Encode(sigBytes);
        } catch (Exception e) {
            log.error("[monitor][encryption][日期处理]", e);
        }
        return null;
    }

    private static byte[] signData(byte[] bytes, PrivateKey privateKey, String algorithm) {
        try {
            Signature sig = Signature.getInstance(algorithm);
            sig.initSign(privateKey);
            sig.update(bytes);
            return sig.sign();
        } catch (Exception e) {
            log.error("[monitor][encryption][初始化]", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyData(String data, String signValue, PublicKey key, String algorithm) {
        byte[] bytes = data.getBytes(Charsets.UTF_8);
        return verifyData(bytes, signValue, key, algorithm);
    }

    public static boolean verifyData(byte[] bytes, String signValue, PublicKey key, String algorithm) {
        try {
            Signature sig = Signature.getInstance(algorithm);
            sig.initVerify(key);
            sig.update(bytes);
            byte[] signValueByte = base64DecodeBySeparator(signValue, "\n");
            if (!sig.verify(signValueByte)) {
                return false;
            }
            log.debug("验签OK！");
            return true;
        } catch (Exception e) {
            log.error("[monitor][encryption][验签]{}", e);
        }
        return false;
    }

    public static PublicKey getPublicKey(String publicKeyPath) {
        InputStream is = null;
        try {
            is = new FileInputStream(publicKeyPath);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
            return cert.getPublicKey();
        } catch (FileNotFoundException e) {
            log.error("[monitor][IO][读取公钥]", e);
        } catch (CertificateException e) {
            log.error("[monitor][encryption][验签]", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("[monitor][IO][读取公钥]", e);
                }
            }
        }
        return null;
    }

    public static PrivateKey getPrivateKey(String privateKeyPath, String keyPwd, String storeAlias) {
        char[] storePwdArr;
        BufferedInputStream bis = null;
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(privateKeyPath);
            bis = new BufferedInputStream(fis);
// store password
            storePwdArr = keyPwd.toCharArray();
            ks.load(bis, storePwdArr);
            PrivateKey priv = (PrivateKey) ks.getKey(storeAlias, storePwdArr);
            return priv;
        } catch (Exception e) {
            log.error("[monitor][IO][读取私钥]", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] base64DecodeBySeparator(String crypto, String separator) {
        return StringUtils.isEmpty(crypto) ? null : BaseEncoding.base64().withSeparator(separator, 70).decode(crypto);
    }

    public static void main(String[] args) throws Exception {
        byte[] b = {'a', 'b'};
        char c = 'c';
        System.out.println("b:" + b.toString());
        String aa = "80006379";
        String encode = aesEncrypt(aa, "676243218923905CF94CB52A3C9D3EB3");
        System.out.println("============编码:" + encode);
        System.out.println("============解码:" + aesDecrypt(encode, "676243218923905CF94CB52A3C9D3EB3"));
    }
}