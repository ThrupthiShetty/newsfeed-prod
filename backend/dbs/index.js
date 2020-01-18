const MongoClient = require('mongodb').MongoClient
 
// Note: A production application should not expose database credentials in plain text.
// For strategies on handling credentials, visit 12factor: https://12factor.net/config.


//firebase functions:config:set mongo.host="value for mongo host" mongo.user="mongo userid " mongo.password="mongo password " mongo.port="monog.port" mongo.db="monog db " monog.authdb="mongo auth source"
const dbConnetionString = 
//`mongodb+srv://${process.env.mongo_ss_user}:${process.env.mongo_ss_password}@${process.env.mongo_ss_cluster_host}/${process.env.mongo_ss_db}?retryWrites=true&w=majority`
 `mongodb://${process.env.mongo_host}:${process.env.mongo_port}/${process.env.mongo_db}?retryWrites=true&ssl=false`
console.log(dbConnetionString)
 //mongo "mongodb+srv://sscluster-6kv3t.gcp.mongodb.net/test"  --username <username> 
//const PROD_URI = "mongodb://<dbuser>:<dbpassword>@<host1>:<port1>,<host2>:<port2>/<dbname>?replicaSet=<replicaSetName>"
//const MKTG_URI = "mongodb://<dbuser>:<dbpassword>@<host1>:<port1>,<host2>:<port2>/<dbname>?replicaSet=<replicaSetName>"
 
function connect(url) {
  return MongoClient.connect(dbConnetionString,
    { useUnifiedTopology: true,
        useNewUrlParser: true }).then(client => client.db())
}
 
module.exports = async function() {
//   let databases = await Promise.all([connect(PROD_URI), connect(MKTG_URI)])
 
//   return {
//     production: databases[0],
//     marketing: databases[1]
//   }

console.log("connecting to mongo db ", dbConnetionString);

let databases = await Promise.all([connect(dbConnetionString)])

 
  return {
    devlopment: databases[0]
  }

}