package services;

import database.DbAccessor;
import database.EManager;
import entidades.TbEmpresa;
import entidades.TbUsuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("organizacao")
public class OrganizacaoService {

    @GET
    @Path("organizacoesByDominio")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbEmpresa> getOrganizacoesByDominio(
            @HeaderParam("authorization") String authorization,
            @HeaderParam("dominio") String dominio) {
        if (authorization != null && authorization.equals("secret")) {
            List<TbEmpresa> lista = DbAccessor.getOrganizacoesByDominio(dominio);
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setTbEmpresaList(null);
                lista.get(i).setTbSalaList(null);
                lista.get(i).setTbUsuarioList(null);
                lista.get(i).setIdFilial(null);
                lista.get(i).setEndereco(null);
            }
            return lista;
        }
        return null;
    }

    @GET
    @Path("organizacaoByCnpj")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbEmpresa getOrganizacoesByCNPJ(
            @HeaderParam("authorization") String authorization,
            @HeaderParam("cnpj") String cnpj) {
        if (authorization != null && authorization.equals("secret")) {
           
                TbEmpresa empresa = DbAccessor.getOrganizacaoById(cnpj);
                empresa.setTbEmpresaList(null);
                empresa.setTbSalaList(null);
                empresa.setTbUsuarioList(null);
                empresa.setIdFilial(null);
                empresa.setEndereco(null);
                              
                return empresa;
        }
        return null;
    }

}
