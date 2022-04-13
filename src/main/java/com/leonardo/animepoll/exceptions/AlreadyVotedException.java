package com.leonardo.animepoll.exceptions;

public class AlreadyVotedException extends RuntimeException{
    
    public AlreadyVotedException(String msg){
        super(msg);
    }

    public AlreadyVotedException(String msg, Throwable cause){
        super(msg, cause);
    }

}
