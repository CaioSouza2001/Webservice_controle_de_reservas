package services;

import database.EManager;
import entidades.TbEmpresa;
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
            TbUsuario user = EManager.getInstance().getDbAccessor().getUserByEmail(email);
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
                TbUsuario user = EManager.getInstance().getDbAccessor().getUserByEmail(email);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                //user.setTbEmpresaList(null);
                //user.setTbReservaList(null);
                if (user != null) {

                    user.setCnpjEmpresa(null);
                    user.setTbReservaList(null);
                    return user;
                } else {
                    return null;
                }
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
    public void detectarAcesso()
    {
        System.out.println("Acessado");
    }
    

    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String cadastrarUsuario(@HeaderParam("authorization") String authorization,
            @HeaderParam("novoUsuario") String novoUsuarioEncoded,
            @HeaderParam("cnpj") String cnpj) {
        if (authorization != null && authorization.equals("secret")) {
            try {
                String userDecoded = new String(Base64.getDecoder().decode(novoUsuarioEncoded.getBytes()), Charset.forName("UTF-8"));

                JSONObject userObj = new JSONObject(userDecoded);

                TbUsuario novoUsuario = new TbUsuario();
                String email, nome;
                String dominio = null;

                if (userObj.has("email") && userObj.has("nome")) {
                    email = userObj.getString("email");
                    nome = userObj.getString("nome");

                    if (email.isEmpty() || nome.isEmpty()) {
                        return "Erro ao criar conta, os dados enviados estão incompletos";
                    } else if (email.contains("@")) {
                        dominio = email.split("@")[1];
                    }
                } else {
                    return "Erro ao criar conta, os dados enviados estão incompletos";
                }

                if (EManager.getInstance().getDbAccessor().getUserByEmail(email) != null) {
                    return "O email informado já está cadastrado";
                }

                novoUsuario.setEmail(email);
                novoUsuario.setNome(nome);

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
