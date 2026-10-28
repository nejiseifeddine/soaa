package ressources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("options")
public class optionsressources {

    OptionBusiness optBusiness = new OptionBusiness();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getAll(@QueryParam("domaine") String domaine) {

        if (domaine != null) {
            return this.optBusiness.getOptionsByDomaine(domaine);
        }

        return this.optBusiness.getListeOptions();
    }



    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Option getOptionByCode(@PathParam("code") int code) {

        return this.optBusiness.getOptionByCode(code);
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOption(Option option) {

        this.optBusiness.addOption(option);

        return Response.status(Response.Status.CREATED).build();
    }



    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifierOption(
            @PathParam("code") int code,
            Option option) {

        boolean resultat =
                this.optBusiness.updateOption(code, option);

        if (resultat) {
            return Response.ok(option).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    
    @DELETE
    @Path("/{code}")
    public Response deleteOption(@PathParam("code") int code) {

        boolean resultat =
                this.optBusiness.deleteOption(code);

        if (resultat) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}