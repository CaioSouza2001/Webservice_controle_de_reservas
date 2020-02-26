package entidades;

import entidades.TbEmpresa;
import entidades.TbReserva;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-25T23:05:23")
@StaticMetamodel(TbUsuario.class)
public class TbUsuario_ { 

    public static volatile SingularAttribute<TbUsuario, List> listaChaveReservas;
    public static volatile SingularAttribute<TbUsuario, String> senha;
    public static volatile SingularAttribute<TbUsuario, Boolean> ativo;
    public static volatile SingularAttribute<TbUsuario, Date> ultimaModificacao;
    public static volatile ListAttribute<TbUsuario, TbReserva> tbReservaList;
    public static volatile SingularAttribute<TbUsuario, String> chave_empresa;
    public static volatile SingularAttribute<TbUsuario, String> nome;
    public static volatile SingularAttribute<TbUsuario, Date> criacao;
    public static volatile SingularAttribute<TbUsuario, String> email;
    public static volatile SingularAttribute<TbUsuario, TbEmpresa> cnpjEmpresa;

}