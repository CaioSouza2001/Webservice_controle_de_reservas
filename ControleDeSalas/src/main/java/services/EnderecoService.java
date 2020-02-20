/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.DbAccessor;
import database.EManager;
import entidades.TbEmpresa;
import entidades.TbEndereco;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author WISE
 */

@Path("endereco")
public class EnderecoService {
    

@GET
    @Path("enderecoByCep")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbEndereco getEnderecoByCep(
            @HeaderParam("authorization") String authorization,
            @HeaderParam("cep") String cep) {

          TbEndereco endereco = DbAccessor.getEnderecoByCEP(cep);
          endereco.setTbEmpresaList(null);
          return endereco;
    }


}
