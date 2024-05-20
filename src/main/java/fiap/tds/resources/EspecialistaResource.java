package fiap.tds.resources;

import fiap.tds.models.Especialista;
import fiap.tds.repositories.EspecialistaRepositoryOracle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("especialistas")
public class EspecialistaResource {

    EspecialistaRepositoryOracle especialistaRepo = new EspecialistaRepositoryOracle();


    @GET
    @Path("{id_espec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEspecialista(@PathParam("id_espec") int id_espec) {
        var especialista = especialistaRepo.findById(id_espec);
        if (especialista == null)
            return Response.status(404).entity("Especialista não encontrado").build();
        return Response.status(200).entity(especialista).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEspecialista(Especialista especialista) {
        if (especialista == null)
            return Response.status(400).entity("Especialista não pode ser nulo").build();
        especialistaRepo.create(especialista);
        return Response.status(201).entity(especialista).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEspecialistas() {
        List<Especialista> especialistas = especialistaRepo.readAll();
        return Response.status(200).entity(especialistas).build();
    }


    @PUT
    @Path("{id_espec}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEspecialista(@PathParam("id_espec") int id_espec, Especialista especialista) {
        especialista.setId(id_espec);
        especialistaRepo.update(especialista);
        return Response.status(Response.Status.OK).entity(especialista).build();
    }


    @DELETE
    @Path("{id_espec}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEspecialista(@PathParam("id_espec") int id_espec) {
        especialistaRepo.delete(id_espec);
        return Response.status(204).build();
    }

}

