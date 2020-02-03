package database;

import entidades.TbEmpresa;
import entidades.TbEndereco;
import entidades.TbReserva;
import entidades.TbSala;
import entidades.TbUsuario;
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
        return this.manager.createNamedQuery("TbUsuario.findAll").getResultList();
    }
   

    public TbUsuario getUserByEmail(String email) {
        try {
            return (TbUsuario) this.manager.createNamedQuery("TbUsuario.findByEmail").setParameter("email", email).getSingleResult();
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
        return this.manager.createNamedQuery("TbEmpresa.findAll").getResultList();
    }

    public TbEmpresa getOrganizacaoById(String id) {
        try {
            return (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByCnpj").setParameter("cnpj", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
     

    public TbEmpresa getOrganizacaoByDominio(String dominio) {
        try {
            return (TbEmpresa) this.manager.createNamedQuery("TbEmpresa.findByDominio").setParameter("dominio", dominio).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbEmpresa> getOrganizacoesByDominio(String dominio) {
        try {
            return this.manager.createNamedQuery("TbEmpresa.findByDominio").setParameter("dominio", dominio).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TbSala> getAllSalas() {
        return this.manager.createNamedQuery("TbSala.findAll").getResultList();
    }
    
    public TbEndereco getEnderecoByCEP(String cep)
    {
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
