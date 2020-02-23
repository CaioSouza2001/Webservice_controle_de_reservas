package entidades;

import entidades.TbEmpresa;
import entidades.TbEndereco;
import entidades.TbSala;
import entidades.TbUsuario;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-23T18:17:41")
@StaticMetamodel(TbEmpresa.class)
public class TbEmpresa_ { 

    public static volatile SingularAttribute<TbEmpresa, String> telefone;
    public static volatile SingularAttribute<TbEmpresa, String> tipo;
    public static volatile SingularAttribute<TbEmpresa, Date> ultimaModificacao;
    public static volatile SingularAttribute<TbEmpresa, Boolean> ativo;
    public static volatile SingularAttribute<TbEmpresa, TbEndereco> endereco;
    public static volatile SingularAttribute<TbEmpresa, List> chave_filiais;
    public static volatile SingularAttribute<TbEmpresa, String> dominio;
    public static volatile ListAttribute<TbEmpresa, TbUsuario> tbUsuarioList;
    public static volatile SingularAttribute<TbEmpresa, String> nome;
    public static volatile SingularAttribute<TbEmpresa, String> cnpj;
    public static volatile SingularAttribute<TbEmpresa, Date> criacao;
    public static volatile SingularAttribute<TbEmpresa, List> chave_salas;
    public static volatile ListAttribute<TbEmpresa, TbSala> tbSalaList;
    public static volatile SingularAttribute<TbEmpresa, Date> horarioEncerramento;
    public static volatile ListAttribute<TbEmpresa, TbEmpresa> tbEmpresaList;
    public static volatile SingularAttribute<TbEmpresa, List> chave_usuarios;
    public static volatile SingularAttribute<TbEmpresa, String> chave_endereco;
    public static volatile SingularAttribute<TbEmpresa, String> email;
    public static volatile SingularAttribute<TbEmpresa, Date> horarioAbertura;
    public static volatile SingularAttribute<TbEmpresa, TbEmpresa> idFilial;

}