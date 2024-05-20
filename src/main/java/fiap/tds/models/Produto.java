package fiap.tds.models;

public class Produto extends _BaseEntity {
    private String nm_prod;
    private int plano_prod;
    private String desc_prod;

    public Produto() {
    }

    public Produto(int id, String nm_prod, int plano_prod, String desc_prod) {
        super(id);
        this.nm_prod = nm_prod;
        this.plano_prod = plano_prod;
        this.desc_prod = desc_prod;
    }

    public String getNm_prod() {
        return nm_prod;
    }

    public void setNm_prod(String nm_prod) {
        this.nm_prod = nm_prod;
    }

    public int getPlano_prod() {
        return plano_prod;
    }

    public void setPlano_prod(int plano_prod) {
        this.plano_prod = plano_prod;
    }

    public String getDesc_prod() {
        return desc_prod;
    }

    public void setDesc_prod(String desc_prod) {
        this.desc_prod = desc_prod;
    }

    @Override
    public String toString() {
        return "Produto{" +
            "nm_prod='" + nm_prod + '\'' +
            ", plano_prod=" + plano_prod +
            ", desc_prod='" + desc_prod + '\'' +
            "} " + super.toString();
    }
}
