package org.simonscode.photoshare.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(){
        super("Not autherized!");
    }
}
