const fs = require('fs');
var express = require('express');
var request = require("request");
var bodyParser = require('body-parser');
var cors = require('cors')
const dotenv = require('dotenv');
dotenv.config();
const initDatabases = require('./dbs');
initDatabases().then(dbs => {

  console.log("connected to mongo db");
  let db = dbs.devlopment;
  let app = express();
  app.use(cors())
  app.mongodb = dbs.devlopment; // (procee.env.env ==dev) ? <development> : <production> 


  //});
  // fs.readFile('apis/newsfeed.json', "utf8", (err, data) => {
  //   if (err) throw err;
  //   let articlesArray = JSON.parse(data).articles;
  //   console.log(articlesArray)

  //   for (var i = 0; i < articlesArray.length; i++) {
  //     for (var j = i + 1; j < articlesArray.length; j++) {
  //       let splitarray1 = articlesArray[i].title.split(" ")
  //       let splitarray2 = articlesArray[j].title.split(" ")
  //       console.log(splitarray1, splitarray2)

  //     }
  //   }
  // })

  app.use(bodyParser.json()); // support json encoded bodies
  app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies
  const port = process.env.PORT || 3080;
  db.collection("newsfeed").drop(function (err, delOK) {
    if (err) throw err;
    if (delOK) console.log("Collection deleted");
  });
  request('https://newsapi.org/v2/top-headlines?country=in&apiKey=dd73b96fac8740bbaaa1fb86da64af9b', function (error, response, body) {
    console.log("hh")
    if (error) throw error;;
    db.collection('newsfeed').insertMany((JSON.parse(body)).articles).then(function (result) {
      console.log(`added a new doc ${result.result} insertid ${result.insertedId}`);

    });;
    db.collection('newsfeed').createIndex( { description: "text", title: "text" } )

    //console.log(JSON.parse(response))
    //console.log(JSON.parse(body))
  });
  app.get('/', function (req, res) {
    res.send(200, "Hello" + new Date().getTime());
  });

  app.listen(port, function (error) {
    if (error) {
      console.error(error)
    } else {
      console.info("Listening on port %s. Open up http://localhost:%s/ in your browser.", port, port)
    }
  })

  app.get('/topnews', (req, res) => {
    console.log("jjjjj")
    db.collection('newsfeed').find({}).limit(100).toArray((err, docs) => {

      // .then(function (result) {
      //console.log(`added a new doc ${result.result} insertid ${result.insertedId}`);
      res.json(docs)
    });

  })

  app.get('/search/:searchstring', (req, res) => {
    //
   // db.stores.createIndex({ name: "text", description: "text" })
  //})

  db.collection('newsfeed').find( { $text: { $search: req.params.searchstring } } ).limit(100).toArray((err, docs) => {

    // .then(function (result) {
    //console.log(`added a new doc ${result.result} insertid ${result.insertedId}`);
    res.json(docs)
  });

})


const { Builder, By, Key, until } = require('selenium-webdriver');

  // (async function example() {
  //   let driver = await new Builder().forBrowser('chrome').build();
  //   try {
  //     await driver.get('http://www.google.com/ncr');
  //     await driver.findElement(By.name('q')).sendKeys('webdriver', Key.RETURN);
  //    // await driver.wait(until.titleIs('webdriver - Google Search'), 1000);
  //   } finally {
  //     //await driver.quit();
  //   }
  // })();

});