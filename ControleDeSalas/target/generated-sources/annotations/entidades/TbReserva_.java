package entidades;

import entidades.TbSala;
import entidades.TbUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-17T16:32:53")
@StaticMetamodel(TbReserva.class)
public class TbReserva_ { 

    public static volatile SingularAttribute<TbReserva, Date> horarioInicio;
    public static volatile SingularAttribute<TbReserva, Date> ultimaModificacao;
    public static volatile SingularAttribute<TbReserva, Boolean> ativo;
    public static volatile SingularAttribute<TbReserva, String> chave_organizador;
    public static volatile SingularAttribute<TbReserva, Date> previsaoTermino;
    public static volatile SingularAttribute<TbReserva, Integer> chave_sala;
    public static volatile SingularAttribute<TbReserva, String> titulo;
    public static volatile SingularAttribute<TbReserva, TbSala> idSala;
    public static volatile SingularAttribute<TbReserva, Integer> id;
    public static volatile SingularAttribute<TbReserva, Date> criacao;
    public static volatile SingularAttribute<TbReserva, TbUsuario> idOrganizador;
    public static volatile SingularAttribute<TbReserva, String> descricao;

}