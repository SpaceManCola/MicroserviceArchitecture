package inc.cognito.MicroserviceArchitecture.model.repository;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Client {
    private BigInteger id;
    private String name;

    public Client(BigDecimal id, String name) {
        this.id = id != null ? id.toBigInteger() : null;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
