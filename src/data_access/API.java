package data_access;

import com.google.cloud.storage.*;

import java.nio.file.Paths;

public class API implements APIInterface {

    public static void main(String[] args) {
        API api = new API();

        api.download("./src/data_access/data.json", "data.json");
    }

    @Override
    public void upload(String dataPath, String fileName) {
        try {
            // The ID of your GCP project
            String projectId = "newsoftware-433920";

            // The ID of your GCS bucket
            String bucketName = "newsoftware-storage-bucket1";

            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            // Optional: set a generation-match precondition to avoid potential race
            // conditions and data corruptions. The request returns a 412 error if the
            // preconditions are not met.
            Storage.BlobWriteOption precondition;
            if (storage.get(bucketName, fileName) == null) {
                // For a target object that does not yet exist, set the DoesNotExist precondition.
                // This will cause the request to fail if the object is created before the request runs.
                precondition = Storage.BlobWriteOption.doesNotExist();
            } else {
                // If the destination already exists in your bucket, instead set a generation-match
                // precondition. This will cause the request to fail if the existing object's generation
                // changes before the request runs.
                precondition =
                        Storage.BlobWriteOption.generationMatch(
                                storage.get(bucketName, fileName).getGeneration());
            }
            storage.createFrom(blobInfo, Paths.get(dataPath), precondition);

            System.out.println(
                    "File " + dataPath + " uploaded to bucket " + bucketName + " as " + fileName);

        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public void download(String dataPath, String fileName) {
        try {
            // The ID of your GCP project
            String projectId = "newsoftware-433920";

            // The ID of your GCS bucket
            String bucketName = "newsoftware-storage-bucket1";

            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

            Blob blob = storage.get(BlobId.of(bucketName, fileName));
            blob.downloadTo(Paths.get(dataPath));

            System.out.println(
                    "Downloaded object "
                            + fileName
                            + " from bucket name "
                            + bucketName
                            + " to "
                            + dataPath);

        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
}
