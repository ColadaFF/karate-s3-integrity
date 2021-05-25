package software.ias;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

public class FileChecker {
    private static final DependencyProvider dependencyProvider = new DependencyProvider();


    private static final Logger logger = LoggerFactory.getLogger(FileChecker.class);

    public static String getMd5FromS3(String fileName) {
        logger.info("java was called from karate ! id: {}", fileName);
        S3Client s3Client = dependencyProvider.s3Client;

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket("some-bucket")
                .key(fileName)
                .build();
        ResponseBytes<GetObjectResponse> responseInputStream = s3Client.getObjectAsBytes(request);

        byte[] digest = new DigestUtils(MD5).digest(responseInputStream.asByteArray());
        return Base64.encodeBase64String(digest);
    }

}
