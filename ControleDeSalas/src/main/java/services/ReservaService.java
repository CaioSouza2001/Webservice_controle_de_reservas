/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.EManager;
import entidades.TbEmpresa;
import entidades.TbReserva;
import entidades.TbSala;
import entidades.TbUsuario;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author WISE
 */

@Path("reserva")
public class ReservaService {
    
    @GET
    @Path("findReservaById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbReserva getReservaById(
            @HeaderParam("id") Integer id,
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
            
            TbReserva reserva = EManager.getInstance().getDbAccessor().getReservaById(id);
            
            reserva.setIdOrganizador(null);
            reserva.setIdSala(null);
            
            return reserva;    
        } else {
            return null;
        }
    }
    
    @POST
    @Path("cadastrarReserva")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String cadastrarReserva(@HeaderParam("authorization") String authorization,
            @HeaderParam("novaReserva") String novoUsuarioEncoded) {
        if (authorization != null && authorization.equals("secret")) {
            try {
                String userDecoded = new String(Base64.getDecoder().decode(novoUsuarioEncoded.getBytes()), Charset.forName("UTF-8"));

                JSONObject reservaObj = new JSONObject(userDecoded);

                TbReserva reserva = new TbReserva();
                
                String titulo, descricao, email;

                if (reservaObj.has("titulo") && reservaObj.has("descricao")) {
                    titulo = reservaObj.getString("titulo");
                    descricao = reservaObj.getString("descricao");
                    email = reservaObj.getString("email");
                    
                    

                    if (titulo.isEmpty() || descricao.isEmpty()) {
                        return "Erro ao criar reserva, os dados enviados estão incompletos";
                    } 
                } else {
                    return "Erro ao criar reserva, os dados enviados estão incompletos";
               
                }
/*
                TbSala sala = EManager.getInstance().getDbAccessor().getSalaById(idSala);
                
                reserva.setTitulo(titulo);
                reserva.setDescricao(descricao);
                reserva.setAtivo(true);
                reserva.setChave_organizador(authorization);
                
                TbEmpresa empresa = EManager.getInstance().getDbAccessor().getOrganizacaoById(cnpj);

                if (empresa.getTbUsuarioList() == null) {
                    List<TbUsuario> funcionarios = new ArrayList();
                    empresa.setTbUsuarioList(funcionarios);
                }
                empresa.getTbUsuarioList().add(novoUsuario);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                //sdf.setTimeZone(TimeZone.getTimeZone("UTC3"));
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, -3);
                date = calendar.getTime();

                novoUsuario.setCriacao(date);
                novoUsuario.setUltimaModificacao(date);
                novoUsuario.setCnpjEmpresa(empresa);
                novoUsuario.setAtivo(true);

                EManager.getInstance().getDbAccessor().novoUsuario(novoUsuario);
*/
                return "Usuário criado com sucesso";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao criar usuário: " + e.getMessage();
            }

        } else {
            return "Token inválido";
        }
    }
    
}
