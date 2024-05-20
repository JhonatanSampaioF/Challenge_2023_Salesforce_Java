package fiap.tds.resources;

import fiap.tds.models.Feedback;
import fiap.tds.repositories.FeedbackRepositoryOracle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("feedbacks")
public class FeedbackResource {

    FeedbackRepositoryOracle feedbackRepo = new FeedbackRepositoryOracle();


    @GET
    @Path("{id_feedback}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedback(@PathParam("id_feedback") int id_feedback) {
        var feedback = feedbackRepo.findById(id_feedback);
        if (feedback == null)
            return Response.status(404).entity("Feedback não encontrado").build();
        return Response.status(200).entity(feedback).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFeedback(Feedback feedback) {
        if (feedback == null)
            return Response.status(400).entity("Feedback não pode ser nulo").build();
        feedbackRepo.create(feedback);
        return Response.status(201).entity(feedback).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepo.readAll();
        return Response.status(200).entity(feedbacks).build();
    }


    @PUT
    @Path("{id_feedback}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFeedback(@PathParam("id_feedback") int id_feedback, Feedback feedback) {
        feedback.setId(id_feedback);
        feedbackRepo.update(feedback);
        return Response.status(Response.Status.OK).entity(feedback).build();
    }


    @DELETE
    @Path("{id_feedback}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFeedback(@PathParam("id_feedback") int id_feedback) {
        feedbackRepo.delete(id_feedback);
        return Response.status(204).build();
    }

}

