package com.example.stuntingapps;

import java.util.List;

public class IbusResponse {

    private boolean error;
    private List<ObjectIbu> ibus;

    public IbusResponse(boolean error, List<ObjectIbu> ibus) {
        this.error = error;
        this.ibus = ibus;
    }

    public boolean isError() {
        return error;
    }

    public List<ObjectIbu> getIbus() {
        return ibus;
    }
}
