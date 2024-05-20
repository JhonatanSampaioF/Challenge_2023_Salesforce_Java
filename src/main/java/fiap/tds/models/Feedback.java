package fiap.tds.models;

import java.sql.Date;

public class Feedback extends _BaseEntity {
    private int like_dislike_feedback;
    private String desc_feedback;
    private Date dt_feedback;
    private int fk_cliente_id_clie;

    public Feedback() {
    }

    public Feedback(int id, int like_dislike_feedback, String desc_feedback, Date dt_feedback, int fk_cliente_id_clie) {
        super(id);
        this.like_dislike_feedback = like_dislike_feedback;
        this.desc_feedback = desc_feedback;
        this.dt_feedback = dt_feedback;
        this.fk_cliente_id_clie = fk_cliente_id_clie;
    }

    public int getLike_dislike_feedback() {
        return like_dislike_feedback;
    }

    public void setLike_dislike_feedback(int like_dislike_feedback) {
        this.like_dislike_feedback = like_dislike_feedback;
    }

    public String getDesc_feedback() {
        return desc_feedback;
    }

    public void setDesc_feedback(String desc_feedback) {
        this.desc_feedback = desc_feedback;
    }

    public Date getDt_feedback() {
        return dt_feedback;
    }

    public void setDt_feedback(Date dt_feedback) {
        this.dt_feedback = dt_feedback;
    }

    public int getFk_cliente_id_clie() {
        return fk_cliente_id_clie;
    }

    public void setFk_cliente_id_clie(int fk_cliente_id_clie) {
        this.fk_cliente_id_clie = fk_cliente_id_clie;
    }

    @Override
    public String toString() {
        return "Feedback{" +
            "like_dislike_feedback=" + like_dislike_feedback +
            ", desc_feedback='" + desc_feedback + '\'' +
            ", dt_feedback=" + dt_feedback +
            ", fk_cliente_id_clie=" + fk_cliente_id_clie +
            "} " + super.toString();
    }
}
