package cl.prueba.mantenedor_backend.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;
import java.util.UUID;

public class PruebaException extends RuntimeException {
    private static final long serialVersionUID = -7448287938918515070L;

    private Throwable rootCause;
    private long code;
    private String fullStackTrace;
    private String simpleStackTrace;

    public PruebaException() {
    }


    public PruebaException(String message) {
        super("[" + UUID.randomUUID().toString() + "] - " + message);
    }


    public PruebaException(String message, long errCode) {
        super("[" + UUID.randomUUID().toString() + "] - " + message + "[" + String.valueOf(errCode) + "]");
        setCode(errCode);
    }

    public PruebaException(String message, Throwable cause, long errCode) {
        super("[" + UUID.randomUUID().toString() + "] - " + message, cause);
        setCode(errCode);
        rootCause = ExceptionUtils.getRootCause(cause);
    }

    public PruebaException(Exception cause) {
        super(cause);
        rootCause = ExceptionUtils.getRootCause(cause);
    }


    public PruebaException(String message, Throwable cause) {
        super("[" + UUID.randomUUID().toString() + "] - " + message, cause);
        rootCause = ExceptionUtils.getRootCause(cause);
    }

    public long getCode() {
        return code;
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public String getFullStackTrace() {
        if (fullStackTrace == null) {
            fullStackTrace = ExceptionUtils.getStackTrace(this);
        }
        return fullStackTrace;
    }


    public String getSimpleStackTrace() {
        if (simpleStackTrace == null) {
            StringBuilder sb = new StringBuilder();
            List<Throwable> throwable = ExceptionUtils.getThrowableList(this);
            sb.append(ExceptionUtils.getMessage(throwable.remove(0)));
            for (Throwable thr : throwable) {
                sb.append(", caused by ");
                sb.append(ExceptionUtils.getMessage(thr));
            }
            simpleStackTrace = sb.toString();
        }
        return simpleStackTrace;
    }

    public static String printFullStackTrace(Throwable e) {
        return ExceptionUtils.getStackTrace(e);
    }


    public void setCode(long v) {
        this.code = v;
    }

    @Override
    public String toString() {
        String messageStr;

        messageStr = getMessage();
        if (messageStr == null) {
            messageStr = "";
        }
        if (getCode() > 0) {
            messageStr = messageStr + "[" + getCode() + "]";
        }
        if (getCause() != null) {
            if (getRootCause() != null) {
                messageStr = messageStr + ", Cause Exception: " + getCause() + ", Root Cause Exception: "
                        + getRootCause();
            } else {
                messageStr = messageStr + ", Cause and Root Exception: " + getCause();
            }
        }
        return messageStr;
    }
}
