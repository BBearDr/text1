package com.test;

/**
 * chenjx
 */
public class ListDemoException extends Exception {


    private static final long serialVersionUID = 4256748151776132093L;

    public ListDemoException() {
        super();
    }

    public ListDemoException(String message) {
        super(message);
        try {
            System.out.println(message+"成功了");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(message+"出错了");
        }
    }

    public ListDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListDemoException(Throwable cause) {
        super(cause);
    }

    protected ListDemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
    }
}
