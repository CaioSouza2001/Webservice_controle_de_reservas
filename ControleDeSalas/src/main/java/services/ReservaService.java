/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import database.EManager;
import entidades.TbEmpresa;
import entidades.TbReserva;
import entidades.TbSala;
import entidades.TbUsuario;
import java.lang.reflect.Array;
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

            for (int indice = 0; indice < sala.getListaIdReservas().size(); indice++) {
                reservas.add(EManager.getInstance().getDbAccessor().getReservaById(sala.getListaIdReservas().get(indice)));
            }
            return reservas;
        } else {
            return null;
        }
    }

    @GET
    @Path("findReservaBySalaWithMonth")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TbReserva> getReservaByIdSalaWithMonth(
            @HeaderParam("id") Integer id,
            @HeaderParam("data") String data, 
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
            
            Date filtro = new Date(Long.parseLong(data));
            System.out.println("Filtro: "+ filtro.toString());

            TbSala sala = EManager.getInstance().getDbAccessor().getSalaById(id);
            List<TbReserva> reservas = new ArrayList<>();

            for (int indice = 0; indice < sala.getListaIdReservas().size(); indice++) {
                TbReserva reserva = EManager.getInstance().getDbAccessor().getReservaById(sala.getListaIdReservas().get(indice));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(reserva.getHorarioInicio());
                Calendar limite = Calendar.getInstance();
                limite.setTime(filtro);

                if (calendar.get(Calendar.MONTH) == limite.get(Calendar.MONTH) && calendar.get(Calendar.YEAR) == limite.get(Calendar.YEAR)) {
                    reservas.add(reserva);
                }
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

            for (int indice = 0; indice < usuario.getListaChaveReservas().size(); indice++) {
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
                    inicio = new Date((reservaObj.getLong("inicio")));
                    termino = new Date((reservaObj.getLong("termino")));
                    idSala = reservaObj.getInt("id_sala");
                    email = reservaObj.getString("email_organizador");

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
                TbEmpresa empresa = EManager.getInstance().getDbAccessor().getOrganizacaoById(usuario.getChave_empresa());

                if (usuario.getTbReservaList() == null) {
                    List<TbReserva> minhasReservas = new ArrayList<>();
                    usuario.setTbReservaList(minhasReservas);
                }
                usuario.getTbReservaList().add(reserva);

                if (sala.getTbReservaList() == null) {
                    List<TbReserva> reservas = new ArrayList<>();
                    sala.setTbReservaList(reservas);
                }
                sala.getTbReservaList().add(reserva);

                List<TbReserva> reservas = new ArrayList<>();
                for (int id : sala.getListaIdReservas()) {
                    reservas.add(EManager.getInstance().getDbAccessor().getReservaById(id));
                }

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

                boolean autorizacaoCadastroReserva = true;

                boolean verificarHorariosEmpresa = false;
                boolean horarioPermitido = true;

                if (empresa.getHorarioAbertura() != null || empresa.getHorarioEncerramento() != null) {
                    verificarHorariosEmpresa = true;
                }

                if (verificarHorariosEmpresa) {
                    horarioPermitido = compararIntervaloDeTempo(empresa.getHorarioAbertura(),
                            reserva.getHorarioInicio(), empresa.getHorarioEncerramento());
                    System.out.println("Horario permitido:" + horarioPermitido);

                    if (horarioPermitido) {
                        horarioPermitido = compararIntervaloDeTempo(empresa.getHorarioAbertura(),
                                reserva.getPrevisaoTermino(), empresa.getHorarioEncerramento());

                    }
                }
                if (horarioPermitido) {
                    if (reserva.getHorarioInicio().before(reserva.getPrevisaoTermino())) {
                        if (verificarDisponibilidade(reserva.getHorarioInicio(), reserva.getPrevisaoTermino(), reservas)) {
                            EManager.getInstance().getDbAccessor().novaReserva(reserva);
                            return "Reserva criada com sucesso";
                        } else {
                            return "Já há reservas para este horário";
                        }
                    } else {
                        return "Período selecionado inválido!";
                    }
                } else {
                    return "A empresa nao se encontra aberta neste horario!";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao criar reserva: " + e.getMessage();
            }

        } else {
            return "Token inválido";
        }
    }

    public boolean compararIntervaloDeTempo(Date inicio, Date analise, Date fim) {

        Calendar calendarInicio = Calendar.getInstance();
        Calendar calendarAnalise = Calendar.getInstance();
        Calendar calendarFim = Calendar.getInstance();

        calendarInicio.setTime(inicio);
        calendarAnalise.setTime(analise);
        calendarFim.setTime(fim);

        int horaInicio = calendarInicio.get(Calendar.HOUR_OF_DAY);
        int minutoInicio = calendarInicio.get(Calendar.MINUTE);

        int horaAnalise = calendarAnalise.get(Calendar.HOUR_OF_DAY);
        int minutoAnalise = calendarAnalise.get(Calendar.MINUTE);

        int horaFim = calendarFim.get(Calendar.HOUR_OF_DAY);
        int minutoFim = calendarFim.get(Calendar.MINUTE);

        if (horaAnalise >= horaInicio && horaAnalise <= horaFim) {
            if (horaAnalise == horaInicio) {
                if (minutoAnalise >= minutoInicio) {
                    return true;
                }
                return false;

            } else if (horaAnalise == horaFim) {
                if (minutoAnalise <= minutoFim) {
                    return true;
                }
                return false;
            }
            return true;
        }

        return false;
    }

    private boolean verificarDisponibilidade(Date inicio, Date fim, List<TbReserva> reservas) {
        for (TbReserva reserva : reservas) {
            if (!(inicio.before(reserva.getHorarioInicio()) && fim.before(reserva.getHorarioInicio())) && !(inicio.after(reserva.getPrevisaoTermino()) && fim.after(reserva.getPrevisaoTermino()))) {
                return false;
            }
        }
        return true;
    }

}
