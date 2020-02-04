package services;

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
            TbEmpresa empresa = EManager.getInstance().getDbAccessor().getOrganizacaoById(cnpj);

            for (int indice = 0; indice < empresa.getTbSalaList().size(); indice++) {
                empresa.getTbSalaList().get(indice).setIdEmpresa(null);
                empresa.getTbSalaList().get(indice).setTbReservaList(null);
            }
            return empresa.getTbSalaList();
        } else {
            return null;
        }
    }
    
    @GET
    @Path("encontrarSalasEmpresaFuncionario")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbSala> encontrarEmpresaFuncionario(@HeaderParam("authorization") String authorization,
            @HeaderParam("ids") List<Integer> ids) {
     
        List<TbSala> salas = new ArrayList<>();
        
        for(int id : ids)
        {
            salas.add(EManager.getInstance().getDbAccessor().getSalaById(id));
        }
        
        for(TbSala sala : salas)
        {
            sala.setIdEmpresa(null);
            sala.setTbReservaList(null);
        }
        
        return  salas;
    }


}
