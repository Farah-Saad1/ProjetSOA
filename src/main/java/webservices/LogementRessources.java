package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logements")

public class LogementRessources {
    LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = help.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(200).entity(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/delegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = help.getLogementsByDeleguation(delegation);
        return Response.status(200).entity(logements).build();
    }


    @POST
    @Path("/ajouter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(200).entity("Logement ajouté avec succès !").build();
        } else {
            return Response.status(500).entity("Échec de l'ajout du logement.").build();
        }
    }

    @DELETE
    @Path("/supprimer/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response.status(200).entity("Logement supprimé avec succès !").build();
        } else {
            return Response.status(404).entity("Logement non trouvé.").build();
        }
    }


    @GET
    @Path("/liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogements() {
        List<Logement> logementsList = help.getLogements();
        return Response.status(200).entity(logementsList).build();
    }

    @GET
    @Path("/liste/reference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@PathParam("reference") int reference) {
        List<Logement> logementsList = help.getLogementsListeByref(reference);
        if (logementsList.isEmpty()) {
            return Response.status(404).entity("Aucun logement trouvé pour la référence : " + reference).build();
        } else {
            return Response.status(200).entity(logementsList).build();
        }
    }


    @PUT
    @Path("/modifier/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response.status(200).entity("Logement mis à jour avec succès !").build();
        } else {
            return Response.status(404).entity("Logement non trouvé pour la référence : " + reference).build();
        }



    }


    @PUT
    @Path("/setLogements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLogements(List<Logement> logements) {
        help.setLogements(logements);
        return Response.status(200).entity("mise à jour avec succès ").build();
    }




}
