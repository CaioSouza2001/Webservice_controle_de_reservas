package services;

import database.DbAccessor;
import database.EManager;
import entidades.TbEmpresa;
import entidades.TbReserva;
import entidades.TbUsuario;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

@Path("usuario")
public class UsuarioService {

    @GET
    @Path("/JS{email}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbUsuario getUserJson(
            @PathParam("email") String email,
            @HeaderParam("authorization") String authorization) {
        if (authorization != null && authorization.equals("secret")) {
            TbUsuario user = DbAccessor.getUserByEmail(email);
            if (user != null) {
                //user.getIdOrganizacao().setUsuarioCollection(null);
                user.setTbReservaList(null);
                return user;
            }
        } else {
            return null;
        }
        return null;
    }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public TbUsuario authentication(
            @HeaderParam("email") String email,
            @HeaderParam("authorization") String authorization) {
        try {
            if (authorization != null && authorization.equals("secret")) {
                TbUsuario user = DbAccessor.getUserByEmail(email);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                //user.setTbEmpresaList(null);
                //user.setTbReservaList(null);
                if (user != null) {

                    user.setCnpjEmpresa(null);
                    user.setTbReservaList(null);
                } 
                    return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

    @GET
    @Path("KK")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void detectarAcesso() {
        System.out.println("Acessado");
    }

    @PUT
    @Path("atualizar")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String atualizarUsuario(@HeaderParam("authorization") String authorization,
            @HeaderParam("novoUsuario") String novoUsuarioEncoded) {
        if (authorization != null && authorization.equals("secret")) {
            try {
                String userDecoded = new String(Base64.getDecoder().decode(novoUsuarioEncoded.getBytes()), Charset.forName("UTF-8"));

                JSONObject userObj = new JSONObject(userDecoded);

                TbUsuario novoUsuario = new TbUsuario();
                String email, nome, senha;
                String dominio = null;
                String cnpj;

                if (userObj.has("email") && userObj.has("nome")) {
                    email = userObj.getString("email");
                    nome = userObj.getString("nome");
                    cnpj = userObj.getString("cnpj");
                    senha = userObj.getString("senha");

                    if (email.isEmpty() || nome.isEmpty()) {
                        return "Erro ao atualizar conta, os dados enviados estão incompletos";
                    } else if (email.contains("@")) {
                        dominio = email.split("@")[1];
                    }
                } else {
                    return "Erro ao atualizar conta, os dados enviados estão incompletos";
                }

                novoUsuario.setEmail(email);
                novoUsuario.setNome(nome);
                novoUsuario.setSenha(senha);

                TbEmpresa empresa = DbAccessor.getOrganizacaoById(cnpj);

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

                DbAccessor.atualizarUsuario(novoUsuario);

                return "Usuário atualizado com sucesso";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao atualizar usuário: " + e.getMessage();
            }

        } else {
            return "Token inválido";
        }
    }

    @PUT
    @Path("desativar")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String desativarUsuario(@HeaderParam("authorization") String authorization,
            @HeaderParam("id") String novoUsuarioEncoded) {
        if (authorization != null && authorization.equals("secret")) {
            try {
                ReservaService reservaService = new ReservaService();

                String userDecoded = new String(Base64.getDecoder().decode(novoUsuarioEncoded.getBytes()), Charset.forName("UTF-8"));

                String email = userDecoded;

                TbUsuario usuario = DbAccessor.getUserByEmail(email);

                TbEmpresa empresa = DbAccessor.getOrganizacaoById(usuario.getChave_empresa());
                usuario.setCnpjEmpresa(empresa);

                if (usuario.getListaChaveReservas() != null) {
                    for (int indice = 0; indice < usuario.getListaChaveReservas().size(); indice++) {
                        
                        reservaService.removerReserva(authorization, String.valueOf(usuario.getListaChaveReservas().get(indice)));
                   }
                }

                usuario.setAtivo(false);
                DbAccessor.atualizarUsuario(usuario);

                return "Usuário desativada com sucesso";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao atualizar usuário: " + e.getMessage();
            }

        } else {
            return "Token inválido";
        }
    }

    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String cadastrarUsuario(@HeaderParam("authorization") String authorization,
            @HeaderParam("novoUsuario") String novoUsuarioEncoded) {
        if (authorization != null && authorization.equals("secret")) {
            try {
                String userDecoded = new String(Base64.getDecoder().decode(novoUsuarioEncoded.getBytes()), Charset.forName("UTF-8"));

                JSONObject userObj = new JSONObject(userDecoded);

                TbUsuario novoUsuario = new TbUsuario();
                String email, nome, senha;
                String dominio = null;
                String cnpj;

                if (userObj.has("email") && userObj.has("nome")) {
                    email = userObj.getString("email");
                    nome = userObj.getString("nome");
                    cnpj = userObj.getString("cnpj");
                    senha = userObj.getString("senha");

                    if (email.isEmpty() || nome.isEmpty()) {
                        return "Erro ao criar conta, os dados enviados estão incompletos";
                    } else if (email.contains("@")) {
                        dominio = email.split("@")[1];
                    }
                } else {
                    return "Erro ao criar conta, os dados enviados estão incompletos";
                }

                if (DbAccessor.getUserByEmail(email) != null) {
                    return "O email informado já está cadastrado";
                }

                novoUsuario.setEmail(email);
                novoUsuario.setNome(nome);
                novoUsuario.setSenha(senha);

                TbEmpresa empresa = DbAccessor.getOrganizacaoById(cnpj);

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

                DbAccessor.novoUsuario(novoUsuario);

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
