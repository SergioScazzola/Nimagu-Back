package com.nimagu.back.Entidades;

public class PagoComp {

private Pago      cabpago;
private Detpago[] detpago;

public PagoComp(){}

public Pago getCabpago() {
    return cabpago;
}

public void setCabpago(Pago cabpago) {
    this.cabpago = cabpago;
}

public Detpago[] getDetpago() {
    return detpago;
}

public void setDetpago(Detpago[] detpago) {
    this.detpago = detpago;
}


}