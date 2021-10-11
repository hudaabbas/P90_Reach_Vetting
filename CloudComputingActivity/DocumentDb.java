/* Huda Abbas
P90- Reach Worldwide Activity 1
Cloud Computing
*/

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class DocumentDb {
    public static void main(String[] args){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDB("myFavoriteMovies");

        createComedyDocuments(database);
        createActionDocuments(database);
        createThrillerDocuments(database);

        mongoClient.getDatabaseNames().forEach(System.out::println);
        Set collections = database.getCollectionNames();

        for (String collection : collections)
        {
            System.out.println(collection);
            BasicDBObject query = new BasicDBObject();

            DBCursor cursor = collection.find(query);

            while(cursor.hasNext())
            {
                System.out.println(cursor.next());
            }
            cursor.close();
        }

    }

    private static void createComedyDocuments(DB database){
        //Creating three collections (by genre)
        database.createCollection("Comedy Movies", null);

        //creating documents for "Comedy Movies" collection
        DBCollection comedyCollection = database.getCollection("Comedy Movies");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Home Alone");
        document.put("producer", "Chris Columbus");
        document.put("poster image link", "https://www.imdb.com/title/tt0099785/mediaviewer/rm2335283456/");
        document.put("rating", "7.6");
        document.put("release date", "1990"); //extra attribute
        comedyCollection.insert(document);
    }

    private static void createThrillerDocuments(DB database){
        database.createCollection("Thriller Movies", null);

        //creating documents for "Thriller Movies" collection
        DBCollection thrillerCollection = database.getCollection("Thriller Movies");
        BasicDBObject document1 = new BasicDBObject();
        document1.put("name", "Get Out");
        document1.put("producer", "Jordan Peele");
        document1.put("poster image link", "https://www.imdb.com/title/tt5052448/mediaviewer/rm1537293568/");
        document1.put("rating", "9.6");
        thrillerCollection.insert(document1);

        BasicDBObject document2 = new BasicDBObject();
        document2.put("name", "Bird Box");
        document2.put("producer", "Susanne Bier");
        document2.put("poster image link", "https://www.imdb.com/title/tt2737304/mediaviewer/rm1969058304/");
        document2.put("rating", "7.6");
        thrillerCollection.insert(document2);
    }

    private static void createActionDocuments(DB database){
        //creating the colllection
        database.createCollection("Action Movies", null);

        //creating documents for "Action Movies" collection
        DBCollection actionCollection = database.getCollection("Action Movies");
        BasicDBObject document3 = new BasicDBObject();
        document3.put("name", "Blank Panther");
        document3.put("producer", "Ryan Coogler");
        document3.put("poster image link", "https://www.imdb.com/title/tt1825683/mediaviewer/rm172972800/");
        document3.put("rating", "8.6");
        actionCollection.insert(document3);

    }
}