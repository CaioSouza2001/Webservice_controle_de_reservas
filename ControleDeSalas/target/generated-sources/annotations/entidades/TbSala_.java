package entidades;

import entidades.TbEmpresa;
import entidades.TbReserva;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-20T16:12:36")
@StaticMetamodel(TbSala.class)
public class TbSala_ { 

    public static volatile SingularAttribute<TbSala, String> area;
    public static volatile SingularAttribute<TbSala, String> andar;
    public static volatile SingularAttribute<TbSala, Date> ultimaModificacao;
    public static volatile SingularAttribute<TbSala, String> estado;
    public static volatile SingularAttribute<TbSala, Boolean> ativo;
    public static volatile ListAttribute<TbSala, TbReserva> tbReservaList;
    public static volatile SingularAttribute<TbSala, String> chave_empresa;
    public static volatile SingularAttribute<TbSala, String> nome;
    public static volatile SingularAttribute<TbSala, Date> criacao;
    public static volatile SingularAttribute<TbSala, Integer> quantidadeArCondicionado;
    public static volatile SingularAttribute<TbSala, Integer> quantidadeProjetor;
    public static volatile SingularAttribute<TbSala, Integer> quantidadeAssentos;
    public static volatile SingularAttribute<TbSala, List> listaIdReservas;
    public static volatile SingularAttribute<TbSala, TbEmpresa> idEmpresa;
    public static volatile SingularAttribute<TbSala, Integer> id;
    public static volatile SingularAttribute<TbSala, Integer> capacidadeMaxima;

}