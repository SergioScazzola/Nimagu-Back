package com.nimagu.back.Entidades;

public class CobroComp {
    private Cobranza   cabcob;
    private Detcobro[] detcob;

    public CobroComp(){}

    public Cobranza getCabcob() {
        return cabcob;
    }

    public void setCabcob(Cobranza cabcob) {
        this.cabcob = cabcob;
    }

    public Detcobro[] getDetcob() {
        return detcob;
    }

    public void setDetcob(Detcobro[] detcob) {
        this.detcob = detcob;
    }

    
}
