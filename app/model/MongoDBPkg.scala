package model
import com.mongodb.client.model.Indexes
import org.mongodb.scala._
import com.mongodb.{ServerApi, ServerApiVersion}
import org.bson.codecs.configuration.{CodecRegistries, CodecRegistry}
import codec.ManagerCodecProvider



object MongoDBPkg {

  val uri = "mongodb://localhost"

  val codecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
    MongoClient.DEFAULT_CODEC_REGISTRY,
    CodecRegistries.fromProviders(new ManagerCodecProvider())
  )


  val mongoClientSettings: MongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(uri))
    .codecRegistry(codecRegistry)
    .build()

  val mongoClient: MongoClient = MongoClient(mongoClientSettings)

  def getConnection(dbName:String):MongoDatabase ={
    mongoClient.getDatabase(dbName)
  }

  def createCollection(db:MongoDatabase,collectionName:String): Unit ={
    val colObs = db.createCollection(collectionName)
    colObs.subscribe(_ => {
      db.getCollection(collectionName).createIndex(Indexes.ascending("id"))
    })
  }


}
