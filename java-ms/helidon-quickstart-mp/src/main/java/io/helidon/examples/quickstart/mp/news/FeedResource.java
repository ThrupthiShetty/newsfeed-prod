/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.helidon.examples.quickstart.mp.news;

import java.io.StringReader;
import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/greet
 *
 * Get greeting message for Joe:
 * curl -X GET http://localhost:8080/greet/Joe
 *
 * Change greeting
 * curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Howdy"}' http://localhost:8080/greet/greeting
 *
 * The message is returned as a JSON object.
 */
@Path("/feed")
@RequestScoped
public class FeedResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    /**
     * The greeting message provider.
     */
    private final FeedProvider feedProvider;

    /**
     * Using constructor injection to get a configuration property.
     * By default this gets the value from META-INF/microprofile-config
     *
     * @param greetingConfig the configured greeting message
     */
    @Inject
    public FeedResource(final FeedProvider feedConfig) {
		this.feedProvider = feedConfig;
    }

    /**
     * Return a wordly greeting message.
     *
     * @return {@link JsonObject}
     */
    @SuppressWarnings("checkstyle:designforextension")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNewsFeeds() {
        final Document myewsFeeds[] = feedProvider.getNewsFeeds();

        /// JsonReader jsonReader = Json.createReader(new
        /// StringReader(myewsFeed.toJson()));
        // JsonObject reply = jsonReader.readObject();

        JsonObject newsFeedJOs[] = new JsonObject[myewsFeeds.length];

        JsonReader jsonReader = null;
        for (int i = 0; i < myewsFeeds.length; i++) {
            Document myewsFeed = myewsFeeds[i];

            jsonReader = Json.createReader(new StringReader(myewsFeed.toJson()));

            newsFeedJOs[i] = jsonReader.readObject();

        }
        // final JSONObject finalR = new JSONObject(myewsFeed.toJson());

        return Response.ok().entity(newsFeedJOs).header("Access-Control-Allow-Origin", "*").build();
    }

    /**
     * Return a greeting message using the name that was provided.
     *
     * @param name the name to greet
     * @return {@link JsonObject}
     */
    @SuppressWarnings("checkstyle:designforextension")
    @Path("/filters/{filtername}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getFilteredNews(@PathParam("name") final String name) {
        return null;
    }

    /**
     * Set the greeting to use in future messages.
     *
     * @param jsonObject JSON containing the new greeting
     * @return {@link Response}
     */
//     @SuppressWarnings("checkstyle:designforextension")
//     @Path("/greeting")
//     @PUT
//     @Consumes(MediaType.APPLICATION_JSON)
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response updateGreeting(final JsonObject jsonObject) {

//         if (!jsonObject.containsKey("greeting")) {
//             final JsonObject entity = JSON.createObjectBuilder()
//                     .add("error", "No greeting provided")
//                     .build();
//             return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
//         }

//         final String newGreeting = jsonObject.getString("greeting");

//         feedProvider.setMessage(newGreeting);
//         return Response.status(Response.Status.NO_CONTENT).build();
//     }

//     private JsonObject createResponse(final String who) {
//         final String msg = String.format("%s %s!", feedProvider.getMessage(), who);

//         return JSON.createObjectBuilder()
//                 .add("message", msg)
//                 .build();
//     }
 }
