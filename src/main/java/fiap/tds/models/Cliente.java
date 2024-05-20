package fiap.tds.models;

public class Cliente extends _BaseEntity {
    private String nm_clie;
    private String sobrenome;
    private String email;
    private String empresa;
    private int tamanho_empresa;
    private String pais;
    private String cargo;
    private String telefone;

    public Cliente() {
    }

    public Cliente(int id, String nm_clie, String sobrenome, String email, String empresa, int tamanho_empresa, String pais, String cargo, String telefone) {
        super(id);
        this.nm_clie = nm_clie;
        this.sobrenome = sobrenome;
        this.email = email;
        this.empresa = empresa;
        this.tamanho_empresa = tamanho_empresa;
        this.pais = pais;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public String getNm_clie() {
        return nm_clie;
    }

    public void setNm_clie(String nm_clie) {
        this.nm_clie = nm_clie;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getTamanho_empresa() {
        return tamanho_empresa;
    }

    public void setTamanho_empresa(int tamanho_empresa) {
        this.tamanho_empresa = tamanho_empresa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "nm_clie='" + nm_clie + '\'' +
            ", sobrenome='" + sobrenome + '\'' +
            ", email='" + email + '\'' +
            ", empresa='" + empresa + '\'' +
            ", tamanho_empresa=" + tamanho_empresa +
            ", pais='" + pais + '\'' +
            ", cargo='" + cargo + '\'' +
            ", telefone='" + telefone + '\'' +
            "} " + super.toString();
    }
}
