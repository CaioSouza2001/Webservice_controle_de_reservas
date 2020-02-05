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
import java.util.Locale;
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
    @Path("findReservaBySala")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbReserva> getReservaByIdSala(
            @HeaderParam("id") Integer id,
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
            
            
           TbSala sala = EManager.getInstance().getDbAccessor().getSalaById(id);
           List<TbReserva> reservas = new ArrayList<>();
                      
           for(int indice = 0; indice < sala.getListaIdReservas().size(); indice++)
           {
               reservas.add(EManager.getInstance().getDbAccessor().getReservaById(sala.getListaIdReservas().get(indice)));
           }
            return reservas;    
        } else {
            return null;
        }
    }
    
      
    @GET
    @Path("findReservaByEmail")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbReserva> getReservaByEmail(
            @HeaderParam("email") String email,
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
           
            List<TbReserva> reservas = new ArrayList<>();
            
            TbUsuario usuario = EManager.getInstance().getDbAccessor().getUserByEmail(email);
           
            for(int indice = 0; indice < usuario.getListaChaveReservas().size(); indice++)
            {
                reservas.add(EManager.getInstance().getDbAccessor().getReservaById(usuario.getListaChaveReservas().get(indice)));
            }
            return reservas;    
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

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                
                JSONObject reservaObj = new JSONObject(userDecoded);

                TbReserva reserva = new TbReserva();
                
                String titulo, descricao, email;
                int idSala;
                Date inicio, termino, criacao, ultimaModificacao;
         
                
                if (reservaObj.has("titulo") && reservaObj.has("descricao")) {
                    titulo = reservaObj.getString("titulo");
                    descricao = reservaObj.getString("descricao");
                    email = reservaObj.getString("email_organizador");
                    inicio = new Date((reservaObj.getLong("inicio")));
                    termino = new Date((reservaObj.getLong("termino")));
                    idSala = reservaObj.getInt("id_sala");
                    email= reservaObj.getString("email_organizador");


              
                    if (titulo.isEmpty() || descricao.isEmpty()) {
                        return "Erro ao criar reserva, os dados enviados estão incompletos";
                    } 
                } else {
                    return "Erro ao criar reserva, os dados enviados estão incompletos";
               
                }                
                reserva.setTitulo(titulo);
                reserva.setDescricao(descricao);
                reserva.setHorarioInicio(inicio);
                reserva.setPrevisaoTermino(termino);
                reserva.setAtivo(true);             
                reserva.setChave_sala(idSala);
                reserva.setChave_organizador(email);
                
                TbUsuario usuario = EManager.getInstance().getDbAccessor().getUserByEmail(email);
                TbSala sala = EManager.getInstance().getDbAccessor().getSalaById(idSala);
                
               reserva.setIdOrganizador(usuario);
               reserva.setIdSala(sala);
                

                //sdf.setTimeZone(TimeZone.getTimeZone("UTC3"));
                criacao = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(criacao);
                calendar.add(Calendar.HOUR_OF_DAY, -3);
                criacao = calendar.getTime();
                reserva.setCriacao(criacao);
                
                ultimaModificacao = new Date();
                Calendar calendar2 = Calendar.getInstance();
                calendar.setTime(ultimaModificacao);
                calendar.add(Calendar.HOUR_OF_DAY, -3);
                ultimaModificacao = calendar.getTime();
                reserva.setUltimaModificacao(ultimaModificacao);
                
                EManager.getInstance().getDbAccessor().novaReserva(reserva);

                return "Reserva criada com sucesso";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao criar reserva: " + e.getMessage();
            }

        } else {
            return "Token inválido";
        }
    }
    
}
