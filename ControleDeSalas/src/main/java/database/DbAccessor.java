package database;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entidades.TbEmpresa;
import entidades.TbEndereco;
import entidades.TbReserva;
import entidades.TbSala;
import entidades.TbUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class DbAccessor {

   public DbAccessor(){}
           

    public static List<TbUsuario> getAllUsuarios() {
        try {
            List<TbUsuario> usuarios = EManager.getInstance().createNamedQuery("TbUsuario.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

            for (TbUsuario usuario : usuarios) {
                if (usuario.getTbReservaList() != null) {
                    if (usuario.getListaChaveReservas() == null) {
                        List<Integer> chave_reservas = new ArrayList<>();
                        usuario.setListaChaveReservas(chave_reservas);
                    }

                    for (TbReserva reserva : usuario.getTbReservaList()) {
                        usuario.getListaChaveReservas().add(reserva.getId());
                    }

                }

                usuario.setChave_empresa(usuario.getCnpjEmpresa().getCnpj());
                usuario.setCnpjEmpresa(null);
                usuario.setTbReservaList(null);
            }

            clear();
            // this.manager.close();
            return usuarios;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbUsuario getUserByEmail(String email) {
        try {
            TbUsuario usuario = (TbUsuario) EManager.getInstance().createNamedQuery("TbUsuario.findByEmail").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("email", email).getSingleResult();

            if (usuario.getTbReservaList() != null) {

                if (usuario.getListaChaveReservas() == null) {
                    List<Integer> chave_reservas = new ArrayList<>();
                    usuario.setListaChaveReservas(chave_reservas);
                }

                for (TbReserva reserva : usuario.getTbReservaList()) {
                    usuario.getListaChaveReservas().add(reserva.getId());
                }
            }

            usuario.setChave_empresa(usuario.getCnpjEmpresa().getCnpj());
            usuario.setTbReservaList(null);
            usuario.setCnpjEmpresa(null);

            clear();
            //this.manager.close();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbReserva getReservaById(int id) {
        try {
            TbReserva reserva = (TbReserva) EManager.getInstance().createNamedQuery("TbReserva.findById").setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .setParameter("id", id).getSingleResult();

            reserva.setChave_organizador(reserva.getIdOrganizador().getEmail());
            reserva.setChave_sala(reserva.getIdSala().getId());

            reserva.setIdOrganizador(null);
            reserva.setIdSala(null);

            clear();
            return reserva;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<TbEmpresa> getAllOrganizacoes() {
        try {
            List<TbEmpresa> empresas = EManager.getInstance().createNamedQuery("TbEmpresa.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

            for (TbEmpresa empresa : empresas) {
                empresa.setChave_endereco(empresa.getEndereco().getCep());

                if (empresa.getIdFilial() != null) {
                    if (empresa.getChave_filiais() == null) {
                        List<String> chaves = new ArrayList<>();
                        empresa.setChave_filiais(chaves);
                    }

                    for (TbEmpresa filial : empresa.getTbEmpresaList()) {
                        empresa.getChave_filiais().add(filial.getCnpj());
                    }
                }

                if (empresa.getTbSalaList() != null) {
                    if (empresa.getChave_salas() == null) {
                        List<Integer> chaves = new ArrayList<>();
                        empresa.setChave_salas(chaves);
                    }

                    for (TbSala sala : empresa.getTbSalaList()) {
                        empresa.getChave_salas().add(sala.getId());
                    }
                }

                if (empresa.getTbUsuarioList() != null) {
                    if (empresa.getChave_usuarios() == null) {
                        List<String> id_funcionarios = new ArrayList<>();
                        empresa.setChave_usuarios(id_funcionarios);
                    }

                    for (TbUsuario usuario : empresa.getTbUsuarioList()) {
                        empresa.getChave_usuarios().add(usuario.getEmail());
                    }
                }

                empresa.setEndereco(null);
                empresa.setIdFilial(null);
                empresa.setTbEmpresaList(null);
                empresa.setTbSalaList(null);
                empresa.setTbUsuarioList(null);
            }

            clear();
            //  this.manager.close();
            return empresas;

        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbEmpresa getOrganizacaoById(String id) {
        try {
            TbEmpresa empresa = (TbEmpresa) EManager.getInstance().createNamedQuery("TbEmpresa.findByCnpj").setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .setParameter("cnpj", id).getSingleResult();

            empresa.setChave_endereco(empresa.getEndereco().getCep());

            if (empresa.getIdFilial() != null) {
                if (empresa.getChave_filiais() == null) {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }

                for (TbEmpresa filial : empresa.getTbEmpresaList()) {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }

            if (empresa.getTbSalaList() != null) {
                if (empresa.getChave_salas() == null) {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }

                for (TbSala sala : empresa.getTbSalaList()) {
                    empresa.getChave_salas().add(sala.getId());
                }
            }

            if (empresa.getTbUsuarioList() != null) {
                if (empresa.getChave_usuarios() == null) {
                    List<String> id_funcionarios = new ArrayList<>();
                    empresa.setChave_usuarios(id_funcionarios);
                }

                for (TbUsuario usuario : empresa.getTbUsuarioList()) {
                    empresa.getChave_usuarios().add(usuario.getEmail());
                }
            }

            empresa.setEndereco(null);
            empresa.setIdFilial(null);
            empresa.setTbEmpresaList(null);
            empresa.setTbSalaList(null);
            empresa.setTbUsuarioList(null);

            clear();
            //  this.manager.close();
            return empresa;

        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbEmpresa getOrganizacaoByDominio(String dominio) {
        try {
            TbEmpresa empresa = (TbEmpresa) EManager.getInstance().createNamedQuery("TbEmpresa.findByDominio").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("dominio", dominio).getSingleResult();

            empresa.setChave_endereco(empresa.getEndereco().getCep());

            if (empresa.getIdFilial() != null) {
                if (empresa.getChave_filiais() == null) {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }

                for (TbEmpresa filial : empresa.getTbEmpresaList()) {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }

            if (empresa.getTbSalaList() != null) {
                if (empresa.getChave_salas() == null) {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }

                for (TbSala sala : empresa.getTbSalaList()) {
                    empresa.getChave_salas().add(sala.getId());
                }
            }

            if (empresa.getTbUsuarioList() != null) {
                if (empresa.getChave_usuarios() == null) {
                    List<String> id_funcionarios = new ArrayList<>();
                    empresa.setChave_usuarios(id_funcionarios);
                }

                for (TbUsuario usuario : empresa.getTbUsuarioList()) {
                    empresa.getChave_usuarios().add(usuario.getEmail());
                }
            }

            empresa.setEndereco(null);
            empresa.setIdFilial(null);
            empresa.setTbEmpresaList(null);
            empresa.setTbSalaList(null);
            empresa.setTbUsuarioList(null);

           clear();
            //  this.manager.close();
            return empresa;

        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<TbEmpresa> getOrganizacoesByDominio(String dominio) {
        try {
            List<TbEmpresa> empresas = EManager.getInstance().createNamedQuery("TbEmpresa.findByDominio").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("dominio", dominio).getResultList();

            for (TbEmpresa empresa : empresas) {
                empresa.setChave_endereco(empresa.getEndereco().getCep());

                if (empresa.getIdFilial() != null) {
                    if (empresa.getChave_filiais() == null) {
                        List<String> chaves = new ArrayList<>();
                        empresa.setChave_filiais(chaves);
                    }

                    for (TbEmpresa filial : empresa.getTbEmpresaList()) {
                        empresa.getChave_filiais().add(filial.getCnpj());
                    }
                }

                if (empresa.getTbSalaList() != null) {
                    if (empresa.getChave_salas() == null) {
                        List<Integer> chaves = new ArrayList<>();
                        empresa.setChave_salas(chaves);
                    }

                    for (TbSala sala : empresa.getTbSalaList()) {
                        empresa.getChave_salas().add(sala.getId());
                    }
                }

                if (empresa.getTbUsuarioList() != null) {
                    if (empresa.getChave_usuarios() == null) {
                        List<String> id_funcionarios = new ArrayList<>();
                        empresa.setChave_usuarios(id_funcionarios);
                    }

                    for (TbUsuario usuario : empresa.getTbUsuarioList()) {
                        empresa.getChave_usuarios().add(usuario.getEmail());
                    }
                }

                empresa.setEndereco(null);
                empresa.setIdFilial(null);
                empresa.setTbEmpresaList(null);
                empresa.setTbSalaList(null);
                empresa.setTbUsuarioList(null);
            }

            clear();
            return empresas;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<TbSala> getAllSalas() {
        try {
            List<TbSala> salas = EManager.getInstance().createNamedQuery("TbSala.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

            for (TbSala sala : salas) {
                sala.setChave_empresa(sala.getIdEmpresa().getCnpj());
                if (sala.getListaIdReservas() != null) {
                    if (sala.getListaIdReservas() == null) {
                        List<Integer> chave_reservas = new ArrayList<>();
                        sala.setListaIdReservas(chave_reservas);
                    }

                    for (TbReserva reserva : sala.getTbReservaList()) {
                        sala.getListaIdReservas().add(reserva.getId());
                    }
                }

                if (sala.getTbReservaList() != null) {
                    if (sala.getListaIdReservas() == null) {
                        List<Integer> chave_reservas = new ArrayList<>();
                        sala.setListaIdReservas(chave_reservas);
                    }

                    for (TbReserva reserva : sala.getTbReservaList()) {
                        sala.getListaIdReservas().add(reserva.getId());
                    }
                }

                sala.setIdEmpresa(null);
                sala.setTbReservaList(null);
            }

            clear();
            return salas;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbSala getSalaById(Integer id) {
        try {
            TbSala sala = (TbSala) EManager.getInstance().createNamedQuery("TbSala.findById").setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .setParameter("id", id).getSingleResult();

            sala.setChave_empresa(sala.getIdEmpresa().getCnpj());
            if (sala.getListaIdReservas() != null) {
                if (sala.getListaIdReservas() == null) {
                    List<Integer> chave_reservas = new ArrayList<>();
                    sala.setListaIdReservas(chave_reservas);
                }

                for (TbReserva reserva : sala.getTbReservaList()) {
                    sala.getListaIdReservas().add(reserva.getId());
                }
            }

            if (sala.getTbReservaList() != null) {
                if (sala.getListaIdReservas() == null) {
                    List<Integer> chave_reservas = new ArrayList<>();
                    sala.setListaIdReservas(chave_reservas);
                }

                for (TbReserva reserva : sala.getTbReservaList()) {
                    sala.getListaIdReservas().add(reserva.getId());
                }
            }

            sala.setIdEmpresa(null);
            sala.setTbReservaList(null);

            clear();
            return sala;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static TbEndereco getEnderecoByCEP(String cep) {
        try {
            TbEndereco endereco = (TbEndereco) EManager.getInstance().createNamedQuery("TbEndereco.findByCep").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("cep", cep).getSingleResult();

            if (endereco.getTbEmpresaList() != null) {
                if (endereco.getChave_empresas() == null) {
                    List<String> id_empresas = new ArrayList<>();
                    endereco.setChave_empresas(id_empresas);
                }

                for (TbEmpresa empresa : endereco.getTbEmpresaList()) {
                    endereco.getChave_empresas().add(empresa.getCnpj());
                }
            }
            endereco.setTbEmpresaList(null);

            clear();
            return endereco;
        } catch (NoResultException e) {
            return null;
        }
    }

    public static synchronized void atualizarEmpresa(TbEmpresa empresa) {
        try {      
                EManager.getInstance().getTransaction().begin();
                EManager.getInstance().merge(empresa);
                EManager.getInstance().getTransaction().commit();
                clear();
            
        } catch (Exception e) {
            e.printStackTrace();
            if (EManager.getInstance().getTransaction().isActive()) {
                EManager.getInstance().getTransaction().rollback();
                clear();
            }
        }
    }

    public static synchronized void atualizarReserva(TbReserva reserva) {
        try {
                EManager.getInstance().getTransaction().begin();
                EManager.getInstance().merge(reserva);
                EManager.getInstance().getTransaction().commit();
                clear();
           
        } catch (Exception e) {
            e.printStackTrace();
            if (EManager.getInstance().getTransaction().isActive()) {
                EManager.getInstance().getTransaction().rollback();
                clear();
            }
        }
    }

    public static synchronized void atualizarUsuario(TbUsuario usuario) {
        try {
                EManager.getInstance().getTransaction().begin();
                EManager.getInstance().merge(usuario);
                EManager.getInstance().getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            if (EManager.getInstance().getTransaction().isActive()) {
                EManager.getInstance().getTransaction().rollback();
               clear();
            }
        }
    }

    public static synchronized void novoUsuario(TbUsuario usuario) {
        try {
                EManager.getInstance().getTransaction().begin();
                EManager.getInstance().persist(usuario);
                EManager.getInstance().getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            if (EManager.getInstance().getTransaction().isActive()) {
                EManager.getInstance().getTransaction().rollback();
                clear();
            }
        }
    }

    public static synchronized void novaReserva(TbReserva reserva) {
        try {
                EManager.getInstance().getTransaction().begin();
                EManager.getInstance().persist(reserva);
                EManager.getInstance().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (EManager.getInstance().getTransaction().isActive()) {
                EManager.getInstance().getTransaction().rollback();
                clear();
            }
        }
    }
    
    public static void clear() {
        EManager.getInstance().clear();
    }

//
//    public void modificaUsuario(Usuario usuario) {
//        synchronized (this.operationLock) {
//            this.manager.getTransaction().begin();
//            this.manager.merge(usuario);
//            this.manager.getTransaction().commit();
//        }
//    }
//
//    public void excluirUsuario(Usuario usuario) {
//        synchronized (this.operationLock) {
//            this.manager.getTransaction().begin();
//            this.manager.remove(usuario);
//            this.manager.getTransaction().commit();
//        }
//    }
}
