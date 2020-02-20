package services;

import database.DbAccessor;
import database.EManager;
import entidades.TbEmpresa;
import entidades.TbSala;
import entidades.TbUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("sala")
public class SalaService {

    @GET
    @Path("findSalasByCNPJ")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbSala> getSalasByEmpresa(
            @HeaderParam("cnpj") String cnpj,
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
            TbEmpresa empresa = DbAccessor.getOrganizacaoById(cnpj);
            List<TbSala> salas = new ArrayList<>();
            
            System.out.println(empresa.getChave_salas().size());
            for (int indice = 0; indice < empresa.getChave_salas().size(); indice++) {
                salas.add(DbAccessor.getSalaById(empresa.getChave_salas().get(indice)));
            }
            return salas;
        } else {
            return null;
        }
    }

    @GET
    @Path("encontrarSalasById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbSala encontrarEmpresaFuncionario(@HeaderParam("authorization") String authorization,
            @HeaderParam("id") Integer id) {


        TbSala sala = DbAccessor.getSalaById(id);
       
            sala.setIdEmpresa(null);
            sala.setTbReservaList(null);
       
        return sala;
    }

}
