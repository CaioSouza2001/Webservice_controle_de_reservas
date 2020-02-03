/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WISE
 */
@Entity
@Table(name = "tb_sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSala.findAll", query = "SELECT t FROM TbSala t"),
    @NamedQuery(name = "TbSala.findById", query = "SELECT t FROM TbSala t WHERE t.id = :id"),
    @NamedQuery(name = "TbSala.findByNome", query = "SELECT t FROM TbSala t WHERE t.nome = :nome"),
    @NamedQuery(name = "TbSala.findByCapacidadeMaxima", query = "SELECT t FROM TbSala t WHERE t.capacidadeMaxima = :capacidadeMaxima"),
    @NamedQuery(name = "TbSala.findByQuantidadeAssentos", query = "SELECT t FROM TbSala t WHERE t.quantidadeAssentos = :quantidadeAssentos"),
    @NamedQuery(name = "TbSala.findByAndar", query = "SELECT t FROM TbSala t WHERE t.andar = :andar"),
    @NamedQuery(name = "TbSala.findByQuantidadeArCondicionado", query = "SELECT t FROM TbSala t WHERE t.quantidadeArCondicionado = :quantidadeArCondicionado"),
    @NamedQuery(name = "TbSala.findByQuantidadeProjetor", query = "SELECT t FROM TbSala t WHERE t.quantidadeProjetor = :quantidadeProjetor"),
    @NamedQuery(name = "TbSala.findByArea", query = "SELECT t FROM TbSala t WHERE t.area = :area"),
    @NamedQuery(name = "TbSala.findByCriacao", query = "SELECT t FROM TbSala t WHERE t.criacao = :criacao"),
    @NamedQuery(name = "TbSala.findByUltimaModificacao", query = "SELECT t FROM TbSala t WHERE t.ultimaModificacao = :ultimaModificacao"),
    @NamedQuery(name = "TbSala.findByEstado", query = "SELECT t FROM TbSala t WHERE t.estado = :estado"),
    @NamedQuery(name = "TbSala.findByAtivo", query = "SELECT t FROM TbSala t WHERE t.ativo = :ativo")})
public class TbSala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidade_maxima")
    private int capacidadeMaxima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_assentos")
    private int quantidadeAssentos;
    @Size(max = 50)
    @Column(name = "andar")
    private String andar;
    @Column(name = "quantidade_ar_condicionado")
    private Integer quantidadeArCondicionado;
    @Column(name = "quantidade_projetor")
    private Integer quantidadeProjetor;
    @Size(max = 150)
    @Column(name = "area")
    private String area;
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
    @Size(max = 20)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSala")
    private List<TbReserva> tbReservaList;
    @JoinColumn(name = "id_empresa", referencedColumnName = "cnpj")
    @ManyToOne(optional = false)
    private TbEmpresa idEmpresa;

    public TbSala() {
    }

    public TbSala(Integer id) {
        this.id = id;
    }

    public TbSala(Integer id, String nome, int capacidadeMaxima, int quantidadeAssentos, Date criacao, Date ultimaModificacao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAssentos = quantidadeAssentos;
        this.criacao = criacao;
        this.ultimaModificacao = ultimaModificacao;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getQuantidadeAssentos() {
        return quantidadeAssentos;
    }

    public void setQuantidadeAssentos(int quantidadeAssentos) {
        this.quantidadeAssentos = quantidadeAssentos;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public Integer getQuantidadeArCondicionado() {
        return quantidadeArCondicionado;
    }

    public void setQuantidadeArCondicionado(Integer quantidadeArCondicionado) {
        this.quantidadeArCondicionado = quantidadeArCondicionado;
    }

    public Integer getQuantidadeProjetor() {
        return quantidadeProjetor;
    }

    public void setQuantidadeProjetor(Integer quantidadeProjetor) {
        this.quantidadeProjetor = quantidadeProjetor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<TbReserva> getTbReservaList() {
        return tbReservaList;
    }

    public void setTbReservaList(List<TbReserva> tbReservaList) {
        this.tbReservaList = tbReservaList;
    }

    public TbEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(TbEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        if (!(object instanceof TbSala)) {
            return false;
        }
        TbSala other = (TbSala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbSala[ id=" + id + " ]";
    }
    
}
