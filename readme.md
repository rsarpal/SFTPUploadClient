# Description

Package supports uploading files via SFTP using private key OR username/password

# Dependencies
The client uses sshj library

# Package
 com.rsarpal.SFTP_Client
 
# Methods

1. establishConnectionKey()  - establish connection when using key
2. establishConnection()  - establish connection when using userid/password
3. amIConnected() - checks connection status
4. sftpUploadFile() - uploads the file

# Constructor

1. SFTP_Client(String host, int port, String userName, String password, String keyFilePath , String localFilePath ,String remoteFilePath)
2. SFTP_Client(String propertyFileName)
3. SFTP_Client(String host, int port,String userName, String password, String localFilePath ,String remoteFilePath )


# Example

/src/TestSFTP


