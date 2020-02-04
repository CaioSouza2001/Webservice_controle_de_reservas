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
@Table(name = "tb_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEmpresa.findAll", query = "SELECT t FROM TbEmpresa t"),
    @NamedQuery(name = "TbEmpresa.findByCnpj", query = "SELECT t FROM TbEmpresa t WHERE t.cnpj = :cnpj"),
    @NamedQuery(name = "TbEmpresa.findByNome", query = "SELECT t FROM TbEmpresa t WHERE t.nome = :nome"),
    @NamedQuery(name = "TbEmpresa.findByTelefone", query = "SELECT t FROM TbEmpresa t WHERE t.telefone = :telefone"),
    @NamedQuery(name = "TbEmpresa.findByTipo", query = "SELECT t FROM TbEmpresa t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TbEmpresa.findByEmail", query = "SELECT t FROM TbEmpresa t WHERE t.email = :email"),
    @NamedQuery(name = "TbEmpresa.findByDominio", query = "SELECT t FROM TbEmpresa t WHERE t.dominio = :dominio"),
    @NamedQuery(name = "TbEmpresa.findByCriacao", query = "SELECT t FROM TbEmpresa t WHERE t.criacao = :criacao"),
    @NamedQuery(name = "TbEmpresa.findByUltimaModificacao", query = "SELECT t FROM TbEmpresa t WHERE t.ultimaModificacao = :ultimaModificacao"),
    @NamedQuery(name = "TbEmpresa.findByHorarioAbertura", query = "SELECT t FROM TbEmpresa t WHERE t.horarioAbertura = :horarioAbertura"),
    @NamedQuery(name = "TbEmpresa.findByHorarioEncerramento", query = "SELECT t FROM TbEmpresa t WHERE t.horarioEncerramento = :horarioEncerramento"),
    @NamedQuery(name = "TbEmpresa.findByAtivo", query = "SELECT t FROM TbEmpresa t WHERE t.ativo = :ativo")})
public class TbEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Size(max = 25)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dominio")
    private String dominio;
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
    @Column(name = "horario_abertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioAbertura;
    @Column(name = "horario_encerramento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioEncerramento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cnpjEmpresa")
    private List<TbUsuario> tbUsuarioList;
    @OneToMany(mappedBy = "idFilial")
    private List<TbEmpresa> tbEmpresaList;
    @JoinColumn(name = "id_filial", referencedColumnName = "cnpj")
    @ManyToOne
    private TbEmpresa idFilial;
    @JoinColumn(name = "endereco", referencedColumnName = "cep")
    @ManyToOne
    private TbEndereco endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<TbSala> tbSalaList;
    
    private List<String> chave_usuarios;
    private List<String> chave_filiais;
    private List<Integer> chave_salas;
    private String chave_endereco;

    public TbEmpresa() {
    }

    public TbEmpresa(String cnpj) {
        this.cnpj = cnpj;
    }

    public TbEmpresa(String cnpj, String nome, String tipo, String email, String dominio, Date criacao, Date ultimaModificacao, boolean ativo) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.dominio = dominio;
        this.criacao = criacao;
        this.ultimaModificacao = ultimaModificacao;
        this.ativo = ativo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
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

    public Date getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(Date horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public Date getHorarioEncerramento() {
        return horarioEncerramento;
    }

    public void setHorarioEncerramento(Date horarioEncerramento) {
        this.horarioEncerramento = horarioEncerramento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<String> getChave_usuarios() {
        return chave_usuarios;
    }

    public void setChave_usuarios(List<String> chave_usuarios) {
        this.chave_usuarios = chave_usuarios;
    }

    public List<String> getChave_filiais() {
        return chave_filiais;
    }

    public void setChave_filiais(List<String> chave_filiais) {
        this.chave_filiais = chave_filiais;
    }

    public List<Integer> getChave_salas() {
        return chave_salas;
    }

    public void setChave_salas(List<Integer> chave_salas) {
        this.chave_salas = chave_salas;
    }

    public String getChave_endereco() {
        return chave_endereco;
    }

    public void setChave_endereco(String chave_endereco) {
        this.chave_endereco = chave_endereco;
    }
    
    
    
    

    @XmlTransient
    public List<TbUsuario> getTbUsuarioList() {
        return tbUsuarioList;
    }

    public void setTbUsuarioList(List<TbUsuario> tbUsuarioList) {
        this.tbUsuarioList = tbUsuarioList;
    }

    @XmlTransient
    public List<TbEmpresa> getTbEmpresaList() {
        return tbEmpresaList;
    }

    public void setTbEmpresaList(List<TbEmpresa> tbEmpresaList) {
        this.tbEmpresaList = tbEmpresaList;
    }

    public TbEmpresa getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(TbEmpresa idFilial) {
        this.idFilial = idFilial;
    }

    public TbEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(TbEndereco endereco) {
        this.endereco = endereco;
    }

    @XmlTransient
    public List<TbSala> getTbSalaList() {
        return tbSalaList;
    }

    public void setTbSalaList(List<TbSala> tbSalaList) {
        this.tbSalaList = tbSalaList;
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnpj != null ? cnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbEmpresa)) {
            return false;
        }
        TbEmpresa other = (TbEmpresa) object;
        if ((this.cnpj == null && other.cnpj != null) || (this.cnpj != null && !this.cnpj.equals(other.cnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TbEmpresa[ cnpj=" + cnpj + " ]";
    }
    
}
