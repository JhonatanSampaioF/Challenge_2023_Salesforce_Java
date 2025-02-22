package fiap.tds.resources;

import fiap.tds.models.Produto;
import fiap.tds.repositories.ProdutoRepositoryOracle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("produtos")
public class ProdutoResource {

    ProdutoRepositoryOracle produtoRepo = new ProdutoRepositoryOracle();


    @GET
    @Path("{id_produto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduto(@PathParam("id_produto") int id_produto) {
        var produto = produtoRepo.findById(id_produto);
        if (produto == null)
            return Response.status(404).entity("Produto não encontrado").build();
        return Response.status(200).entity(produto).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduto(Produto produto) {
        if (produto == null)
            return Response.status(400).entity("Produto não pode ser nulo").build();
        produtoRepo.create(produto);
        return Response.status(201).entity(produto).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProdutos() {
        List<Produto> produtos = produtoRepo.readAll();
        return Response.status(200).entity(produtos).build();
    }


    @PUT
    @Path("{id_produto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id_produto") int id_produto, Produto produto) {
        produto.setId(id_produto);
        produtoRepo.update(produto);
        return Response.status(Response.Status.OK).entity(produto).build();
    }


    @DELETE
    @Path("{id_produto}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduto(@PathParam("id_produto") int id_produto) {
        produtoRepo.delete(id_produto);
        return Response.status(204).build();
    }

}

