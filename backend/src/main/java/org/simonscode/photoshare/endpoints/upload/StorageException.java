package org.simonscode.photoshare.endpoints.upload;

@SuppressWarnings("WeakerAccess")
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
