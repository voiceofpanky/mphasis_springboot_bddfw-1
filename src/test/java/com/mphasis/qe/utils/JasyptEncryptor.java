package com.mphasis.qe.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptEncryptor {
    private static StringEncryptor stringEncryptor(String seed) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("secret");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    private static String encrypt(String text, String seed) {
        StringEncryptor textEncryptor = stringEncryptor(seed);
        String encryptedText = textEncryptor.encrypt(text);
        return encryptedText;
    }

    public static String decrypt(String text) {
        StringEncryptor textEncryptor = stringEncryptor(text);
        String decryptedText = textEncryptor.decrypt(text);
        return decryptedText;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String uid = "";
        String pwd = "";
        String seed = "";

        try {
            System.out.print("Enter seed string : ");
            seed = scanner.nextLine();
            System.out.print("UID : ");
            uid = scanner.nextLine();
            System.out.print("PWD : ");
            pwd = scanner.nextLine();
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println("System.in was closed; exiting");
        }
        System.out.println("Encrypted UID : " + encrypt(uid, seed));
        System.out.println("Encrypted PWD : " + encrypt(pwd, seed));
    }
}
