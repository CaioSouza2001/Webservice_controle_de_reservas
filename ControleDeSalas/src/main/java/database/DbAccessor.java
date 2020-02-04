package database;

import entidades.TbEmpresa;
import entidades.TbEndereco;
import entidades.TbReserva;
import entidades.TbSala;
import entidades.TbUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class DbAccessor {

    private final EntityManager manager;
    private final Object operationLock;

    public DbAccessor(EntityManager manager, Object operationLock) {
        this.manager = manager;
        this.operationLock = operationLock;
    }

    public List<TbUsuario> getAllUsuarios() {
        List<TbUsuario> usuarios = this.manager.createNamedQuery("TbUsuario.findAll").getResultList();

        for (TbUsuario usuario : usuarios) {
            if (usuario.getTbReservaList() != null) {
                if (usuario.getListaChaveReservas() == null) {
                    List<Integer> chave_reservas = new ArrayList<>();
                    usuario.setListaChaveReservas(chave_reservas);
                }
                
                for(TbReserva reserva : usuario.getTbReservaList())
                {
                    usuario.getListaChaveReservas().add(reserva.getId());
                }      
            }
            
            usuario.setChave_empresa(usuario.getCnpjEmpresa().getCnpj());

        }

        return usuarios;
    }

    public TbUsuario getUserByEmail(String email) {
        try {
            TbUsuario usuario = (TbUsuario) this.manager.createNamedQuery("TbUsuario.findByEmail").setParameter("email", email).getSingleResult();
            
            if (usuario.getTbReservaList() != null) {
                if (usuario.getListaChaveReservas() == null) {
                    List<Integer> chave_reservas = new ArrayList<>();
                    usuario.setListaChaveReservas(chave_reservas);
                }
                
                for(TbReserva reserva : usuario.getTbReservaList())
                {
                    usuario.getListaChaveReservas().add(reserva.getId());
                }      
            }
            
            usuario.setChave_empresa(usuario.getCnpjEmpresa().getCnpj());
            
            
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    /*public TbUsuario getCredencials(String email) {
        try {
            return (TbUsuario) this.manager.createNamedQuery("TbUsuario.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }*/
    public List<TbEmpresa> getAllOrganizacoes() {
        List<TbEmpresa> empresas = this.manager.createNamedQuery("TbEmpresa.findAll").getResultList();
        
        for(TbEmpresa empresa : empresas)
        {
            empresa.setChave_endereco(empresa.getEndereco().getCep());
            
            if(empresa.getIdFilial() != null)
            {
                if(empresa.getChave_filiais() == null)
                {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }
                
                for(TbEmpresa filial : empresa.getTbEmpresaList())
                {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }
            
            if(empresa.getTbSalaList() != null)
            {
                if(empresa.getChave_salas()== null)
                {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }
                
                for(TbSala sala : empresa.getTbSalaList())
                {
                    empresa.getChave_salas().add(sala.getId());
                }
            }
        }
        
        return empresas;
    }

    public TbEmpresa getOrganizacaoById(String id) {
        try {
            TbEmpresa empresa =  (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByCnpj")
                    .setParameter("cnpj", id).getSingleResult();
            
            empresa.setChave_endereco(empresa.getEndereco().getCep());
            
            if(empresa.getIdFilial() != null)
            {
                if(empresa.getChave_filiais() == null)
                {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }
                
                for(TbEmpresa filial : empresa.getTbEmpresaList())
                {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }
            
            if(empresa.getTbSalaList() != null)
            {
                if(empresa.getChave_salas()== null)
                {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }
                
                for(TbSala sala : empresa.getTbSalaList())
                {
                    empresa.getChave_salas().add(sala.getId());
                }
            }
            
            
            return empresa;
        } catch (NoResultException e) {
            return null;
        }
    }

    public TbEmpresa getOrganizacaoByDominio(String dominio) {
        try {
            TbEmpresa empresa = (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByDominio").setParameter("dominio", dominio).getSingleResult();
       
       empresa.setChave_endereco(empresa.getEndereco().getCep());
            
            if(empresa.getIdFilial() != null)
            {
                if(empresa.getChave_filiais() == null)
                {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }
                
                for(TbEmpresa filial : empresa.getTbEmpresaList())
                {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }
            
            if(empresa.getTbSalaList() != null)
            {
                if(empresa.getChave_salas()== null)
                {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }
                
                for(TbSala sala : empresa.getTbSalaList())
                {
                    empresa.getChave_salas().add(sala.getId());
                }
            }
        
        return empresa;
        
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbEmpresa> getOrganizacoesByDominio(String dominio) {
        try {
            List<TbEmpresa> empresas = this.manager.createNamedQuery("TbEmpresa.findByDominio").setParameter("dominio", dominio).getResultList();
       
        
        for(TbEmpresa empresa: empresas)
        {
            empresa.setChave_endereco(empresa.getEndereco().getCep());
            
            if(empresa.getIdFilial() != null)
            {
                if(empresa.getChave_filiais() == null)
                {
                    List<String> chaves = new ArrayList<>();
                    empresa.setChave_filiais(chaves);
                }
                
                for(TbEmpresa filial : empresa.getTbEmpresaList())
                {
                    empresa.getChave_filiais().add(filial.getCnpj());
                }
            }
            
            if(empresa.getTbSalaList() != null)
            {
                if(empresa.getChave_salas()== null)
                {
                    List<Integer> chaves = new ArrayList<>();
                    empresa.setChave_salas(chaves);
                }
                
                for(TbSala sala : empresa.getTbSalaList())
                {
                    empresa.getChave_salas().add(sala.getId());
                }
            }
        }
        return empresas;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbSala> getAllSalas() {
        List<TbSala> salas = this.manager.createNamedQuery("TbSala.findAll").getResultList();
        
        for(TbSala sala : salas)
        {
            sala.setChave_empresa(sala.getIdEmpresa().getCnpj());
            if(sala.getListaIdReservas() != null)
            {
                if(sala.getListaIdReservas() == null)
                {
                    List<Integer> chave_reservas = new ArrayList<>();
                    sala.setListaIdReservas(chave_reservas);
                }
                
                for(TbReserva reserva : sala.getTbReservaList())
                {
                    sala.getListaIdReservas().add(reserva.getId());
                }
            }
            
            if(sala.getTbReservaList() != null)
            {
                if(sala.getListaIdReservas() == null)
                {
                    List<Integer> chave_reservas = new ArrayList<>();
                    sala.setListaIdReservas(chave_reservas);
                }
                
                for(TbReserva reserva : sala.getTbReservaList())
                {
                    sala.getListaIdReservas().add(reserva.getId());
                }
            }           
        }
        
        return salas;
    }

    public TbSala getSalaById(Integer id)
    {
        return (TbSala) this.manager.createNamedQuery("TbSala.findById").setParameter("id", id).getSingleResult();
    }
    
    public TbEndereco getEnderecoByCEP(String cep) {
        try {
            return (TbEndereco) this.manager.createNamedQuery("TbEndereco.findByCep").setParameter("cep", cep).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbReserva> getAllAlocacaoSalas() {
        return this.manager.createNamedQuery("TbReserva.findAll").getResultList();
    }

    public void atualizarEmpresaNovoFuncionario(TbEmpresa empresa) {
        synchronized (this.operationLock) {
            this.manager.getTransaction().begin();
            this.manager.merge(empresa);
            this.manager.getTransaction().commit();
        }
    }

    public void novoUsuario(TbUsuario usuario) {
        synchronized (this.operationLock) {
            this.manager.getTransaction().begin();
            this.manager.persist(usuario);
            this.manager.getTransaction().commit();
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
