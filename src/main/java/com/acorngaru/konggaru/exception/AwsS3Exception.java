package com.acorngaru.konggaru.exception;

public class AwsS3Exception extends Exception {

    public AwsS3Exception(String message) {
        super(message);
    }
    public AwsS3Exception(Exception exception) { super(exception); }
}
