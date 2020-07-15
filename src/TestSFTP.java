
import com.rsarpal.PropertyReader.PropertyReader;
import com.rsarpal.SFTP_Client.SFTP_Client;


public class TestSFTP {

    public static void main(String[] args) {

        //SFTP_Client(String host,String userName, String password, String privateKeyFilePAth , String localFilePath ,String remoteFilePath)

        //SFTP with private key
        //SFTP_Client freshSftp= new SFTP_Client("localhost",22,"admin", "pwd","..\\resources\\keys\\public_dapi_batch_test_openssh_newformat" , "testuploadfile.txt" ,"/tmp");
        //freshSftp.establishConnectionKey();

        //SFTP_Client(String host,int port, String userName, String password, String localFilePath ,String remoteFilePath )
        SFTP_Client freshSftp= new SFTP_Client("localhost",22,"admin", "pwd", "testuploadfile.txt" ,"/tmp");


        freshSftp.establishConnection();
        System.out.println("ConnectionEstablished: " + freshSftp.amIConnected());

        if (freshSftp.amIConnected()) {
            freshSftp.sftpUploadFile();
        }

        //}
        freshSftp.disconnect();



    }
}
