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
    @NamedQuery(name = "TbReserva.findAll", query = "SELECT t FROM TbReserva t WHERE t.ativo=1"),
    @NamedQuery(name = "TbReserva.findById", query = "SELECT t FROM TbReserva t WHERE t.id = :id and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByHorarioInicio", query = "SELECT t FROM TbReserva t WHERE t.horarioInicio = :horarioInicio and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByPrevisaoTermino", query = "SELECT t FROM TbReserva t WHERE t.previsaoTermino = :previsaoTermino and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByCriacao", query = "SELECT t FROM TbReserva t WHERE t.criacao = :criacao and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByUltimaModificacao", query = "SELECT t FROM TbReserva t WHERE t.ultimaModificacao = :ultimaModificacao and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByAtivo", query = "SELECT t FROM TbReserva t WHERE t.ativo = :ativo"),
    @NamedQuery(name = "TbReserva.findByDescricao", query = "SELECT t FROM TbReserva t WHERE t.descricao = :descricao and t.ativo=1"),
    @NamedQuery(name = "TbReserva.findByTitulo", query = "SELECT t FROM TbReserva t WHERE t.titulo = :titulo and t.ativo=1")})
public class TbReserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva")
    private Integer idReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_organizador_email", referencedColumnName = "email")
    @ManyToOne
    private TbUsuario idOrganizadorEmail;

    private static final long serialVersionUID = 1L;
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
    @Column(name = "ultima_modificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacao;
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

    public TbReserva(Integer id, Date criacao, Date ultimaModificacao, boolean ativo, String descricao, String titulo) {
        this.id = id;
        this.criacao = criacao;
        this.ultimaModificacao = ultimaModificacao;
        this.ativo = ativo;
        this.descricao = descricao;
        this.titulo = titulo;
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

    public Date getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Date ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
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

    public TbReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public TbReserva(Integer idReserva, int id) {
        this.idReserva = idReserva;
        this.id = id;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TbUsuario getIdOrganizadorEmail() {
        return idOrganizadorEmail;
    }

    public void setIdOrganizadorEmail(TbUsuario idOrganizadorEmail) {
        this.idOrganizadorEmail = idOrganizadorEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbReserva)) {
            return false;
        }
        TbReserva other = (TbReserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbReserva[ idReserva=" + idReserva + " ]";
    }
    
}
