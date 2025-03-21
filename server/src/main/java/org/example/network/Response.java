package org.example.network;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = 20L;

    private String result ="Успешно";


    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public Response(String result) {
        this.result = result;
    }

    public Response() {}
}
