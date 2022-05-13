package com.mycompany.listademails;

/**
 *
 * @author AlanB
 */
public class Mail {

    private String mail;
    private String flag;
    private String fecha;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return mail + "   Flags: " + flag + "   Fecha de Recepción: " + fecha + "\n";
    }
}
