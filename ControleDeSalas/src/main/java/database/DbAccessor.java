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

    private final EntityManager manager;
    private final Object operationLock;

    public DbAccessor(EntityManager manager, Object operationLock) {
        this.manager = manager;
        this.operationLock = operationLock;
    }

    public List<TbUsuario> getAllUsuarios() {
        try {
            List<TbUsuario> usuarios = this.manager.createNamedQuery("TbUsuario.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

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

            this.manager.clear();
            // this.manager.close();
            return usuarios;
        } catch (NoResultException e) {
            return null;
        }
    }

    public TbUsuario getUserByEmail(String email) {
        try {
            TbUsuario usuario = (TbUsuario) this.manager.createNamedQuery("TbUsuario.findByEmail").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("email", email).getSingleResult();

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

            this.manager.clear();
            //this.manager.close();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public TbReserva getReservaById(int id) {
        try {
            TbReserva reserva = (TbReserva) this.manager.createNamedQuery("TbReserva.findById").setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .setParameter("id", id).getSingleResult();

            reserva.setChave_organizador(reserva.getIdOrganizador().getEmail());
            reserva.setChave_sala(reserva.getIdSala().getId());

            reserva.setIdOrganizador(null);
            reserva.setIdSala(null);

            this.manager.clear();
            return reserva;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbEmpresa> getAllOrganizacoes() {
        try {
            List<TbEmpresa> empresas = this.manager.createNamedQuery("TbEmpresa.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

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

            this.manager.clear();
            //  this.manager.close();
            return empresas;

        } catch (NoResultException e) {
            return null;
        }
    }

    public TbEmpresa getOrganizacaoById(String id) {
        try {
            TbEmpresa empresa = (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByCnpj").setHint(QueryHints.REFRESH, HintValues.TRUE)
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

            this.manager.clear();
            //  this.manager.close();
            return empresa;

        } catch (NoResultException e) {
            return null;
        }
    }

    public TbEmpresa getOrganizacaoByDominio(String dominio) {
        try {
            TbEmpresa empresa = (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByDominio").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("dominio", dominio).getSingleResult();

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

            this.manager.clear();
            //  this.manager.close();
            return empresa;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbEmpresa> getOrganizacoesByDominio(String dominio) {
        try {
            List<TbEmpresa> empresas = this.manager.createNamedQuery("TbEmpresa.findByDominio").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("dominio", dominio).getResultList();

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

            this.manager.clear();
            return empresas;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbSala> getAllSalas() {
        try {
            List<TbSala> salas = this.manager.createNamedQuery("TbSala.findAll").setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

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

            this.manager.clear();
            return salas;
        } catch (NoResultException e) {
            return null;
        }
    }

    public TbSala getSalaById(Integer id) {
        try {
            TbSala sala = (TbSala) this.manager.createNamedQuery("TbSala.findById").setHint(QueryHints.REFRESH, HintValues.TRUE)
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

            this.manager.clear();
            return sala;
        } catch (NoResultException e) {
            return null;
        }
    }

    public TbEndereco getEnderecoByCEP(String cep) {
        try {
            TbEndereco endereco = (TbEndereco) this.manager.createNamedQuery("TbEndereco.findByCep").setHint(QueryHints.REFRESH, HintValues.TRUE).setParameter("cep", cep).getSingleResult();

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

            this.manager.clear();
            return endereco;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void atualizarEmpresa(TbEmpresa empresa) {
        try {
            synchronized (this.operationLock) {
                this.manager.getTransaction().begin();
                this.manager.merge(empresa);
                this.manager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.manager.getTransaction().isActive()) {
                this.manager.getTransaction().rollback();
                this.manager.clear();
            }
        }
    }

    public void atualizarReserva(TbReserva reserva) {
        try {
            synchronized (this.operationLock) {
                this.manager.getTransaction().begin();
                this.manager.merge(reserva);
                this.manager.getTransaction().commit();
                this.manager.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.manager.getTransaction().isActive()) {
                this.manager.getTransaction().rollback();
                this.manager.clear();
            }
        }
    }

  

    public void atualizarUsuario(TbUsuario usuario) {
        try {
            synchronized (this.operationLock) {
                this.manager.getTransaction().begin();
                this.manager.merge(usuario);
                this.manager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.manager.getTransaction().isActive()) {
                this.manager.getTransaction().rollback();
                this.manager.clear();
            }
        }
    }

    public void novoUsuario(TbUsuario usuario) {
        try {
            synchronized (this.operationLock) {
                this.manager.getTransaction().begin();
                this.manager.persist(usuario);
                this.manager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.manager.getTransaction().isActive()) {
                this.manager.getTransaction().rollback();
                this.manager.clear();
            }
        }
    }

    public void novaReserva(TbReserva reserva) {
        try {
            synchronized (this.operationLock) {
                this.manager.getTransaction().begin();
                this.manager.persist(reserva);
                this.manager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.manager.getTransaction().isActive()) {
                this.manager.getTransaction().rollback();
                this.manager.clear();
            }
        }
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
