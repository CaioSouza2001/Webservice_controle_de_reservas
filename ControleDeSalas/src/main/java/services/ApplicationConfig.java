package services;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        resources.add(services.CORSFilter.class);
        resources.add(services.EnderecoService.class);
        resources.add(services.OrganizacaoService.class);
        resources.add(services.SalaService.class);
        resources.add(services.UsuarioService.class);
    }
    
}
