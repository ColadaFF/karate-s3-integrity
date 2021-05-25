package software.ias;

import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

public class DependencyProvider {
    public final S3Client s3Client;

    public DependencyProvider() {
        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .build();
    }
}
