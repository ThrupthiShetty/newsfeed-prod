/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Provider for greeting message.
 */
@ApplicationScoped
public class FeedProvider {
    private final AtomicReference<MongoClient> mongoClient = new AtomicReference<>();
    /**
     * Create a new greeting provider, reading the message from configuration.
     *
     * @param message greeting to use
     */
    @Inject
    public FeedProvider(@ConfigProperty(name = "app.feed.url") final String url,
            @ConfigProperty(name = "app.feed.url.apikey") final String apikey)

    {

        System.out.println("any info application level need to be passed to the feed provider E.g feed url,api key etc"
                + url + apikey);

        // final Config config = Config.create();

        // final Config configmh = config.get("mongohost"); // =
        // config.get("mongohost").asString().get();

        // final ConfigValue<String> value = configmh.asString();
        // value.get();

        // System.out.println("mongodb connection host: "+ value.get());
        // // + " port "+mongoport);

        // final int mongoport = config.get("mongoport").asInt().orElse(27017);
        final String mongohost = "mongodb";//"mongo-database";// "mongodb"; ;
        final int mongoport = 27017;

        this.mongoClient.set(new MongoClient(mongohost, mongoport));

    }

    

     Document[] getNewsFeeds() {

        final MongoClient mClient = (MongoClient) mongoClient.get();

        final MongoDatabase database = mClient.getDatabase("test");

        final MongoCollection<Document> coll = database.getCollection("newsfeed");

        // coll.createIndex(Indexes.text("author"));

        // String data = coll.find(eq("author", "Itika Sharma Punit"))

        // int feedsCount = coll.find().

        // folowing is the ineffcient way of findingout count
        final List<Document> all = coll.find().into(new ArrayList<Document>());

        final Document returnItem[] = new Document[all.size()];

        final FindIterable<Document> fi = coll.find();

        final MongoCursor<Document> cursor = fi.iterator();

        // = new Document[];
        // cursor.
        try {
            int i = 0;
            while (cursor.hasNext()) {

                returnItem[i] = cursor.next();
                i++;
                // newsItem = returnItem.toJson();

                // System.out.println(newsItem);

            }
        } finally {
            cursor.close();
        }

        return returnItem;

    }
}
