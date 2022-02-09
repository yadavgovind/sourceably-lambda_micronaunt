package com.oms.projectbuddy.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileOperation {
    private static final Logger LOG = LoggerFactory.getLogger(FileOperation.class);
//    private final Path fileStorageLocation;

//    @Value("${aws.s3.bucket}")
//    private String awsS3Bucket;
//
//    private AmazonS3 amazonS3;

//    @Autowired
//    public FileOperation(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3Bucket) {
//        this.amazonS3 = AmazonS3ClientBuilder.standard()
//                .withCredentials(awsCredentialsProvider)
//                .withRegion(awsRegion.getName()).build();
//        this.awsS3Bucket = awsS3Bucket;
//    }


//    public FileUploadResponse storeFile1(MultipartFile multipartFile, String fileType) {
//        FileUploadResponse fileUploadResponse = new FileUploadResponse();
//
//        if (multipartFile == null) {
//            return fileUploadResponse;
//        }
//
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
//        if (extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")
//        || extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx") || extension.equalsIgnoreCase("xlsx")) {
//
//            // Check if the file's name contains invalid characters
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            try {
//                String generateFileName = fileType + "_" + Instant.now().toEpochMilli() + "." + extension;
//                InputStream fileData = multipartFile.getInputStream();
//
//                PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, generateFileName,
//                        fileData, new ObjectMetadata());
//
//                this.amazonS3.putObject(putObjectRequest);
//
//                String fileUrl = amazonS3.getUrl(awsS3Bucket, generateFileName).toExternalForm();
//
//                fileUploadResponse.setFileName(generateFileName);
//                fileUploadResponse.setFilePath(fileUrl);
//
//                LOG.info(fileName + "  URL IS  " + fileUrl);
//
//            } catch (AmazonServiceException | IOException ex) {
//                LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
//            }
//
//        }
//        return fileUploadResponse;
//    }
//    public FileUploadResponse storeDoc1(MultipartFile multipartFile, String fileType) {
//        FileUploadResponse fileUploadResponse = new FileUploadResponse();
//
//        if (multipartFile == null) {
//            return fileUploadResponse;
//        }
//
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
//        if (extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc")
//                || extension.equalsIgnoreCase("docx") || extension.equalsIgnoreCase("xlsx")){
//
//            // Check if the file's name contains invalid characters
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            try {
//                //String generateFileName = Instant.now().toEpochMilli() +"_"+fileName ;
//                InputStream fileData = multipartFile.getInputStream();
//
//                PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, fileName,
//                        fileData, new ObjectMetadata());
//
//                this.amazonS3.putObject(putObjectRequest);
//
//                String fileUrl = amazonS3.getUrl(awsS3Bucket, fileName).toExternalForm();
//
//                fileUploadResponse.setFileName(fileName);
//                fileUploadResponse.setFilePath(fileUrl);
//
//                LOG.info(fileName + "  URL IS  " + fileUrl);
//
//            } catch (AmazonServiceException | IOException ex) {
//                LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
//            }
//
//        }
//        return fileUploadResponse;
//    }
//    public String deleteDoc(String fileName){
//        try {
//            this.amazonS3.deleteObject(awsS3Bucket, fileName);
//            return "removed "+fileName;
//        }catch (Exception ex){
//            LOG.error("error [" + ex.getMessage() + "] occurred while deleting [" + fileName + "] ");
//            return ex.getMessage();
//        }
//    }
//
//
//    public FileUploadResponse storeVideo1(MultipartFile multipartFile, String fileType) {
//        FileUploadResponse fileUploadResponse = new FileUploadResponse();
//
//        if (multipartFile == null) {
//            return fileUploadResponse;
//        }
//
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
//        if (extension.equalsIgnoreCase("mp4")) {
//
//            // Check if the file's name contains invalid characters
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            try {
//                String generateFileName = fileType + "#" + Instant.now().toEpochMilli() + "." + extension;
//                InputStream fileData = multipartFile.getInputStream();
//
//                PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, generateFileName,
//                        fileData, new ObjectMetadata());
//
//                this.amazonS3.putObject(putObjectRequest);
//
//                String fileUrl = amazonS3.getUrl(awsS3Bucket, generateFileName).toExternalForm();
//
//                fileUploadResponse.setFileName(generateFileName);
//                fileUploadResponse.setFilePath(fileUrl);
//
//                LOG.info(fileName + "  URL IS  " + fileUrl);
//
//            } catch (AmazonServiceException | IOException ex) {
//                LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
//            }
//        }
//        return fileUploadResponse;
//    }
//
//    public FileUploadResponse storeMediaDetails(MultipartFile multipartFile, String fileType) throws Exception {
//        FileUploadResponse fileUploadResponse = new FileUploadResponse();
//
//        if (multipartFile == null) {
//            return fileUploadResponse;
//        }
//
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
//        String mediaType = checkForMediaTypeExists(extension);
//        // Check if the file's name contains invalid characters
//        if (fileName.contains("..")) {
//            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//        }
//
//        try {
//            String generateFileName = fileType + "_" + Instant.now().toEpochMilli() + "." + extension;
//            InputStream fileData = multipartFile.getInputStream();
//
//            PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, generateFileName,
//                    fileData, new ObjectMetadata());
//
//            this.amazonS3.putObject(putObjectRequest);
//
//
//            String fileUrl = amazonS3.getUrl(awsS3Bucket, generateFileName).toExternalForm();
//
//            fileUploadResponse.setFileName(generateFileName);
//            fileUploadResponse.setFilePath(fileUrl);
//            fileUploadResponse.setMediaType(mediaType);
//            LOG.info(fileName + "  URL IS  " + fileUrl);
//
//        } catch (AmazonServiceException | IOException ex) {
//            LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
//        }
//
//
//        return fileUploadResponse;
//    }
//
//    private String checkForMediaTypeExists(String extension) throws Exception {
//        extension = extension.toLowerCase();
//        switch (extension) {
//            //Image Type Below
//            case "tif":
//                return "image";
//            case "jpg":
//                return "image";
//            case "gif":
//                return "image";
//            case "png":
//                return "image";
//            //VideoTypes Below
//            case "mp4":
//                return "video";
//            case "mov":
//                return "video";
//            case "wmv":
//                return "video";
//            case "avi":
//                return "video";
//            case "avchd":
//                return "video";
//            case "mkv":
//                return "video";
//            case "webm":
//                return "video";
//            case "mpeg":
//                return "video";
//            default:
//                throw new Exception("No Media Specified For the Upload");
//        }
//    }
   // public String deleteDocument(String fileName)

    /*public FileUploadResponse storeCandidateCvFile(MultipartFile multipartFile, CandidateCv candidateCv, String fileType) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();

        if (multipartFile == null) {
            return fileUploadResponse;
        }

        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
        if (extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx")) {

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            try {
                //String generateFileName = fileType + "_" + Instant.now().toEpochMilli() + "." + extension;
                String generateFileName = candidateCv.getCvSequenceId() + "." + extension;
                InputStream fileData = multipartFile.getInputStream();

                PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, generateFileName,
                        fileData, new ObjectMetadata());

                this.amazonS3.putObject(putObjectRequest);

                String fileUrl = amazonS3.getUrl(awsS3Bucket, generateFileName).toExternalForm();

                fileUploadResponse.setFileName(generateFileName);
                fileUploadResponse.setFilePath(fileUrl);

                LOG.info(fileName + "  URL IS  " + fileUrl);

            } catch (AmazonServiceException | IOException ex) {
                LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
            }

        }
        return fileUploadResponse;
    }

    public FileUploadResponse storeInterviewVideo(MultipartFile multipartFile, String fileType, String s1) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();

        if (multipartFile == null) {
            return fileUploadResponse;
        }

        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //Check FileType If Pdf OR JPG OR PNG Then Save Otherwise Return null
        if (extension.equalsIgnoreCase("mp4")) {

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            try {
                String generateFileName = s1 + "." + extension;
                InputStream fileData = multipartFile.getInputStream();

                PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, generateFileName,
                        fileData, new ObjectMetadata());

                this.amazonS3.putObject(putObjectRequest);

                String fileUrl = amazonS3.getUrl(awsS3Bucket, generateFileName).toExternalForm();

                fileUploadResponse.setFileName(generateFileName);
                fileUploadResponse.setFilePath(fileUrl);

                LOG.info(fileName + "  URL IS  " + fileUrl);

            } catch (AmazonServiceException | IOException ex) {
                LOG.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
            }
        }
        return fileUploadResponse;

    }*/
}
