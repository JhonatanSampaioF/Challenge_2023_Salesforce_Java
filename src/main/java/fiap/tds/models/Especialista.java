package fiap.tds.models;

public class Especialista extends _BaseEntity {
    private String nm_espec;
    private String email_espec;
    private String telefone_espec;

    public Especialista() {
    }

    public Especialista(int id, String nm_espec, String email_espec, String telefone_espec) {
        super(id);
        this.nm_espec = nm_espec;
        this.email_espec = email_espec;
        this.telefone_espec = telefone_espec;
    }

    public String getNm_espec() {
        return nm_espec;
    }

    public void setNm_espec(String nm_espec) {
        this.nm_espec = nm_espec;
    }

    public String getEmail_espec() {
        return email_espec;
    }

    public void setEmail_espec(String email_espec) {
        this.email_espec = email_espec;
    }

    public String getTelefone_espec() {
        return telefone_espec;
    }

    public void setTelefone_espec(String telefone_espec) {
        this.telefone_espec = telefone_espec;
    }

    @Override
    public String toString() {
        return "Especialista{" +
            "nm_espec='" + nm_espec + '\'' +
            ", email_espec='" + email_espec + '\'' +
            ", telefone_espec='" + telefone_espec + '\'' +
            "} " + super.toString();
    }
}
