package ressources;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("etudiants")
public class studentressources {

    EtudiantBusiness etudiantBusiness = new EtudiantBusiness();
    OptionBusiness optionBusiness = new OptionBusiness();



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEtudiant(Etudiant etudiant) {

        boolean resultat =
                this.etudiantBusiness.addEtudiant(etudiant);

        if (resultat) {
            return Response.status(Response.Status.CREATED)
                    .entity(etudiant)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getAllEtudiants() {

        return this.etudiantBusiness.getAllEtudiants();
    }



    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(
            @PathParam("identifiant") String identifiant) {

        Etudiant etudiant =
                this.etudiantBusiness.getEtudiantByIdentifiant(identifiant);

        if (etudiant != null) {
            return Response.ok(etudiant).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(
            @PathParam("identifiant") String identifiant) {

        boolean resultat =
                this.etudiantBusiness.deleteEtudiant(identifiant);

        if (resultat) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("identifiant") String identifiant,
            Etudiant etudiant) {

        boolean resultat =
                this.etudiantBusiness.updateEtudiant(
                        identifiant,
                        etudiant
                );

        if (resultat) {
            return Response.ok(etudiant).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public EtudiantList getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {

        Option option =
                this.optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return new EtudiantList();
        }

        List<Etudiant> etudiants =
                this.etudiantBusiness.getEtudiantsByOption(option);

        return new EtudiantList(etudiants);
    }
}
