package controller;

/**
 * This class is used to throw an exception when a vehicle cannot be embarked.
 */
public class EmbarkException extends Exception{
    EmbarkException(String message){
        super(message);
    }
}
