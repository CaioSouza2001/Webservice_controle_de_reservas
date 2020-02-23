package entidades;

import entidades.TbEmpresa;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-23T18:17:41")
@StaticMetamodel(TbEndereco.class)
public class TbEndereco_ { 

    public static volatile SingularAttribute<TbEndereco, String> cidade;
    public static volatile SingularAttribute<TbEndereco, String> estado;
    public static volatile SingularAttribute<TbEndereco, Boolean> ativo;
    public static volatile ListAttribute<TbEndereco, TbEmpresa> tbEmpresaList;
    public static volatile SingularAttribute<TbEndereco, List> chave_empresas;
    public static volatile SingularAttribute<TbEndereco, String> logradouro;
    public static volatile SingularAttribute<TbEndereco, String> bairro;
    public static volatile SingularAttribute<TbEndereco, String> latitude;
    public static volatile SingularAttribute<TbEndereco, String> cep;
    public static volatile SingularAttribute<TbEndereco, String> longitude;

}