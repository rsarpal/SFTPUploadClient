/*
* Author : Rishu Sarpal
* Date : 04-07-2020
* Description : SFTP Client for uploading files. Supports key files
*
* compile : javac SFTP_Client.java  -cp "sshj-0.29.0.jar;slf4j-api-1.7.9.jar;bcprov-jdk15on-1.65.jar;eddsa-0.3.0.jar;."
* run : java -cp "sshj-0.29.0.jar;slf4j-api-1.7.9.jar;bcprov-jdk15on-1.65.jar;eddsa-0.3.0.jar;."  SFTP_Client
*
*  Java Doc - https://javadoc.io/static/com.hierynomus/sshj/0.29.0/net/schmizz/sshj/SSHClient.html
*  Source Git sshj - https://github.com/hierynomus/sshj
*  Maven Repo - https://search.maven.org/artifact/com.hierynomus/sshj/0.29.0/jar
* */


package com.rsarpal.SFTP_Client;

import com.rsarpal.PropertyReader.PropertyReader;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.xfer.FileSystemFile;
import java.io.*;


public class SFTP_Client {

    private String remoteHost;
    private String userName;
    private String password;
    private String privateKeyFilePAth;
    private String remoteFilePath;
    private String localFilePath;
    private int port;

    private SSHClient sshConn;

    public  SFTP_Client(String host, int port, String userName, String password, String keyFilePath , String localFilePath ,String remoteFilePath){
        this.remoteHost=host;
        this.port=port;
        this.userName=userName;
        this.password=password;
        this.privateKeyFilePAth=keyFilePath;
        this.remoteFilePath=remoteFilePath;
        this.localFilePath=localFilePath;


    }

    public  SFTP_Client(String propertyFileName){
        PropertyReader pr = new PropertyReader(propertyFileName);
        this.remoteHost=pr.getPropertyValue("host");;
        this.port= Integer.parseInt(pr.getPropertyValue("port"));
        this.userName=pr.getPropertyValue("username");
        this.password=pr.getPropertyValue("password");
        this.privateKeyFilePAth=pr.getPropertyValue("keyFilePath");
        this.remoteFilePath=pr.getPropertyValue("remoteFilePath");
        this.localFilePath=pr.getPropertyValue("localFilePath");
    }


    public  SFTP_Client(String host, int port,String userName, String password, String localFilePath ,String remoteFilePath ){
        this.remoteHost=host;
        this.port=port;
        this.userName=userName;
        this.password=password;
        this.remoteFilePath=remoteFilePath;
        this.localFilePath=localFilePath;
    }


    public void establishConnectionKey(){

        try {
            this.sshConn = new SSHClient();

            // addHostKeyVerifier , needed to establish connection else will get key verification error
            this.sshConn.addHostKeyVerifier(new PromiscuousVerifier());

            //establish connection
            this.sshConn.connect(this.remoteHost,this.port);

            // pass userid and the private keyfile with loadkeys which returns Keyprovider
            this.sshConn.authPublickey(this.userName,this.sshConn.loadKeys(this.privateKeyFilePAth));
            //this.sshConn.authPassword(this.userName,this.password);
            System.out.println("SFTP_Client: publickey auth done");


        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

    public void establishConnection(){

        try {
            this.sshConn = new SSHClient();
            this.sshConn.addHostKeyVerifier(new PromiscuousVerifier());
            this.sshConn.connect(this.remoteHost);
            this.sshConn.authPassword(this.userName,this.password);


        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

    public boolean amIConnected(){
        return this.sshConn.isConnected();
    }


    public void sftpUploadFile(){

        try {

            try (SFTPClient sftp = this.sshConn.newSFTPClient()){
                final String src = this.localFilePath;
                try {
                    sftp.put(new FileSystemFile(src), this.remoteFilePath);
                } finally {
                    System.out.println("SFTP_Client: File Uploaded Successfully");
                    sftp.close();
                }
            } finally {
                this.sshConn.disconnect();
            }

        }catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            this.sshConn.disconnect();
        }catch (IOException ie) {
            ie.printStackTrace();
        }
    }


}
