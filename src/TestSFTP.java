
import com.rsarpal.PropertyReader.PropertyReader;
import com.rsarpal.SFTP_Client.SFTP_Client;


public class TestSFTP {

    public static void main(String[] args) {

        //SFTP_Client(String host,String userName, String password, String privateKeyFilePAth , String localFilePath ,String remoteFilePath)
        //SFTP_Client freshSftp= new SFTP_Client("10.130.100.150",22,"c949281", "","z:\\Rishu\\scripts\\IdeaProjects\\SFTP_Client\\resources\\keys\\public_dapi_batch_test_openssh_newformat" , "C:\\Rishu\\UTU01_DAPI_SFTP_Upload_001\\UTUFiles\\RSUTU_63CE44_20070714279.dat" ,"/tmp");
        //freshSftp.establishConnectionKey();

        //SFTP_Client(String host,int port, String userName, String password, String localFilePath ,String remoteFilePath )
        SFTP_Client freshSftp= new SFTP_Client("10.130.100.150",22,"batch", "deltabatch01", "C:\\Rishu\\UTU01_DAPI_SFTP_Upload_001\\UTUFiles\\RSUTU_63CE44_20070714279.dat" ,"/opt/platform/batch/1.0/applications/dapi-utu-payments-v1/in/");


        freshSftp.establishConnection();
        System.out.println("ConnectionEstablished: " + freshSftp.amIConnected());

        if (freshSftp.amIConnected()) {
            freshSftp.sftpUploadFile();
        }

        //}
        freshSftp.disconnect();



    }
}
