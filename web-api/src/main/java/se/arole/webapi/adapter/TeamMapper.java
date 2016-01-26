package se.arole.webapi.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import se.arole.api.resource.Team;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMapper implements MessageBodyReader<Team>, MessageBodyWriter<Team> {
	private static final Gson gson = new GsonBuilder().registerTypeAdapter(Team.class, new TeamAdapter()).create();

	@Override
	public long getSize(Team arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return type.isAssignableFrom(Team.class);
	}

	@Override
	public void writeTo(Team team, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream out) throws IOException, WebApplicationException {
		try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out))) {
			gson.toJson(team, Team.class, writer);
		}
	}

	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return type.isAssignableFrom(Team.class);
	}

	@Override
	public Team readFrom(Class<Team> type, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream in) throws IOException, WebApplicationException {
		return gson.fromJson(new InputStreamReader(in), type);
	}
}
