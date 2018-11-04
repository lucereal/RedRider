const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors'); //MW so we can make req to our api from different domain
const app = express();
const mysql = require('mysql');
const db = require('./dbmanager');
//Cors Middleware
// app.use(function (req, res, next) {
//     //Enabling CORS 
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
//     res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
//     next();
// });

app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



app.get('/', function (req, res) {
    app.send('invlalid endpoint');
});

app.post('/makepost', function(req, res){

    res.json({
        success: true
        
    })
})

app.post('/login', function(req,res){
    const email = req.body.email;
    const password = req.body.password;

    console.log("email: " + email);
    console.log("password: " + password);

    db.login(email,password);
    var user = {
        email: email,
        password: password
    }

    res.json({
        success:true,
        user: user
    })
})

app.listen(3000, function () {
    console.log('Listening on port 3000.');
})

