/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WISE
 */
@Entity
@Table(name = "tb_endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEndereco.findAll", query = "SELECT t FROM TbEndereco t"),
    @NamedQuery(name = "TbEndereco.findByCep", query = "SELECT t FROM TbEndereco t WHERE t.cep = :cep"),
    @NamedQuery(name = "TbEndereco.findByLogradouro", query = "SELECT t FROM TbEndereco t WHERE t.logradouro = :logradouro"),
    @NamedQuery(name = "TbEndereco.findByBairro", query = "SELECT t FROM TbEndereco t WHERE t.bairro = :bairro"),
    @NamedQuery(name = "TbEndereco.findByCidade", query = "SELECT t FROM TbEndereco t WHERE t.cidade = :cidade"),
    @NamedQuery(name = "TbEndereco.findByEstado", query = "SELECT t FROM TbEndereco t WHERE t.estado = :estado"),
    @NamedQuery(name = "TbEndereco.findByLatitude", query = "SELECT t FROM TbEndereco t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "TbEndereco.findByLongitude", query = "SELECT t FROM TbEndereco t WHERE t.longitude = :longitude"),
    @NamedQuery(name = "TbEndereco.findByAtivo", query = "SELECT t FROM TbEndereco t WHERE t.ativo = :ativo")})
public class TbEndereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 150)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado")
    private String estado;
    @Size(max = 200)
    @Column(name = "latitude")
    private String latitude;
    @Size(max = 200)
    @Column(name = "longitude")
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(mappedBy = "endereco")
    private List<TbEmpresa> tbEmpresaList;

    public TbEndereco() {
    }

    public TbEndereco(String cep) {
        this.cep = cep;
    }

    public TbEndereco(String cep, String logradouro, String cidade, String estado, boolean ativo) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.ativo = ativo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<TbEmpresa> getTbEmpresaList() {
        return tbEmpresaList;
    }

    public void setTbEmpresaList(List<TbEmpresa> tbEmpresaList) {
        this.tbEmpresaList = tbEmpresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cep != null ? cep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbEndereco)) {
            return false;
        }
        TbEndereco other = (TbEndereco) object;
        if ((this.cep == null && other.cep != null) || (this.cep != null && !this.cep.equals(other.cep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbEndereco[ cep=" + cep + " ]";
    }
    
}
