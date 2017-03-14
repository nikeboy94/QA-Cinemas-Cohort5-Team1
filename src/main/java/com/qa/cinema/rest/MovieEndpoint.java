package com.qa.cinema.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



import com.qa.cinema.service.MovieService;

@ApplicationPath("/*")
@Path("/movie")
public class MovieEndpoint {
	
	static final Logger LOGGER = Logger.getLogger(MovieEndpoint.class);
	public static final String UPLOADED_FILE_PARAMETER_NAME = "image";
    public static final String UPLOAD_DIR = System.getProperty("user.dir") + "\\images\\";

	@Inject
	private MovieService service;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllMovies() {
		return service.listAllMovies();
	}

	@Path("/json/searchByTitle/{title}")
	@GET
	@Produces({"application/json"})
	public String getMovieByTitle(@PathParam("title") String title) {
		return service.searchByTitle(title);
	}
	
	@Path("/json/searchMovies/{title}")
	@GET
	@Produces({"application/json"})
	public String searchMoviesByTitle(@PathParam("title") String title) {
		return service.searchMovies(title);
	}
	
	@Path("/json/searchByGenre/{genre}")
	@GET
	@Produces({"application/json"})
	public String getMovieByGenre(@PathParam("genre") String genre) {
		return service.searchByGenre(genre);
	}
	
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addMovie(String movie) {
		return service.createNewMovie(movie);
	}
	

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateMovie(@PathParam("id") Long movieId, String movie) {
		return service.updateMovie(movieId, movie);
	}
	
	@Path("/json/rating/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateRating(@PathParam("id") Long movieId, String movie) {
		return service.updateRating(movieId, movie);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteMovie(@PathParam("id") Long movieId) {
		return service.deleteMovie(movieId);
	}



    @Path("/upload")
    @POST
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get(UPLOADED_FILE_PARAMETER_NAME);
        for (InputPart inputPart : inputParts){
            MultivaluedMap<String, String> headers = inputPart.getHeaders();
            String filename = getFileName(headers);

            try{
                InputStream inputStream = inputPart.getBody(InputStream.class,null);

                byte [] bytes = IOUtils.toByteArray(inputStream);

                writeFile(bytes, UPLOAD_DIR + filename);
            } 
            catch (IOException e) {
            	LOGGER.info(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        }
        return Response.status(Response.Status.OK).build();
        
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        boolean fileCreate = false;

        if (!file.exists()) {
            fileCreate = file.createNewFile();
        }
        LOGGER.info("File created successfully?: " + fileCreate);

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }

    /**
     * Extract filename from HTTP headers.
     * @param headers
     * @return
     */
    private String getFileName(MultivaluedMap<String, String> headers) {
        String[] contentDisposition = headers.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if (filename.trim().startsWith("filename")) {

                String[] name = filename.split("=");

                return sanitizeFilename(name[1]);
            }
        }
        return "unknown";
    }

    private String sanitizeFilename(String s) {
        return s.trim().replaceAll("\"", "");
}
}   
	
