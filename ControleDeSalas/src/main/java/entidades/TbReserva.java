/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WISE
 */
@Entity
@Table(name = "tb_reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbReserva.findAll", query = "SELECT t FROM TbReserva t"),
    @NamedQuery(name = "TbReserva.findById", query = "SELECT t FROM TbReserva t WHERE t.id = :id"),
    @NamedQuery(name = "TbReserva.findByHorarioInicio", query = "SELECT t FROM TbReserva t WHERE t.horarioInicio = :horarioInicio"),
    @NamedQuery(name = "TbReserva.findByPrevisaoTermino", query = "SELECT t FROM TbReserva t WHERE t.previsaoTermino = :previsaoTermino"),
    @NamedQuery(name = "TbReserva.findByCriacao", query = "SELECT t FROM TbReserva t WHERE t.criacao = :criacao"),
    @NamedQuery(name = "TbReserva.findByAtivo", query = "SELECT t FROM TbReserva t WHERE t.ativo = :ativo"),
    @NamedQuery(name = "TbReserva.findByDescricao", query = "SELECT t FROM TbReserva t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TbReserva.findByTitulo", query = "SELECT t FROM TbReserva t WHERE t.titulo = :titulo")})
public class TbReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "horario_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioInicio;
    @Column(name = "previsao_termino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date previsaoTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date criacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "id_sala", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbSala idSala;
    @JoinColumn(name = "id_organizador", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private TbUsuario idOrganizador;
    
    private int chave_sala;
    private String chave_organizador;

    public TbReserva() {
    }

    public TbReserva(Integer id) {
        this.id = id;
    }

    public TbReserva(Integer id, Date criacao, boolean ativo, String descricao, String titulo) {
        this.id = id;
        this.criacao = criacao;
        this.ativo = ativo;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(Date previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TbSala getIdSala() {
        return idSala;
    }

    public void setIdSala(TbSala idSala) {
        this.idSala = idSala;
    }

    public TbUsuario getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(TbUsuario idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public int getChave_sala() {
        return chave_sala;
    }

    public void setChave_sala(int chave_sala) {
        this.chave_sala = chave_sala;
    }

    public String getChave_organizador() {
        return chave_organizador;
    }

    public void setChave_organizador(String chave_organizador) {
        this.chave_organizador = chave_organizador;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbReserva)) {
            return false;
        }
        TbReserva other = (TbReserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbReserva[ id=" + id + " ]";
    }
    
}
