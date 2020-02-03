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
@Table(name = "tb_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUsuario.findAll", query = "SELECT t FROM TbUsuario t"),
    @NamedQuery(name = "TbUsuario.findByEmail", query = "SELECT t FROM TbUsuario t WHERE t.email = :email"),
    @NamedQuery(name = "TbUsuario.findByNome", query = "SELECT t FROM TbUsuario t WHERE t.nome = :nome"),
    @NamedQuery(name = "TbUsuario.findByAtivo", query = "SELECT t FROM TbUsuario t WHERE t.ativo = :ativo"),
    @NamedQuery(name = "TbUsuario.findByCriacao", query = "SELECT t FROM TbUsuario t WHERE t.criacao = :criacao"),
    @NamedQuery(name = "TbUsuario.findByUltimaModificacao", query = "SELECT t FROM TbUsuario t WHERE t.ultimaModificacao = :ultimaModificacao")})
public class TbUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
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
    @JoinColumn(name = "cnpj_empresa", referencedColumnName = "cnpj")
    @ManyToOne(optional = false)
    private TbEmpresa cnpjEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganizador")
    private List<TbReserva> tbReservaList;

    public TbUsuario() {
    }

    public TbUsuario(String email) {
        this.email = email;
    }

    public TbUsuario(String email, String nome, boolean ativo, Date criacao, Date ultimaModificacao) {
        this.email = email;
        this.nome = nome;
        this.ativo = ativo;
        this.criacao = criacao;
        this.ultimaModificacao = ultimaModificacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public TbEmpresa getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(TbEmpresa cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    @XmlTransient
    public List<TbReserva> getTbReservaList() {
        return tbReservaList;
    }

    public void setTbReservaList(List<TbReserva> tbReservaList) {
        this.tbReservaList = tbReservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUsuario)) {
            return false;
        }
        TbUsuario other = (TbUsuario) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbUsuario[ email=" + email + " ]";
    }
    
}
